

```mermaid
flowchart LR
    start --> tr[[topic-raw]]
    tr --> SplitEnrich
    SplitEnrich --> te[[topic-enrich]]
    SplitEnrich --> te
    SplitEnrich --> te
    te --> FetchEnrich
    FetchEnrich --> ExternalSystem
    FetchEnrich --> t2[[topic-deliver]]
    t2 --> RunRule
    SplitEnrich <--> MongoDB
    FetchEnrich <--> MongoDB
    RunRule <--> MongoDB
    RunRule --> End
```

```bash
kafka-console-producer.sh --producer.config /opt/bitnami/kafka/config/producer.properties --bootstrap-server kafka:9092 --topic cloud-stream

kafka-console-consumer.sh --consumer.config /opt/bitnami/kafka/config/consumer.properties --bootstrap-server kafka:9092 --topic cloud-stream --from-beginning
```