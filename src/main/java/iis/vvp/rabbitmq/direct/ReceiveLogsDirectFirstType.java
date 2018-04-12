package iis.vvp.rabbitmq.direct;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeoutException;

public class ReceiveLogsDirectFirstType {
    private final static String EXCHANGE_NAEM = "Equipment Types";
    private final static String EXCHANGE_TYPE = "direct";
    private final static String[]  EQUIPMENT_TYPES= {
            "EquipmentInformation Transaction Types",
            "EquipmentClass Transaction Types",
            "Equipment Transaction Types",
            "EquipmentCapabilityTestSpec Transaction Types"};
    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建连接和频道
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        // 声明交换机
        channel.exchangeDeclare(EXCHANGE_NAEM, EXCHANGE_TYPE);

        //生成随机队列名
        String queueName = channel.queueDeclare().getQueue();
        //指定Binding Key
        String equipmentTypes = "EquipmentInformation Transaction Types";

        channel.queueBind(queueName, EXCHANGE_NAEM, equipmentTypes);
        System.out.println("开始等待MQ中间件消息....");
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" 接收到的消息是： '" + message + "'");
            }
        };
        channel.basicConsume(queueName, true, consumer);
    }


}

