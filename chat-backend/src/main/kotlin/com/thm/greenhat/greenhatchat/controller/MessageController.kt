package com.thm.greenhat.greenhatchat.controller

import com.thm.greenhat.greenhatchat.exception.BadRequestException
import com.thm.greenhat.greenhatchat.model.Message
import com.thm.greenhat.greenhatchat.model.MessageToDisplay
import com.thm.greenhat.greenhatchat.repository.MessageRepository
import com.thm.greenhat.greenhatchat.service.MessageService
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@CrossOrigin
@RestController
class MessageController(
    private val messageService: MessageService,
    private val messageRepository: MessageRepository
) {

    /**
     *
     * @param id String
     * @return Mono<Message>
     */
    @GetMapping("/message/id/{id}")
    fun findById(@PathVariable id: String): Mono<Message> {
        return messageService.findMessageById(id)
            .switchIfEmpty(Mono.error(BadRequestException("There is no message with such an id")))
    }

    @DeleteMapping("/del/{id}")
    fun deleteMessages(@PathVariable id:String) : Flux<Void> {
        return messageRepository.deleteMessageByUserId(id)
    }
    /**
     *
     * @param message Message
     */
    @PostMapping("/message/new")
    fun addMessage(@RequestBody message: Message) {
        messageService.addMessage(message)
    }

    /**
     *
     * @param groupId String
     * @return Mono<MutableList<MessageToDisplay>>
     */
    @GetMapping("/message/group/{groupId}")
    fun findMessageByGroupId(@PathVariable groupId: String): Mono<MutableList<MessageToDisplay>> {
        return messageService.findMessageByGroupId(groupId)
    }


}