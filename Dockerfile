# Dockerfile focused on development environment use case
FROM openjdk:8-jdk-alpine

# Prep image with gradle cache to use as mount point for host cache when container runs
RUN mkdir /root/.gradle
# Explicitly set home so container run of gradle uses right cache location
ENV HOME /root

# We can mount the host code repo here if we to update with every run, but the copy below makes mounting unnecessary
# if we only want the repo state at build time.
RUN mkdir /app

WORKDIR /app
COPY . .

# Running with gradle in dev environment to enable devtools features (debugging, auto reload)
CMD ["./gradlew", "bootRun"]
