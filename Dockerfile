FROM openjdk:11
VOLUME /tmp
EXPOSE 8081
ADD ./target/threatrest-0.0.1-SNAPSHOT.jar threatrest.jar
ENTRYPOINT ["java", "-jar", "threatrest.jar"]