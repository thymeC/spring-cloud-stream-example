

```mermaid
flowchart LR
    start --> tr[[Topic-Raw]]
    tr --> FusFunction
    FusFunction --> t1[[Topic-Enrich]]
    FusFunction --> t1
    FusFunction --> t1
    t1 --> RoFunction
    RoFunction --> ExternalSystem
    RoFunction --> t2[[Topic-Deliver]]
    t2 --> DahFunction
    FusFunction <--> MongoDB
    RoFunction <--> MongoDB
    DahFunction <--> MongoDB
    DahFunction --> End
```

```bash
kafka-console-producer.sh --producer.config /opt/bitnami/kafka/config/producer.properties --bootstrap-server kafka:9092 --topic cloud-stream

kafka-console-consumer.sh --consumer.config /opt/bitnami/kafka/config/consumer.properties --bootstrap-server kafka:9092 --topic cloud-stream --from-beginning
```