FROM openjdk:8-jdk-alpine
ARG usedb=h2
VOLUME /tmp
RUN mkdir /work
COPY . /work
RUN mv /work/src/main/resources/application.properties.${usedb} /work/src/main/resources/application.properties
WORKDIR /work
RUN /work/gradlew build
RUN mv /work/build/libs/*.jar /work/app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/work/app.jar"]
