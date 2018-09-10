import com.rabbitmq.client.*

import java.io.IOException
import java.nio.charset.StandardCharsets

import common.getConfig
import common.byServer
import okhttp3.Credentials
import rest.Client
import rest.ResultSubscription

object Main {

    @JvmStatic
    fun main(args: Array<String>) {

        val config = getConfig()
        val factory = ConnectionFactory().byServer(config.main!!)

        for (subscription in config.apiEndpoints!!) {
            val client = Client.create(subscription.baseURL!!)
            val authToken = Credentials.basic(subscription.username, subscription.password)
            val call = client.getSubscriptions(authToken)

            call.enqueue(ResultSubscription(factory))
        }

    }

}

