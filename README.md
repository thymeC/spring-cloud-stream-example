

```mermaid
flowchart LR
    start --> tr[[Topic-Raw]]
    tr --> Handler
    Handler --> t1[[Topic-Enrich-1]]
    Handler --> t2[[Topic-Enrich-2]]
    t1 --> Enricher-1
    t2 --> Enricher-2
    Enricher-1 --> t3[[Topic-Enriched]]
    Enricher-2 --> t3
    t3 --> RuleEngine
    RuleEngine --> e[end]
```

```bash
kafka-console-producer.sh --producer.config /opt/bitnami/kafka/config/producer.properties --bootstrap-server kafka:9092 --topic cloud-stream

kafka-console-consumer.sh --consumer.config /opt/bitnami/kafka/config/consumer.properties --bootstrap-server kafka:9092 --topic cloud-stream --from-beginning
```