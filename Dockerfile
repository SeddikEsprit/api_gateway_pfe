FROM openjdk:17
EXPOSE 8761
COPY target/*.jar api-gateway-prod-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/api-gateway-prod-0.0.1-SNAPSHOT.jar"]
