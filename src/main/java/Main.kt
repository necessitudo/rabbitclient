import com.rabbitmq.client.*

import java.io.IOException
import java.nio.charset.StandardCharsets

import common.getConfig
import common.byServer
import data.Result
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Credentials
import rest.Client
import retrofit2.Response

object Main {

    @JvmStatic
    fun main(args: Array<String>) {

        val config = getConfig()


        for (subscription in config.apiEndpoints!!) {
            val client = Client.create(subscription.baseURL!!)
            val authToken = Credentials.basic(subscription.username, subscription.password)
            val call = client.getSubscriptions(authToken)

            call.enqueue(object : retrofit2.Callback<Result> {

                override fun onResponse(call: retrofit2.Call<Result>?, responce: Response<Result>?) {

                    println("success")

                }
                override fun onFailure(call: retrofit2.Call<Result>?, t: Throwable?) {
                    println("fail")
                }

            })
        }

        val factory = ConnectionFactory().byServer(config.main!!)
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

