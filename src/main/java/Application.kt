import com.rabbitmq.client.ConnectionFactory
import common.byServer
import common.getConfig
import okhttp3.Credentials
import org.apache.log4j.BasicConfigurator
import rest.NetworkHelper
import rest.RestClient
import rest.ResultSubscription

class Application(val args: Array<String>) {

    fun start() {

        BasicConfigurator.configure();

        if(args.size!=1) {
            println("Bad arguments - path to app's home not found")
            stop()
        }

        val config = getConfig(args!!.get(0))
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