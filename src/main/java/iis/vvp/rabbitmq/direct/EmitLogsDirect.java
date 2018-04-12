package iis.vvp.rabbitmq.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

public class EmitLogsDirect {
    private final static String EXCHANGE_NAEM = "Equipment Types";
    private final static String EXCHANGE_TYPE = "direct";
    private final static String[]  EQUIPMENT_TYPES= {
            "EquipmentInformation Transaction Types",
            "EquipmentClass Transaction Types",
            "Equipment Transaction Types",
            "EquipmentCapabilityTestSpec Transaction Types"};
    public static void main(String[] args)throws IOException,TimeoutException{
        // 创建连接和频道
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        // 声明交换机
        channel.exchangeDeclare(EXCHANGE_NAEM, EXCHANGE_TYPE);
        for(int i=0;i<6;i++){
            String equipmentTypes = getEquipmentTypes();
            System.out.println("******************"+equipmentTypes);
            String message = equipmentTypes + " _log "+ UUID.randomUUID().toString();
            channel.basicPublish(EXCHANGE_NAEM,equipmentTypes,null,message.getBytes());
            System.out.println("发送的消息是："+message);
        }
        // 关闭频道和连接
        channel.close();
        connection.close();
    }
    private static String getEquipmentTypes(){
        Random random = new Random();
        int ranval = random.nextInt(3);
        System.out.println("-------------"+ranval);
        return EQUIPMENT_TYPES[ranval];
    }

}
