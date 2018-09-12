package amqp

import com.google.gson.GsonBuilder
import com.rabbitmq.client.AMQP
import com.rabbitmq.client.Channel
import com.rabbitmq.client.DefaultConsumer
import com.rabbitmq.client.Envelope
import model.ApiEndpoint
import model.Message
import okhttp3.Credentials
import rest.RestClient
import java.nio.charset.StandardCharsets

class Consumer(channel: Channel, val subscription: ApiEndpoint): DefaultConsumer(channel) {

    override fun handleDelivery(consumerTag: String?, envelope: Envelope?, properties: AMQP.BasicProperties?, body: ByteArray?) {

        val routingKey = envelope!!.routingKey
        val contentType = properties!!.contentType
        val deliveryTag = envelope.deliveryTag
        // (process the message components here ...)

        val bodyMessage = String(body!!, StandardCharsets.UTF_8)
        println("receiving!$bodyMessage")

        val client = RestClient.create(subscription.baseURL!!)
        val authToken = Credentials.basic(subscription.username, subscription.password)

        val gson   = GsonBuilder().create()
        val message = Message(routingKey, bodyMessage)
        val jsonString = gson.toJson(message)

        val call = client.sendMessage(authToken, jsonString)
        val result = call.execute()

        if (result.code()==200) channel.basicAck(deliveryTag, false)
    }
}