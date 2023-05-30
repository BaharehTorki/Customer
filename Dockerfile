FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=build/libs/customer.jar
COPY ${JAR_FILE} customer.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/customer.jar"]