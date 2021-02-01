package com.thm.greenhat.greenhatchat.service

import com.thm.greenhat.greenhatchat.model.Message
import com.thm.greenhat.greenhatchat.model.MessageToDisplay
import com.thm.greenhat.greenhatchat.model.User.UserToDisplay
import com.thm.greenhat.greenhatchat.repository.MessageRepository
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.support.GenericMessage
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux
import java.text.SimpleDateFormat
import java.util.*

@Service
class MessageService(
    private val userService: UserService,
    private val messageRepository: MessageRepository,
    private val kafkaTemplate: KafkaTemplate<String, String>,
) {
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
    private val topicName: String = "mytopic"

    /**
     *
     * @param id String
     * @return Mono<Message>
     */
    fun findMessageById(id: String): Mono<Message> {
        return messageRepository.findById(id)
    }

    /**
     *
     * @param message Message
     */
    fun addMessage(message: Message) {
        val headers: MutableMap<String, Any> = HashMap()
        headers[KafkaHeaders.TOPIC] = topicName
        kafkaTemplate.send(GenericMessage<Message>(message, headers))
    }

    /**
     *
     * @param groupId String
     * @return Mono<MutableList<MessageToDisplay>>
     */
    fun findMessageByGroupId(groupId: String): Mono<MutableList<MessageToDisplay>> {
        return messageRepository.findMessageByGroupId(groupId).collectList()
            .map { listOfMessages ->
                listOfMessages.toFlux().flatMap { oldMessage ->
                    userService.findById(oldMessage.userId).map {
                        MessageToDisplay(
                            oldMessage._id,
                            oldMessage.content,
                            sdf.format(oldMessage.created),
                            UserToDisplay(it.id, it.username, it.avatarPicture, it.avatarName),
                            oldMessage.groupId
                        )
                    }
                }.collectList().map { it }
            }.flatMap {
                it.map { it }
            }
    }
}
