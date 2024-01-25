package kafka.consumer.per.partition;

import kafka.config.Configuration;
import kafka.config.CustomPartitioner;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Arrays;

public class Consumer2 {

    public static void main(String[] args) {
        // 1. Create kafka.consumer configuration
        var properties = Configuration.getConsumerProperties();

        // 2. Create kafka.consumer
        var consumer = new KafkaConsumer<String, String>(properties);

        // 3. Subscribe on topic(s)
        consumer.assign(Arrays.asList(new TopicPartition(Configuration.TOPIC, CustomPartitioner.MANAGERS_TO_PARTITION_ID.get("cdn21"))));

        // 4. Poll for new data
        while (true) {
            var records = consumer.poll(Duration.ofMillis(100));
            for (var record : records) {
                System.out.println(System.currentTimeMillis() + " Received new record" + " key: " + record.key() + " value: " + record.value() + " partition: " + record.partition());
            }
        }
    }
}
