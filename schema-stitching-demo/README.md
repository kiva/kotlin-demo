# Schema stitching demo

This is an ultra simple example mostly copied from https://github.com/stubailo/schema-stitching-demo

The best way to see it in action is to run:
```
npm install
npm start
```

I started building some docker support but would need to figure out how containers can talk to eachother over http. So this won't work right now:
```
docker build -t stitching-demo .
docker run -it --rm -p 3000:3000 stitching-demo
```

Then, open [localhost:3000/graphiql](http://localhost:3000/graphiql) in your web browser, and hit run on the query!

Essentially all this does it takes Kiva's graphql schema from dev, and combines it with the "Kiosa" graphql schema from localhost:8080 and exposes it at localhost:3000

One interesting thing we should think about is we have a Kiva graphql type called "Subscription" (ie a recurring deposit and lending product), but this is also a reserved word in graphql and gets exposed separately in the schema along side Query and Mutation.  