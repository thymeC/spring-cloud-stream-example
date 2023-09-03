package com.example.stream;

//@Configuration
//@RequiredArgsConstructor
public class KafkaConfig {

//    private final KafkaProperties properties;
//
//    @Bean
//    public KafkaAdmin KafkaAdmin() {
//        return new KafkaAdmin(properties.buildAdminProperties());
//    }
//
//    @Bean
//    NewTopic topic() {
//        return new NewTopic("cloud-stream", 1, (short) 1);
//    }
//
//    @Bean
//    public ConsumerFactory<String, String> consumerFactory() {
//        return new DefaultKafkaConsumerFactory<>(properties.buildConsumerProperties());
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, String> customListenerContainerFactory() {
//        var factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
//        factory.setConsumerFactory(consumerFactory());
//        return factory;
//    }
}