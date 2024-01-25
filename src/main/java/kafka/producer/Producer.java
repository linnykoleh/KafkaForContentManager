package kafka.producer;

import kafka.config.Configuration;
import kafka.domain.Config;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class Producer {

    public static void main(String[] args) throws Exception {
        var properties = Configuration.getProducerProperties();

        // 2. Create the kafka.producer
        var producer = new KafkaProducer<String, String>(properties);

        while (true) {
            for (var manager : Configuration.MANAGERS) {
                // 3. Create producer record
                var key = manager;

                var config = Configuration.gson.toJson(new Config("Information for " + key, System.currentTimeMillis()));
                var producerRecord = new ProducerRecord<>(Configuration.TOPIC, key, config);

                // 4. Send data asynchronous
                producer.send(producerRecord, (metadata, exception) -> {
                    if (exception == null) {
                        System.out.println("Sent to" + " topic: " + metadata.topic() + " partition: " + metadata.partition() + " key " + key + " config: " + config);
                    } else {
                        System.out.println("Didn't sent: " + exception);
                    }
                });
                Thread.sleep(3000);
            }
        }

        // flush
//        producer.flush();

        // flush and close producer
//        producer.close();
    }
}
