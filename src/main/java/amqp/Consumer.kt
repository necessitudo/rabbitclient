package amqp

import com.rabbitmq.client.AMQP
import com.rabbitmq.client.Channel
import com.rabbitmq.client.DefaultConsumer
import com.rabbitmq.client.Envelope
import model.ApiEndpoint
import okhttp3.MediaType
import rest.NetworkHelper
import okhttp3.RequestBody



class Consumer(channel: Channel, val endpoint: ApiEndpoint, val networkHelper: NetworkHelper): DefaultConsumer(channel) {

    override fun handleDelivery(consumerTag: String?, envelope: Envelope?, properties: AMQP.BasicProperties?, body: ByteArray?) {

        /*val routingKey = envelope!!.routingKey
        val contentType = properties!!.contentType
        val deliveryTag = envelope.deliveryTag
        // (process the message components here ...)*/

        val bodyMessage = RequestBody.create(MediaType.parse("text/plain"), body)

        val call = networkHelper.client.sendMessage(networkHelper.authToken,envelope!!.exchange, envelope!!.routingKey,  bodyMessage)
        try {
           val result = call.execute()
           if (result.code()==200) channel.basicAck(envelope!!.deliveryTag, false)
        } catch (e: Exception){e.printStackTrace()}

    }
}