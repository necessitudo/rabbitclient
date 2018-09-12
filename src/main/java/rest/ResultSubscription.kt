package rest

import amqp.Consumer
import com.google.gson.GsonBuilder
import com.rabbitmq.client.ConnectionFactory
import model.ApiEndpoint
import model.Message
import model.Result
import okhttp3.Credentials
import retrofit2.Response

class ResultSubscription(private val factory: ConnectionFactory, private val subscription: ApiEndpoint) : retrofit2.Callback<Result> {

    override fun onResponse(call: retrofit2.Call<Result>?, responce: Response<Result>?) {

        if (responce!!.code()!=200){
            throw Exception("Error on try connection(instance:${subscription.name}):${responce.raw()}")
        }
        println("Success connection(instance:${subscription.name})")

        try {
            val conn = factory.newConnection()
            val channel = conn.createChannel()

            println("Connection success!")

            val autoAck = false
            channel.basicConsume("goodsforwms", autoAck, "goodsforcrm", Consumer(channel, subscription))

        } catch (e: Exception) {
            println(e.message)
        }

    }
    override fun onFailure(call: retrofit2.Call<Result>?, t: Throwable?) {
        println("Error on try connection(instance:${subscription.name}):${t!!.message}")
    }
}