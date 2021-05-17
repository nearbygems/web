package kz.bigdata.web.config.kafka;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ProducerConfiguration {

  // region Autowired fields
  @Autowired
  private KafkaConfig kafkaConfig;
  // endregion

  @Bean
  public ProducerFactory<String, byte[]> producerFactory() {
    return new DefaultKafkaProducerFactory<>(producerConfigurations());
  }

  @Bean
  public Map<String, Object> producerConfigurations() {
    var configurations = new HashMap<String, Object>();
    configurations.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfig.host() + ":" + kafkaConfig.port());
    configurations.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    configurations.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class);
    return configurations;
  }

  @Bean
  public KafkaTemplate<String, byte[]> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }

}
