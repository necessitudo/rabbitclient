package rest

import amqp.Consumer
import com.rabbitmq.client.ConnectionFactory
import model.ApiEndpoint
import model.Result
import retrofit2.Response

class ResultSubscription(private val factory: ConnectionFactory, private val endpoint: ApiEndpoint, val networkHelper: NetworkHelper) : retrofit2.Callback<Result> {

    override fun onResponse(call: retrofit2.Call<Result>?, responce: Response<Result>?) {

        if (responce!!.code()!=200){
            throw Exception("Error on try connection(instance:${endpoint.name}):${responce.raw()}")
        }
        println("Success connection!!! (instance:${endpoint.name})")

        for(subscription in responce.body().items!!)
        {
            try {
                val channel = factory.newConnection().createChannel()

                channel.basicConsume(subscription.queue, networkHelper.autoAck, subscription.routingKey, Consumer(channel, endpoint, networkHelper))

                println("Success consume to $subscription. Ready to receive messages.")

            } catch (e: Exception) {
                println(e.message)
            }
        }



    }
    override fun onFailure(call: retrofit2.Call<Result>?, t: Throwable?) {
        println("Error on try connection(instance:${endpoint.name}):${t!!.message}")
    }
}