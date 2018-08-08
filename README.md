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

## Configuring JDK 8 in IntelliJ IDEA

If you had a newer version of the JDK installed when you booted IntelliJ IDEA, it may have automatically set your
default JDK to be a newer version, which may now be nonexistent after uninstallation as described above.

After managing your JDK versions as described above, it's probably a good idea to check that InelliJ IDEA knows which
JDK to use. To do so, open IntelliJ with this repository as the project folder, and navigate to
`File > Project Structure...`.

In the popup dialog, first navigate to the `Project Settings > Project` pane (typically selected by default). Here
change the `Project SDK` to be `1.8` from the menu, and set the `Project Language Level` to be
`8 - Lambdas, type annotations, etc` from the menu. Now navigate to the `Platform Settings > SDKs` pane. Here, you
should see `1.8` as an item on the list. If you see `9` or `10`, your life will probably be simpler if you just
delete them from the list by clicking the item to highlight, then clicking the `-` button at the top of the list.

If you don't see `1.8` in either of the panes above, it means that you either don't have JDK 1.8 installed (see the
previous section) or IntelliJ IDEA doesn't know you have JDK 1.8 installed (in which case, try restarting IntelliJ
IDEA).

## Compiling, testing, and running locally


Try this (from your Mac, in the directory you want to house the code):

```
git clone git@github.com:kiva/kotlin-demo.git
cd kotlin-demo

./gradlew test
./gradlew bootRun
```

Then in your browser visit `http://localhost:8080/graphiql?query=%7B%0A%20%20version%0A%7D`

# Install Docker on your Mac

Check if you already have Docker installed: ```docker -v```. If it's old (current is 18.*), uninstall first:
```
$ rm -f /usr/local/bin/docker
$ rm -f /usr/local/bin/docker-compose
$ rm -f /usr/local/bin/docker-machine
```
That will probably do it, but otherwise check out [this page](https://docs.docker.com/toolbox/toolbox_install_mac/#how-to-uninstall-toolbox).

Then [install Docker](https://www.docker.com/docker-mac). Launch the Docker app, and then check the command line tool was installed: in a new terminal window, ```docker -v``` should be something like 18.*.

# Building and running Docker container locally

Make sure the app is not running locally as described above (or remap port bindings).
From the root directory of the application,

```
docker build -t kiva-demo .
docker run -it --rm -p 8080:8080 kiva-demo
```

Then in your browser visit `http://localhost:8080/graphiql?query=%7B%0A%20%20version%0A%7D`

# Using Docker Compose to Spin Up a Dev Stack Locally

See https://github.com/kiva/kotlin-dev-env