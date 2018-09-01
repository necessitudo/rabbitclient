import com.rabbitmq.client.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main{

    public static void main(String[] args) {


        ConnectionFactory factory = new ConnectionFactory();
        // "guest"/"guest" by default, limited to localhost connections
        factory.setUsername("dubrovin");
        factory.setPassword("1234567890");
        factory.setVirtualHost("main");
        factory.setHost("192.168.58.125");
        factory.setPort(5672);

        try {
            final Connection conn = factory.newConnection();
            final Channel channel = conn.createChannel();

            System.out.println("Connection success!");

            Thread shutdownHook = new Thread( "rabbitclient-shutdown-hook" )
            {
                public void run()
                {
                    System.out.println( "Starting MyApp shutdown..." );
                    // Do some cleanup work.

                    try {
                        channel.close();
                        conn.close();
                    }
                    catch (Exception e){}

                    System.out.println( "MyApp shutdown complete." );

                }
            };
            Runtime.getRuntime().addShutdownHook( shutdownHook );

            boolean autoAck = false;
            channel.basicConsume("goodsforwms", autoAck, "goodsforcrm",
                    new DefaultConsumer(channel) {
                        @Override
                        public void handleDelivery(String consumerTag,
                                                   Envelope envelope,
                                                   AMQP.BasicProperties properties,
                                                   byte[] body)
                                throws IOException
                        {
                            String routingKey = envelope.getRoutingKey();
                            String contentType = properties.getContentType();
                            long deliveryTag = envelope.getDeliveryTag();
                            // (process the message components here ...)

                            String str = new String(body, StandardCharsets.UTF_8);
                            System.out.println("receiving!"+str);
                            channel.basicAck(deliveryTag, false);
                        }
                    });

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }


        /*String text = "Hello world";
        BufferedWriter output = null;
        try {
            File file = new File("C:\\Users\\dubrovinom\\Documents\\RabbitTest\\example.txt");
            output = new BufferedWriter(new FileWriter(file));
            output.write(text);
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
            if ( output != null ) {
               try {

                   output.close();

               }
               catch (Exception e){}
            }
        }

       /*ConnectionFactory factory = new ConnectionFactory();
        // "guest"/"guest" by default, limited to localhost connections
        factory.setUsername("dubrovin");
        factory.setPassword("1234567890");
        factory.setVirtualHost("main");
        factory.setHost("192.168.58.125");
        factory.setPort(5672);

        try {
            final Connection conn = factory.newConnection();
            final Channel channel = conn.createChannel();

            //String queueName = channel.queueDeclare().getQueue();
            // channel.queueBind("goodsforcrm", "goods", "goodsforcrm");

            boolean autoAck = false;
            channel.basicConsume("goodsforwms", autoAck, "goodsforcrm",
                    new DefaultConsumer(channel) {
                        @Override
                        public void handleDelivery(String consumerTag,
                                                   Envelope envelope,
                                                   AMQP.BasicProperties properties,
                                                   byte[] body)
                                throws IOException
                        {
                            String routingKey = envelope.getRoutingKey();
                            String contentType = properties.getContentType();
                            long deliveryTag = envelope.getDeliveryTag();
                            // (process the message components here ...)

                            String str = new String(body, StandardCharsets.UTF_8);
                            System.out.println("receiving!"+str);
                            channel.basicAck(deliveryTag, false);
                        }
                    });


           while (true){
                Scanner in = new Scanner(System.in);
                System.out.println("Enter \"exit\" for quit");
                String input = in.nextLine();
                if (input.equals("exit")) {
                    System.exit(1);
                    channel.close();
                    conn.close();
                }
            }

        }

        catch (Exception e){
            e.printStackTrace();
        }
    }*/
}

}