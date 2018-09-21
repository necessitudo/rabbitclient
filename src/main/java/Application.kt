import com.rabbitmq.client.ConnectionFactory
import common.byServer
import common.getConfig
import okhttp3.Credentials
import rest.NetworkHelper
import rest.RestClient
import rest.ResultSubscription

class Application(val args: Array<String>) {

    fun start() {

        val config = getConfig()
        val factory = ConnectionFactory().byServer(config.main!!)

        for (endpoint in config.apiEndpoints!!) {
            val client = RestClient.create(endpoint.baseURL!!)
            val authToken = Credentials.basic(endpoint.username, endpoint.password)
            val call = client.getSubscriptions(authToken)

            call.enqueue(ResultSubscription(factory, endpoint,NetworkHelper(client,authToken)))
        }
    }

    fun stop() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}