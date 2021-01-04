package com.thm.greenhat.greenhatchat.configuration


import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer

@Configuration
class KafkaProducerConfig {

        private val kafkaServer: String = "localhost:9092"

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, String> {
        return KafkaTemplate(producerConfig())
    }

    @Bean
    fun producerConfig(): ProducerFactory<String, String> {
            // TODO Auto-generated method stub
            val config: MutableMap<String, Any?> = HashMap()
            config[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = kafkaServer
            config[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
            config[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = JsonSerializer::class.java
            //Uncomment the below if you want to send String instead of an Object through Kafka
            //config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
            return DefaultKafkaProducerFactory(config)
    }
}
