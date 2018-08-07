# Dockerfile focused on development environment use case
FROM openjdk:8-jdk-alpine
WORKDIR /root
# Copy gradle wrapper and configuration
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle
# Download dependencies only, speeding up later builds
RUN ./gradlew build --no-daemon || return 0 # -x :bootJar -x test --continue
# Now copy the actual project source in
COPY . .
# Build it for real this time
RUN ./gradlew build
# Run with gradle bootRun to enable auto reload behavior
ENTRYPOINT ["./gradlew", "bootRun"]
