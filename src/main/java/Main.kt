import com.rabbitmq.client.*

import java.io.IOException
import java.nio.charset.StandardCharsets

import common.getPreferencies

object Main {

    @JvmStatic
    fun main(args: Array<String>) {

        val preferencies = getPreferencies()

        val factory = ConnectionFactory()
        // "guest"/"guest" by default, limited to localhost connections
        factory.username = "dubrovin"
        factory.password = "1234567890"
        factory.virtualHost = "main"
        factory.host = "192.168.58.125"
        factory.port = 5672

        try {
            val conn = factory.newConnection()
            val channel = conn.createChannel()

            println("Connection success!")

            val shutdownHook = object : Thread("rabbitclient-shutdown-hook") {
                override fun run() {
                    println("Starting MyApp shutdown...")
                    // Do some cleanup work.

                    try {
                        channel.close()
                        conn.close()
                    } catch (e: Exception) {
                    }

                    println("MyApp shutdown complete.")

                }
            }
            Runtime.getRuntime().addShutdownHook(shutdownHook)

            val autoAck = false
            channel.basicConsume("goodsforwms", autoAck, "goodsforcrm",
                    object : DefaultConsumer(channel) {
                        @Throws(IOException::class)
                        override fun handleDelivery(consumerTag: String?,
                                                    envelope: Envelope?,
                                                    properties: AMQP.BasicProperties?,
                                                    body: ByteArray?) {
                            val routingKey = envelope!!.routingKey
                            val contentType = properties!!.contentType
                            val deliveryTag = envelope.deliveryTag
                            // (process the message components here ...)

                            val str = String(body!!, StandardCharsets.UTF_8)
                            println("receiving!$str")
                            channel.basicAck(deliveryTag, false)
                        }
                    })

        } catch (e: Exception) {
            println(e.message)
        }

    }

}