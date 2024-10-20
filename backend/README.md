# backend for vlsimple project

## how to build locally

### database setup

For the first time you're running the project in your environment, you'll need to setup the database prior to building the software.

If in Debian based OS or Mac, run:

`docker run --name postgres-db -e POSTGRES_PASSWORD=80901823093 -e POSTGRES_DB=media_player_db -p 5432:5432 -d postgres`

After running this command, you'll have the container in your machine and from now on you'll need only to run:

`docker start postgres-db`

### running the project with maven/spring-boot

Following up, you'll have to install all the dependencies with:

`mvn clean install -DskipTests`

And after installing all the dependencies once, run:

`mvn spring-boot:run`

## If it builds, great! If not, talk to emanuelle.



























