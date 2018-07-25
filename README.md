# kotlin-demo
Demo kotlin graphql server mocking out a bad blog

# Running locally

Try this (from your Mac, in the directory you want to house the code):

```
git clone git@github.com:kiva/kotlin-demo.git
cd kotlin-demo

./gradlew test
./gradlew bootRun
```

Then in your browser visit `http://localhost:8080/graphiql?query=%7B%0A%20%20version%0A%7D`

# Building and running Docker container locally

Make sure the app is not running locally as described above (or remap port bindings).
From the root directory of the application,

```
docker build -t kiva-demo .
docker run -it --rm -p 8080:8080 kiva-demo
```

Then in your browser visit `http://localhost:8080/graphiql?query=%7B%0A%20%20version%0A%7D`
