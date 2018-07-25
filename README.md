# kotlin-demo
Demo kotlin graphql server mocking out a bad blog

# IntelliJ IDEA

If you want to work on the Kotlin, IntelliJ IDEA (https://www.jetbrains.com/idea/) is highly recommended.
There is a free community license, and we'll most likely be purchasing volume licenses
for the ultimate edition (which has a 30 day trial).

# Running locally

Dependency: JDK 8 (not 9 or 10). The easiest way for Mac is to download from Oracle directly (http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html).
At the current time it appears installing openJDK 8 (the flavor our Docker containers will run) is non-trivial,
although if you find a good path for this, let us know!

Try this (from your Mac, in the directory you want to house the code):

```
git clone git@github.com:kiva/kotlin-demo.git
cd kotlin-demo

./gradlew test
./gradlew bootRun
```

Then in your browser visit `http://localhost:8080/graphiql?query=%7B%0A%20%20version%0A%7D`

# Building and running Docker container locally

Dependency: You'll want to have Docker installed locally.

Make sure the app is not running locally as described above (or remap port bindings).
From the root directory of the application,

```
docker build -t kiva-demo .
docker run -it --rm -p 8080:8080 kiva-demo
```

Then in your browser visit `http://localhost:8080/graphiql?query=%7B%0A%20%20version%0A%7D`
