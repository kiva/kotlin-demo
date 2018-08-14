# kotlin-demo
Demo kotlin graphql server mocking out a bad blog.

At some point this was based on https://spring.io/guides/tutorials/spring-boot-kotlin/ but then spun out to graphQL via https://blog.pusher.com/writing-graphql-service-using-kotlin-spring-boot/. Also it's very bad.

*Note:* Under the default configuration, data will not persist between runtime sessions.

# Setting Up Your Developmet Environment

If you haven't yet, follow our [guide on Confluence](https://confluence.kiva.org/display/BRAIN/Kotlin+Developer+Setup) to set up 
your local development environment and install dependencies.

# Compiling, testing, and running locally

The app includes a gradle wrapper, so if you have a Kotlin-friendly JDK installed on your Java home, all you need to do is:

```
git clone git@github.com:kiva/kotlin-demo.git
cd kotlin-demo

./gradlew test
./gradlew bootRun
```

Then in your browser visit `http://localhost:8080/graphiql?query=%7B%0A%20%20version%0A%7D`

# Building and running Docker container locally

Make sure the app is not running locally as described above (or remap port bindings). You won't need
anything other than Docker Compose installed to make this work.

From the root directory of the application,

```
docker-compose build
docker-compose up
```

Then in your browser visit `http://localhost:8080/graphiql?query=%7B%0A%20%20version%0A%7D`

# Using Docker Compose to Spin Up a Dev Stack Locally

Want to see this app in action in the context of a UI server, a GraphQL schema aggregation server, and backed by a separate DB instance?

See https://github.com/kiva/kotlin-dev-env
