# Builder stage needs JDK and gradle
FROM openjdk:8-jdk-alpine as builder
WORKDIR /root
# Copy gradle wrapper and configuration
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle
# Download dependencies only, speeding up later builds
RUN ./gradlew build -x :bootJar -x test --continue
# Now copy the actual project source in
COPY . .
# Build it for real this time
RUN ./gradlew build

# Runner stage only needs JRE and JAR
FROM openjdk:8-jre-alpine
WORKDIR /root
COPY --from=builder /root/build/libs/*.jar ./app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","./app.jar"]
