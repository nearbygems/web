package kz.bigdata.web.config.kafka;

import kz.bigdata.web.util.KafkaUtil;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ConsumerConfiguration {

  // region Autowired fields
  @Autowired
  private KafkaConfig kafkaConfig;
  // endregion

  @Bean
  public ConsumerFactory<String, byte[]> consumerFactory() {
    return new DefaultKafkaConsumerFactory<>(consumerConfigurations());
  }

  @Bean
  public Map<String, Object> consumerConfigurations() {
    var configurations = new HashMap<String, Object>();
    configurations.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfig.host() + ":" + kafkaConfig.port());
    configurations.put(ConsumerConfig.GROUP_ID_CONFIG, KafkaUtil.GROUP_ID);
    configurations.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    configurations.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ByteArrayDeserializer.class);
    return configurations;
  }

  @Bean
  ConcurrentKafkaListenerContainerFactory<String, byte[]> kafkaListenerContainerFactory() {
    var factory = new ConcurrentKafkaListenerContainerFactory<String, byte[]>();
    factory.setConsumerFactory(consumerFactory());
    return factory;
  }

}