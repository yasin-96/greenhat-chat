package com.thm.greenhat.greenhatchat.controller

import com.thm.greenhat.greenhatchat.exception.BadRequestException
import com.thm.greenhat.greenhatchat.model.Message
import com.thm.greenhat.greenhatchat.service.MessageService
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@CrossOrigin
@RestController
class MessageController(
        private val messageService: MessageService
)


{

    @GetMapping("/message/{id}")
    fun findById(@PathVariable id: String): Mono<Message> {
        return messageService.findMessageById(id)
                .switchIfEmpty(Mono.error(BadRequestException("There is no message with such an id")))
    }

    @PostMapping("/message")
    fun addMessage(@RequestBody message: Message){
        messageService.addMessage(message)
    }

    @GetMapping("{groupId}/message")
    fun findMessageByGroupId(@PathVariable groupId:String) : Flux<Message>{
        return messageService.findMessageByGroupId(groupId)
    }


}