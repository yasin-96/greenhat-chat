package com.thm.greenhat.greenhatchat.controller

import com.google.gson.Gson
import com.thm.greenhat.greenhatchat.model.Message
//import kotlinx.serialization.*
//import kotlinx.serialization.json.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.reactive.HandlerMapping
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter
import reactor.core.publisher.Flux
import reactor.core.publisher.Sinks
import reactor.core.publisher.SynchronousSink
import reactor.kotlin.core.publisher.toFlux
import java.time.Duration
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.thm.greenhat.greenhatchat.model.GroupRequest
import com.thm.greenhat.greenhatchat.model.WSUserAndGRoupInfo
import org.springframework.boot.json.GsonJsonParser
import org.springframework.boot.web.servlet.server.Session
import org.springframework.web.reactive.socket.WebSocketMessage
import org.springframework.web.reactive.socket.WebSocketSession
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import reactor.util.function.Tuple3
import java.util.concurrent.ConcurrentHashMap


//@CrossOrigin
@Configuration
@EnableWebFlux
//@Controller
class KafkaConsumerController {
    val mutableListOfString = object : TypeToken<MutableList<String>>() {}.type
    companion object {
        var onlineSessions = hashMapOf<String, WebSocketSession>()
        var userSessions = hashMapOf<String, String>()
        var groupInfo = hashMapOf<String, MutableSet<String>>()
        lateinit var tmpData: Message
    }

    var sink = Sinks.many().multicast().onBackpressureBuffer<String>()

    @Bean
    fun wsHandlerAdapter() = WebSocketHandlerAdapter()

    @Bean
    fun handlerMapping(): HandlerMapping {
        return SimpleUrlHandlerMapping(
            mapOf("/fromKafka" to wsHandler()),
            -1
        )
    }

    fun wsHandler(): WebSocketHandler {
        return WebSocketHandler { session ->

            //TODO: ID aus der Groupe werfen
            //sessions.close()

            session.send(
                this.sink.asFlux().map {
                    println("Try to Send to Client")
                    val currentGroup = groupInfo.getValue(tmpData.groupId)
                    println("Current Group is : ${tmpData.groupId}")
                    println("GroupInfo: ${groupInfo.toString()}")

                    // val userID = userSessions.getValue(session.id)
                    println("Current Session = ${session.id}")
                    try {
                        if (currentGroup.contains(session.id)) {
                            session.textMessage(it)
                        } else {
                            session.textMessage("")
                        }
                    } catch (e: Exception) {
                        println(e)
                        session.textMessage("")
                    }
                }
            )
                .and(
                    session.receive().map {
                        println("Get Message from Client")
                        println("RAW: ${it.payloadAsText}")
                        var test = ""
                        try {
                            val allGroupsFromUser: MutableList<String> = Gson().fromJson(it.payloadAsText, mutableListOfString)
                            println(">> ${allGroupsFromUser.toString()}")
                            test = allGroupsFromUser.toString()
                            onlineSessions.put(session.id, session)
                            
                            allGroupsFromUser.forEach { groupId ->
                                println("GID: ${groupId}")
                                // val check = groupInfo.keys.contains(groupId)


                                if(groupInfo.isNullOrEmpty()) {
                                    groupInfo.put(groupId, mutableSetOf<String>(session.id))
                                } else {

                                    if (!groupInfo.keys.contains(groupId)) {
                                        groupInfo.put(groupId, mutableSetOf<String>(session.id))
                                    } else {
                                        groupInfo.getValue(groupId).add(session.id)
                                    }
                                }

                            }

                        } catch (e: Exception) {
                            println("Bin direk hier, ${test}")
                            println(e)
                        }
                    }
                )
                .then()
        };
    }


    @KafkaListener(topics = ["mytopic"], groupId = "test-consumer-group")
    fun receiveData(message: Message) {

        println("Message: ${message}")
        tmpData = message.copy()
        val gson = GsonBuilder().serializeNulls().create()

        println("Verbunde Clients: ${this.sink.currentSubscriberCount()}")

        this.sink.tryEmitNext(
            gson.toJson(message)
        )
    }
}