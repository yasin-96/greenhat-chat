package com.thm.greenhat.greenhatchat.configuration

import com.thm.greenhat.greenhatchat.model.Message
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.config.KafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer
import org.springframework.kafka.support.serializer.JsonDeserializer


@Configuration
class KafkaConsumerConfig {

    private val kafkaServer: String = "localhost:9092"
    private val kafkaGroupId : String = "test-consumer-group"

    @Bean
    fun consumerConfig(): ConsumerFactory<String?, Message?>{
        // TODO Auto-generated method stub
        val config: MutableMap<String, Any> = HashMap()
        config[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = kafkaServer
        config[ConsumerConfig.GROUP_ID_CONFIG] = kafkaGroupId
        config[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        config[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = JsonDeserializer::class.java
        return DefaultKafkaConsumerFactory<String, Message>(config, null, JsonDeserializer<Message>(Message::class.java))
    }

    @Bean
    fun kafkaListenerContainerFactory(): KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String?, Message?>>? {
        val listener: ConcurrentKafkaListenerContainerFactory<String, Message> = ConcurrentKafkaListenerContainerFactory<String, Message>()
        listener.consumerFactory = consumerConfig()
        return listener
    }

}