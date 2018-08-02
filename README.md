# kotlin-demo
Demo kotlin graphql server mocking out a bad blog.

At some point this was based on https://spring.io/guides/tutorials/spring-boot-kotlin/ but then spun out to graphQL via https://blog.pusher.com/writing-graphql-service-using-kotlin-spring-boot/. Also it's very bad.

*Note:* Under the default configuration, data will not persist between runtime sessions.

# IntelliJ IDEA

If you want to work on the Kotlin, IntelliJ IDEA (https://www.jetbrains.com/idea/) is highly recommended.
There is a free community license, and we'll most likely be purchasing volume licenses
for the ultimate edition (which has a 30 day trial).

# Running locally

Dependency: JDK 8 (not 9 or 10). The easiest way for Mac is to download from Oracle directly (http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html).
At the current time it appears installing openJDK 8 (the flavor our Docker containers will run) is non-trivial,
although if you find a good path for this, let us know!

## Checking JDK Versions

On your Mac, you can check your installed JDKs from the command line:

```
ls /Library/Java/JavaVirtualMachines/
> 0 drwxr-xr-x  3 root  wheel    96B Jul 30 09:50 ./
> 0 drwxr-xr-x  4 root  wheel   128B Jan 18  2018 ../
> 0 drwxr-xr-x  3 root  wheel    96B Jul 21 10:17 jdk10.0.2.jdk/
> 0 drwxr-xr-x  3 root  wheel    96B Jul 17 18:43 jdk1.8.0_181.jdk/
```

From the output here, 'jdk10.0.2.jdk' means that I have a version of JDK 10 installed. On the other hand,
'jdk1.8.0_181.jdk' means that I have a version of JDK 8 installed. You can check the default JDK in use
via the command line:

```
/usr/libexec/java_home
> /Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home
```

If you see something other than a version of JDK 8 here, you won't be able to run compiled Kotlin code on the JVM. Probably
the most straightforward way of managing the JDK version is simply to remove any installed versions of JDK 9 or 10. (There is a way
to configure the JDK version for a Kotlin app via Gradle, but we do not know of a platform-independent way
of configuring this). To remove the version of JDK 10 in the example above, you can use the command line:

```
sudo rm -r /Library/Java/JavaVirtualMachines/jdk10.0.2.jdk/
```

We're always looking for improvements to the JDK version setup -- let us know if you have any suggestions!

## Compiling, testing, and running locally


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

# Using Docker Compose to Spin Up a Dev Stack Locally

See https://github.com/kiva/kotlin-dev-env