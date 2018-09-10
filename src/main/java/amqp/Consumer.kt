package amqp

import com.rabbitmq.client.AMQP
import com.rabbitmq.client.Channel
import com.rabbitmq.client.DefaultConsumer
import com.rabbitmq.client.Envelope
import okhttp3.Credentials
import rest.Client
import java.nio.charset.StandardCharsets

class Consumer(channel: Channel): DefaultConsumer(channel) {

    override fun handleDelivery(consumerTag: String?, envelope: Envelope?, properties: AMQP.BasicProperties?, body: ByteArray?) {

        val routingKey = envelope!!.routingKey
        val contentType = properties!!.contentType
        val deliveryTag = envelope.deliveryTag
        // (process the message components here ...)

        val str = String(body!!, StandardCharsets.UTF_8)
        println("receiving!$str")

        channel.basicAck(deliveryTag, false)

    }
}