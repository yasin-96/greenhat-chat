package com.thm.greenhat.greenhatchat

import org.json.JSONObject
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.client.RestTemplate
import javax.annotation.PostConstruct


@SpringBootApplication
class GreenhatChatApplication

fun main(args: Array<String>) {
	runApplication<GreenhatChatApplication>(*args)
	//makeFirstCall()
}

	fun makeFirstCall() {
		val headers = HttpHeaders()
		headers.contentType = MediaType.APPLICATION_JSON
		val jsonObject = JSONObject()
		jsonObject.put("name", "mongo-sink")
		val jsonobjectConfKeys = JSONObject()
		jsonobjectConfKeys.put("connector.class","com.mongodb.kafka.connect.MongoSinkConnector")
		jsonobjectConfKeys.put("tasks.max", "1")
		jsonobjectConfKeys.put("topics", "mytopic")
		jsonobjectConfKeys.put("connection.uri", "mongodb://root:example@localhost:27017/test")
		jsonobjectConfKeys.put("database","mydb")
		jsonobjectConfKeys.put("collection","messages")
		jsonobjectConfKeys.put("key.converter","org.apache.kafka.connect.json.JsonConverter" )
		jsonobjectConfKeys.put("value.converter", "org.apache.kafka.connect.json.JsonConverter")
		jsonobjectConfKeys.put("value.converter.schemas.enable","false")
		jsonObject.put("config",jsonobjectConfKeys)

		val request = HttpEntity<String>(jsonObject.toString(), headers)
		val uri = "http://localhost:8083/connectors"

		val restTemplate = RestTemplate()
		val result = restTemplate.postForObject(uri,request,String::class.java)

	}


