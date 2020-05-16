# simple-postgres

simple-postgres is a gradle project meant to illustrate a basic PostgreSQL backed Spring Boot application.  Some of what's illustrated:

* PostgreSQL database
* Hibernate ORM
* Manual flyway database migration via gradle
* Automatic flyway database migration via docker container
* Transactions and roll backs upon failure
* Caching via Ehcache

## Usage

Clone the repository and execute the following.  The application runs on port 8001 and the database runs on port 5432.

```
// Uses the docker-compose.yml file to start the application, database, and flyway migration
./gradlew dockerComposeUp

// You can also run flyway manually via gradle
./gradlew flywayInfo
```

You can view the service logs by running the following.

```
docker-compose logs -f
```

Hit some of the application endpoints by importing the Postman collection in the `postman-collection.json` file.

## License
[MIT](https://choosealicense.com/licenses/mit/)