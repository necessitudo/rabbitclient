import com.rabbitmq.client.*

import common.getConfig
import common.byServer
import okhttp3.Credentials
import rest.RestClient
import rest.ResultSubscription

object Main {

    @JvmStatic
    fun main(args: Array<String>) {

        println("start1")

        val config = getConfig()
        val factory = ConnectionFactory().byServer(config.main!!)

        for (subscription in config.apiEndpoints!!) {
            val client = RestClient.create(subscription.baseURL!!)
            val authToken = Credentials.basic(subscription.username, subscription.password)
            val call = client.getSubscriptions(authToken)

            call.enqueue(ResultSubscription(factory, subscription))
        }

    }

}

