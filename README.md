# Egen Coding Challenge - Level 2

### Problem Statement

The goal of this exercise is to build a system that works like an IoT platform – in this case, a personal weight

tracker. This system is responsible for,

* Consuming data sent from different sensors (emulators)

* Storing the received data in MongoDB

* Running the data through different rules to make basic predictions

Dependencies : [Java 7] (http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html "JDK 7") | [Maven] (https://maven.apache.org/download.cgi "Maven Build") | [Mongo DB] (https://docs.mongodb.com/ "Mongo DB") | [Morphia Api] (http://mongodb.github.io/morphia/ "Morphia Api") | [Easy Rules] (http://www.easyrules.org/ "Easy Rules") | [Sensor-Emulator] (https://github.com/egen/sensor-emulator "Sensor-Emulator")

### Steps to Run

1) Compile Project and generate jar
```
mvn clean package
```

2) Run Project
```
java -jar -Dbase.value=<base_weight> target/egen-be-challenge-0.0.1-SNAPSHOT.jar
```
Enter value for base_weight [Example- -Dbase.value=150]

3) Test Project
```
mvn test
```

### APIs Exposed

#### Metrics

- Stores the data that comes from sensor

1) Create

```
POST Request => http://localhost/metrics/create/
```

2) Read

```
GET Request => http://localhost/metrics/read/
```

3) Read by Range

```
GET Request => http://localhost/metrics/readRange/{startTime}/{endTime}
```


#### Alerts

- Stores the alerts that were created by the rules

1) Read

```
GET Request => http://localhost/alerts/read/
```

2) Read by Range

```
GET Request => http://localhost/alerts/readRange/{startTime}/{endTime}
```

### License

VP

