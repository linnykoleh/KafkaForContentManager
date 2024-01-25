package kafka.config;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

public class CustomPartitioner implements Partitioner {

    public static final Map<String, Integer> MANAGERS_TO_PARTITION_ID = Map.of(
            "cdn10", 0,
            "cdn11", 1,
            "cdn21", 2,
            "cdn22", 3,
            "cdn30", 4
    );

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        return MANAGERS_TO_PARTITION_ID.getOrDefault(key.toString(), 0);
    }

    @Override
    public void close() {
        // nothing to do
    }

    @Override
    public void configure(Map<String, ?> configs) {
        // nothing to do
    }
}