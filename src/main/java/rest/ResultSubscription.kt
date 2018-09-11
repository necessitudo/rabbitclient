package rest

import amqp.Consumer
import com.google.gson.GsonBuilder
import com.rabbitmq.client.ConnectionFactory
import model.ApiEndpoint
import model.Message
import model.Result
import okhttp3.Credentials
import retrofit2.Response

class ResultSubscription(val factory: ConnectionFactory, val subscription: ApiEndpoint) : retrofit2.Callback<Result> {

    override fun onResponse(call: retrofit2.Call<Result>?, responce: Response<Result>?) {

        val client = Client.create(subscription.baseURL!!)
        val authToken = Credentials.basic(subscription.username, subscription.password)

        val gson   = GsonBuilder().create()
        val message = Message("crm", "string")
        val jsonString = gson.toJson(message)

        val call = client.sendMessage(authToken, jsonString)
        val results = call.execute()

        print("success")

        /*println("success")
        try {
            val conn = factory.newConnection()
            val channel = conn.createChannel()

            println("Connection success!")

            val autoAck = false
            channel.basicConsume("goodsforwms", autoAck, "goodsforcrm", Consumer(channel))

        } catch (e: Exception) {
            println(e.message)
        }*/

    }
    override fun onFailure(call: retrofit2.Call<Result>?, t: Throwable?) {
        println("fail")
    }
}