package kafka.consumer.per.group;

import kafka.config.Configuration;
import kafka.domain.Config;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;

import static kafka.config.Configuration.TOPIC;

public class Consumer3 {

    public static final String MANAGER = "cdn22";

    public static void main(String[] args) {
        // 1. Create kafka.consumer configuration
        var properties = Configuration.getConsumerProperties(MANAGER);

        // 2. Create kafka.consumer
        var consumer = new KafkaConsumer<String, String>(properties);

        // 3. Subscribe on topic(s)
        consumer.subscribe(Arrays.asList(TOPIC));

        // 4. Poll for new data
        while (true) {
            var records = consumer.poll(Duration.ofMillis(100));
            for (var record : records) {
                if (record.key().equals(MANAGER)) {
                    var config = Configuration.gson.fromJson(record.value(), Config.class);
                    System.out.println("Received new record" + " key: " + record.key() + " config: " + config + " partition: " + record.partition());
                } else {
                    System.err.println("not for " + MANAGER + " key: " + record.key());
                }
            }
        }
    }
}
