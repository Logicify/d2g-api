FROM ubuntu
RUN apt-get update
RUN apt-get install -y openjdk-8-jdk maven python python-pip
RUN pip install --upgrade --user awscli
ARG DB_URL
ARG DB_USERNAME
ARG DB_PASSWORD
ENV DB_URL ${DB_URL:-jdbc:postgresql://localhost:5432/d2g}
ENV DB_USERNAME ${DB_USERNAME:-d2g-api}
ENV DB_PASSWORD ${DB_PASSWORD:-roseweasel99}
RUN echo $DB_URL
COPY . /var/app/
WORKDIR /var/app/
RUN mvn clean package -Dliquibase.driver=org.postgresql.Driver -Dliquibase.url=$DB_URL -Dliquibase.username=$DB_USERNAME -Dliquibase.password=$DB_PASSWORD -Dliquibase.changeLogFile=src/main/resources/d2g_database/db.changelog-master.xml
EXPOSE 8080
CMD ["java", "-jar", "d2g-webapi/target/d2g-webapi-1.0-SNAPSHOT.jar"]