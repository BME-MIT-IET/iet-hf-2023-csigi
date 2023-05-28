FROM maven:3.8-openjdk-17-slim
WORKDIR vilagtalan_virologusok/vilagtalan_virologusok
COPY . /vilagtalan_virologusok
RUN mvn clean install
ENTRYPOINT ["mvn", "exec:java", "-Dexec.mainClass=\"Controller\""