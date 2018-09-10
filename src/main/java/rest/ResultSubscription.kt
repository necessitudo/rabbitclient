package rest

import amqp.Consumer
import com.rabbitmq.client.ConnectionFactory
import data.Result
import retrofit2.Response

class ResultSubscription(val factory: ConnectionFactory) : retrofit2.Callback<Result> {

    override fun onResponse(call: retrofit2.Call<Result>?, responce: Response<Result>?) {

        println("success")
        try {
            val conn = factory.newConnection()
            val channel = conn.createChannel()

            println("Connection success!")

            val autoAck = false
            channel.basicConsume("goodsforwms", autoAck, "goodsforcrm", Consumer(channel))

        } catch (e: Exception) {
            println(e.message)
        }

    }
    override fun onFailure(call: retrofit2.Call<Result>?, t: Throwable?) {
        println("fail")
    }
}