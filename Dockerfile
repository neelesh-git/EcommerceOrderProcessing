FROM openjdk:14
ADD target/OrderProcessing-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]