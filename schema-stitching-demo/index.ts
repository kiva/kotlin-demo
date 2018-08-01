import * as express from 'express';
import * as bodyParser from 'body-parser';
import {graphqlExpress, graphiqlExpress} from 'apollo-server-express';
import {makeRemoteExecutableSchema, mergeSchemas, introspectSchema} from 'graphql-tools';
import {createApolloFetch,} from 'apollo-fetch';

async function run() {
	const createRemoteSchema = async (uri: string) => {
		const fetcher = createApolloFetch({uri});
		return makeRemoteExecutableSchema({
			schema: await introspectSchema(fetcher),
			fetcher
		});
	}
	// Need to figure out how to actually link docker containers 
	const kiosaSchema = await createRemoteSchema('http://127.0.0.1:8080/graphql')
	const kivaSchema = await createRemoteSchema('https://api.dev.kivaws.org/graphql');
	const schema = mergeSchemas({
		schemas: [kiosaSchema, kivaSchema]
	})

	const app = express();

	app.use('/graphql', bodyParser.json(), graphqlExpress({schema}));

	app.use(
		'/graphiql',
		graphiqlExpress({
			endpointURL: '/graphql',
			query: `{
  version
  hello
}`,
		})
	);

	app.listen(3000);
	console.log('Server running. Open http://localhost:3000/graphiql to run queries.');
}

try {
	run();
} catch (e) {
	console.log(e, e.message, e.stack);
}
