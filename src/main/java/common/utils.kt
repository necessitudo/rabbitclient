package common

import com.google.gson.GsonBuilder
import data.Config
import java.nio.file.Paths
import java.nio.file.Files

fun getPreferencies():data.Config{

    val gson = GsonBuilder().setPrettyPrinting().create()
    val path = Paths.get("C:\\Users\\inter\\IdeaProjects\\rabbitclient\\out\\starter\\conf\\properties.json")
    val fileData = String(Files.readAllBytes(path))
    val config =   gson.fromJson(fileData, Config::class.java)

    return config
}

