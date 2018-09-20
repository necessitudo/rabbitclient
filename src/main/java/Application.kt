import com.rabbitmq.client.ConnectionFactory
import common.byServer
import common.getConfig
import okhttp3.Credentials
import rest.RestClient
import rest.ResultSubscription

class Application(val args: Array<String>) {

    fun start() {

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

    fun stop() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}