FROM openjdk:8

ARG brokerId=1

COPY kafka_2.11-0.10.1.0 /kafka

RUN sed -i s/localhost:2181/zookeeper:2181/g /kafka/config/server.properties && \
        sed -i s/broker.id=0/broker.id=${brokerId}/g /kafka/config/server.properties

EXPOSE 2181 9092