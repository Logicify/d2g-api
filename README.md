# d2g-api
# for building project create new maven configuration and insert next command line:
# clean package -Dliquibase.driver=org.postgresql.Driver -Dliquibase.url=jdbc:postgresql://localhost:5432/d2g -Dliquibase.username=d2g-api -Dliquibase.password=roseweasel99 -Dliquibase.changeLogFile=src/main/resources/d2g_database/db.changelog-master.xml
# for run create new jar application configuration and run d2g-api/d2g-webapi/target/d2g-webapi-1.0-SNAPSHOT.jar file
# if package not work on liquibase configuration make sure in correct liquibase changelogs. If they are correct drop LOCAL databases;

