# kotlin-demo
Demo kotlin graphql server mocking out a bad blog.

At some point this was based on https://spring.io/guides/tutorials/spring-boot-kotlin/ but then spun out to graphQL via https://blog.pusher.com/writing-graphql-service-using-kotlin-spring-boot/. Also it's very bad.

*Note:* Under the default configuration, data will not persist between runtime sessions.

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

## Setting up git on your Mac

Check your current git config on your Mac: ```git config --list```.
If you don't have values for user.name and user.email, set them:
```
git config --global user.name "Mona Lisa"
git config --global user.email "email@example.com"
```

Recommended: install
[bash git completion](https://github.com/bobthecow/git-flow-completion/wiki/Install-Bash-git-completion#os-x--macos)
for git auto-complete.

## Compiling, testing, and running locally

Try this (from your Mac, in the directory you want to house the code):

```
git clone git@github.com:kiva/kotlin-demo.git
cd kotlin-demo

./gradlew test
./gradlew bootRun
```

Then in your browser visit `http://localhost:8080/graphiql?query=%7B%0A%20%20version%0A%7D`

# IntelliJ IDEA

If you want to work on the Kotlin, IntelliJ IDEA (https://www.jetbrains.com/idea/) is highly recommended.
Install the Ultimate edition - it has a 30 day trial and we'll have licenses soon.

When you first open IntelliJ, accept the defaults and select to Open the directory where you cloned the
kotlin-demo repo.  Select "Enable Auto-Import" and make sure the "Use default Gradle wrapper" is selected. If IntelliJ
pops up a dialog asking you to enable auto-import, you can take that route.

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

## Connecting IntelliJ to our Settings Repository

In IntelliJ, go to ```File -> Settings Repository```, enter ```https://github.com/kiva/intellij-settings-repository```
for the Upstream URL, then click ```Overwrite Local```.

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

# Local Development Workflow

## Local Development via Native JVM in IntelliJ

Using the auto-import feature, as you did earlier, sets up a Spring Boot Run / Debug config powered by the Gradle
wrapper included in the repository. To see your options, look for "Play" and "Debug" icons in the upper right of your
window. You should also see a select menu with "BlogApplication" selected.

### Running the Spring Boot application natively in IntelliJ with Auto-Restart

Hitting the "Play" icon will run your Spring Boot application on your native JVM. To confirm it's working, test the graphql
endpoint by visiting `http://localhost:8080/graphiql?query=%7B%0A%20%20version%0A%7D` in your browser and running the version query.


Running the app in this way will enable auto-restart features
by default, meaning that when you make code changes and recompile them, the application will quickly auto restart, enabling
faster feedback. (See https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-devtools.html for more
info.) Note that you will need to not only save the updates to your source files, but also build the project to initiate
restart. You should see action on the Spring Boot app console when restart is triggered.

Note that we can configure the auto-restart behavior to trigger on every file save, but for now this seems excessive.

### Debugging the Spring Boot application natively in IntelliJ

Hitting the "Debug" icon in the Run / Debug tool will start the Spring Boot application in debug mode, and attach
IntelliJ's debugger to the app. Try starting in Debug mode, creating a breakpoint on Query.kt, and hitting the graphql
endpoint to see it in action!

## Local Development via Docker Container with IntelliJ

IntelliJ's Docker plugin is limited. It enables you to manage images and containers from a tool window in IntelliJ,
using Dockerfiles, Docker Compose configs, etc. However, the integration does not have any features specifically powering
Spring Boot development via Docker. Due to the limitations of the plugin, we'll demonstrate command line usage of Docker below.

First, make sure you have launched the Docker app on your Mac. To get started with the plugin, you can right-click the Dockerfile in the project directory listing, and select
"Run Dockerfile." You may need to download the Docker plugin and restart IntelliJ if the plugin was not previously
installed (when you open the Dockerfile to edit, IntelliJ will prompt you to install the Docker plugin).

### Running the Spring Boot application in Docker with Auto-Restart

We recommend using the included Docker Compose file via the command line to configure and boot the application for
development. From the root repository directory,

`docker-compose up`

This will boot the app in a Docker Container with auto-restart enabled, and your Mac's source code mounted as a volume
in the container. To confirm it's working, test the graphql endpoint by visiting `http://localhost:8080/graphiql?query=%7B%0A%20%20version%0A%7D` in your browser and running the version query.

As usual, the application auto-restart will not be triggered by simply saving a source file. Instead,
we recommend you open a second terminal window on your Mac. You'll need the container's name:

`docker container ls`

You should see something like `kotlin-demo_app_1` (make sure to look at the container name, not the image name). This
should match with the console output prefixes in the Docker Compose shell.

With the container name in hand, open a shell into the running container:

`docker exec -it kotlin-demo_app_1 sh`

Now you can run any gradle task in the container, like `./gradlew build` or `./gradlew test`. Either of these tasks will
compile any code changes, which should trigger the auto-restart behavior, which should show in the docker-compose console
output.

*Note:* A known issue with the current setup is that mounting the host's Gradle cache prevents the container from needing
to redownload Gradle and dependencies with every run. However, Gradle spawns daemons that appear to be incompatible
between the host and container (and possibly even incompatible between different container sessions). If we pursue this
setup further, we can improve this situation. The best move for now is to avoid alternating Gradle runs on the host and
the container, and just keep running Gradle exclusively in the container.

### Debugging the Spring Boot application in Docker

The only know current way to debug the Spring Boot application via Docker and IntelliJ is to use remote debugging. A
current limitation of this setup is that we cannot simultaneously enable auto-restart and debugging in a Docker
container. Instead, we need to run the container via differing methods. (If you want to brave Gradle forking and
figuring out how to attach the IntelliJ debugger to the Gradle fork that the actual auto-restart-enabled application is
running in, you can probably solve this. I wonder if setting the Debugger Model to listen might be a solution here?)

#### Setting Up Remote Debugging Configuration in IntelliJ

First, go to `Run > Edit Configurations...` Hit the `+` icon, and select `Remote`. In the dialog, name the configuration
(e.g., "Blog Remote"), and accept the defaults (namely: Host - localhost, Port - 5005, Transport - Socket, Debugger Mode - Attach).

Now, we need to run the container and override the usual entrypoint to avoid running the app with Gradle. From the root
repository directory,

`docker build -t kiva-demo .`

You shouldn't need to build again unless the Dockerfile changes. Now, run the container and override the Gradle entrypoint:

`docker run -it --rm -p 8080:8080 -p 5005:5005 -v "$(pwd)":/app -v $HOME/.gradle:/root/.gradle kiva-demo java -agentlib:jdwp=transport=dt_socket,address=5005,server=y,suspend=n -jar /app/build/libs/blog-0.0.1-SNAPSHOT.jar`

In addition to the docker-compose setup, this command also opens up port 5005 (used by the debugger), and overrides the
`./gradlew bootRun` entrypoint with a direct `java -jar` invocation that passes in command line args setting up the debugger.

##### Aside if this command doesn't work

Note that this method requires that the packaged JAR be available (i.e., Gradle build has been run at least once), and that
it be named `blog-0.0.1-SNAPSHOT.jar`. If Gradle has been run under default configuration, this should be the case. If
not, then we recommend first following the docker-compose instructions in the previous section to make sure the app is
built. Then check the path to the JAR as follows

`docker run -it --rm -v "$(pwd)":/app kiva-demo ls /app/build/libs`

You should be able to replace the JAR filename in the command above with the result of this listing.

##### Attaching IntelliJ debugger to the Docker app

Once you've got the container running in debug mode as above, you can attach the IntelliJ debugger by selecting your Remote
Run / Debug configuration you created and clicking the "Debug" icon. You should see IntelliJ report `Connected to the target VM, address: 'localhost:5005', transport: 'socket'` in the Debugger console.

Now, set a breakpoint (try it on the Query.kt definition for version), and hit that graphql endpoint!