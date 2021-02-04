package com.thm.greenhat.greenhatchat

import org.json.JSONObject
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.client.RestTemplate

@SpringBootApplication
class GreenhatChatApplication
//TODO: auslagern
private const val uri = "http://localhost:8083/connectors"

/**
 *
 * @param args Array<String>
 */
fun main(args: Array<String>) {
    runApplication<GreenhatChatApplication>(*args)

    if (!checkIfConnectorExist()) {
        makeFirstCall()
    }
}






/**
 *
 */
//TODO: auslagern
fun makeFirstCall() {
    val headers = HttpHeaders()
    headers.contentType = MediaType.APPLICATION_JSON
    val jsonObject = JSONObject()
    jsonObject.put("name", "mongo-sink")
    val jsonobjectConfKeys = JSONObject()
    jsonobjectConfKeys.put("connector.class", "com.mongodb.kafka.connect.MongoSinkConnector")
    jsonobjectConfKeys.put("tasks.max", "1")
    jsonobjectConfKeys.put("topics", "mytopic")
    jsonobjectConfKeys.put("connection.uri", "mongodb://root:example@mongo-db:27017/")
    jsonobjectConfKeys.put("database", "mydb")
    jsonobjectConfKeys.put("collection", "messages")
    jsonobjectConfKeys.put("key.converter", "org.apache.kafka.connect.storage.StringConverter")
    jsonobjectConfKeys.put("value.converter", "org.apache.kafka.connect.json.JsonConverter")
    jsonobjectConfKeys.put("value.converter.schemas.enable", "false")
    jsonObject.put("config", jsonobjectConfKeys)

    val request = HttpEntity<String>(jsonObject.toString(), headers)
    val restTemplate = RestTemplate()
    restTemplate.postForObject(uri, request, String::class.java)

}

/**
 *
 * @return Boolean
 */
fun checkIfConnectorExist(): Boolean {
    val restTemplate = RestTemplate()
    val result = restTemplate.getForObject(uri, String::class.java)
    if (result!!.length < 3) return false
    return true
}
