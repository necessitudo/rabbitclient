package common

import com.google.gson.GsonBuilder
import com.rabbitmq.client.ConnectionFactory
import data.Config
import data.MainProperties
import java.nio.file.Paths
import java.nio.file.Files

fun getConfig():data.Config{

    val gson = GsonBuilder().setPrettyPrinting().create()
    val path = Paths.get("C:\\rabbitclient\\conf\\properties.json")
    val fileData = String(Files.readAllBytes(path))
    val config =   gson.fromJson(fileData, Config::class.java)

    return config
}


fun ConnectionFactory.byServer(main:MainProperties): ConnectionFactory {
    val factory = ConnectionFactory()
    // "guest"/"guest" by default, limited to localhost connections
    factory.host        = main.host
    factory.username    = main.username
    factory.password    = main.password
    factory.virtualHost = main.vHost
    factory.port        = main.port!!

    return factory
}

/*fun expectedQuit(){

    val shutdownHook = object : Thread("rabbitclient-shutdown-hook") {
        override fun run() {
            println("Starting MyApp shutdown...")
            // Do some cleanup work.

            try {
                channel.close()
                conn.close()
            } catch (e: Exception) {
            }

            println("MyApp shutdown complete.")

        }
    }
    Runtime.getRuntime().addShutdownHook(shutdownHook)
}*/





