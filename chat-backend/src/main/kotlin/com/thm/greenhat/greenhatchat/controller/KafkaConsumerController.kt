package com.thm.greenhat.greenhatchat.controller

import com.google.gson.Gson
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.web.reactive.HandlerMapping
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter
import reactor.core.publisher.Sinks
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.thm.greenhat.greenhatchat.model.*
import com.thm.greenhat.greenhatchat.model.user.UserToDisplay
import com.thm.greenhat.greenhatchat.service.UserService
import org.springframework.web.reactive.socket.WebSocketSession
import java.text.SimpleDateFormat


@Configuration
@EnableWebFlux
class KafkaConsumerController(private val userService: UserService) {
    var gson = GsonBuilder().serializeNulls().create()
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");

    val mutableListOfString = object : TypeToken<MutableList<String>>() {}.type

    var onlineSessions = hashMapOf<String, WebSocketSession>()
    var userSessions = hashMapOf<String, String>()
    var groupInfo = hashMapOf<String, MutableSet<String>>()
    lateinit var tmpData: Message

    val sink = Sinks.many().multicast().onBackpressureBuffer<String>()

    /**
     *
     * @return WebSocketHandlerAdapter
     */
    @Bean
    fun wsHandlerAdapter() = WebSocketHandlerAdapter()

    /**
     *
     * @return HandlerMapping
     */
    @Bean
    fun handlerMapping(): HandlerMapping {
        return SimpleUrlHandlerMapping(
            mapOf("/fromKafka" to wsHandler()),
            -1
        )
    }

    /**
     *
     * @return WebSocketHandler
     */
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
                            val allGroupsFromUser: MutableList<String> =
                                Gson().fromJson(it.payloadAsText, mutableListOfString)
                            println(">> ${allGroupsFromUser.toString()}")
                            test = allGroupsFromUser.toString()
                            onlineSessions.put(session.id, session)

                            allGroupsFromUser.forEach { groupId ->
                                println("GID: ${groupId}")
                                // val check = groupInfo.keys.contains(groupId)


                                if (groupInfo.isNullOrEmpty()) {
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
                            println(e)
                        }
                    }
                )
                .then()
        };
    }


    /**
     *
     * @param message Message
     */
    @KafkaListener(topics = ["mytopic"], groupId = "test-consumer-group")
    fun receiveData(message: Message) {

        println("Message: ${message}")
        tmpData = message.copy()

        var changedMessage = createNewMessageWithUserInfo(message)

        println("New MSG: ${changedMessage}")
        println("Verbunde Clients: ${this.sink.currentSubscriberCount()}")

        this.sink.tryEmitNext(
            gson.toJson(changedMessage)
        )
    }

    /**
     * Create new Message with UserInfo
     * @param oldMessage Message
     * @return MessageToDisplay?
     */
    fun createNewMessageWithUserInfo(oldMessage: Message): MessageToDisplay? {
        return userService.findById(oldMessage.userId).map {
            var foundedUser = UserToDisplay(it.id, it.username, it.avatarPicture, it.avatarName)

            var newMessage = MessageToDisplay(
                oldMessage._id,
                oldMessage.content,
                sdf.format(oldMessage.created),
                foundedUser,
                oldMessage.groupId
            )

            newMessage
        }.block()
    }
}