本工程模拟多个消费者从同一交换器中获取符合自己binding key的消息的路由消息队列功能。
1、EmitLogsDirect作为消息生产者，
    创建direct型的EXCHANGE_NAEM = "Equipment Types"的交换器，
    并向其中随机发送六条EQUIPMENT_TYPES随机类型的消息。
2、ReceiveLogsDirect作为消息消费者，
    创建direct型的EXCHANGE_NAEM = "Equipment Types"的交换器，
    随机绑定一种类型的EQUIPMENT_TYPES，
    并接收符合binding key的消息。
3、ReceiveLogsDirectFirstType作为消息消费者，
    创建direct型的EXCHANGE_NAEM = "Equipment Types"的交换器，
    随机绑定第一种类型的EQUIPMENT_TYPES，
    并接收符合binding key的消息。
