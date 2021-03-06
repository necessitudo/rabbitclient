package common

import com.google.gson.GsonBuilder
import com.rabbitmq.client.ConnectionFactory
import model.Config
import model.MainProperties
import java.io.File
import java.io.FileReader
import java.nio.file.Paths
import java.nio.file.Files

fun getConfig(appDirectory:String):model.Config{

    val pathToConfig = "$appDirectory${File.separator}conf${File.separator}properties.json"

    val gson   = GsonBuilder().create()
    return gson.fromJson(FileReader(pathToConfig), Config::class.java)

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





