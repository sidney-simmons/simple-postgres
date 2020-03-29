# simple-postgres

simple-postgres is a gradle project meant to illustrate a basic PostgreSQL backed Spring Boot application.  Some of what's illustrated:

* PostgreSQL database
* Hibernate ORM
* Flyway database migration
* Transactions and roll backs upon failure
* Caching via Ehcache

## Usage

Clone the repository and execute the following.  The application runs on port 8001 and the database runs on port 5432.

```
// Uses the docker-compose.yml file to start the application and the database
./gradlew dockerComposeUp

// The application won't start successfully because the database needs to be configured
./gradlew flywayMigrate

// Restart the application by running UP again
./gradlew dockerComposeUp
```

You can view the service logs by running the following.

```
docker-compose logs -f
```

Hit some of the application endpoints by importing the Postman collection in the `postman-collection.json` file.

## License
[MIT](https://choosealicense.com/licenses/mit/)