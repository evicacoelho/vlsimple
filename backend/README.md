# backend for vlsimple project

## how to build locally

### database setup

For the first time you're running the project in your environment, you'll need to setup the database prior to building the software.

If in Debian based OS or Mac, run:

`docker run --name postgres-db -e POSTGRES_PASSWORD=80901823093 -e POSTGRES_DB=vlsimple_db -p 5432:5432 -d postgres`

After running this command, you'll have the container in your machine and from now on you'll need only to run:

`docker start postgres-db`

To check if the db is ready:

`
`DOCKER_CONTAINER_NAME="postgres-db"`
`timeout 90s bash -c "until docker exec $DOCKER_CONTAINER_NAME pg_isready ; do sleep 5 ; done"`

### running the project with maven/spring-boot

Following up, you'll have to install all the dependencies with:

`mvn clean install -DskipTests`

And after installing all the dependencies once, run:

`mvn spring-boot:run`

### regarding liquibase:

If you made any new migration, please be assured to have your model and the migration propperlly reflected in the db.changelog-master.xml file.

After doing any change in any model and/or migration please flush liquibase by doing:

`mvn liquibase:clearCheckSums` and then `mvn liquebase:update`.


## If it builds, great! If not, talk to emanuelle.





























