FROM ubuntu:latest
RUN apt-get update && apt-get clean
RUN apt-get install -q -y openjdk-17-jdk xvfb && apt-get clean

# Set display environment variable
ENV DISPLAY=:1

# Start Xvfb
CMD Xvfb :1 -screen 0 1024x768x16 &
ADD vilagtalan_virologusok/target/vilagtalan_virologusok-1.0-SNAPSHOT.jar vilagtalan_virologusok.jar
EXPOSE 8010
ENTRYPOINT ["java", "-Djava.awt.headless=false", "-cp", "vilagtalan_virologusok.jar:forms_rt.jar", "Controller"]