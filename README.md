- Run kafka in docker
```bash
docker run -p 2181:2181 -p 9092:9092 --name kafka-docker-container --env ADVERTISED_HOST=127.0.0.1 --env ADVERTISED_PORT=9092 spotify/kafka
```

- Run kafka UI
```bash
docker run -it -p 8080:8080 -e DYNAMIC_CONFIG_ENABLED=true provectuslabs/kafka-ui
```

- Create a topic for the storage manager
```bash
bin/kafka-topics.sh --create --topic storage_manager --replication-factor 1  --partitions 3  --zookeeper localhost:2181
```

- List of topics
```bash
bin/kafka-topics.sh --list --zookeeper localhost:2181
```

- Describe a topic
```bash
bin/kafka-topics.sh --describe --topic storage-manager --zookeeper localhost:2181
```

- Adding partitions to a topic
```bash
bin/kafka-topics.sh --zookeeper localhost:2181 --alter --topic storage-manager --partitions 5
```