FROM openjdk:17-oracle
ADD vilagtalan_virologusok/target/vilagtalan_virologusok-1.0-SNAPSHOT.jar vilagtalan_virologusok.jar
EXPOSE 8010
ENTRYPOINT ["java", "-jar","/vilagtalan_virologusok.jar"]