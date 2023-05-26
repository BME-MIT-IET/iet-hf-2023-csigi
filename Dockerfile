FROM openjdk:17-oracle
RUN apt-get install maven
RUN mvn install
ADD vilagtalan_virologusok/target/vilagtalan_virologusok-1.0-SNAPSHOT.jar vilagtalan_virologusok.jar
EXPOSE 8010
ENTRYPOINT ["java", "-cp", "vilagtalan_virologusok.jar;forms_rt.jar", "Controller"]