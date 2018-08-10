FROM maven:3.3.9-jdk-8-alpine
RUN mkdir -p /app
COPY . /app
WORKDIR /app
RUN apk update && apk add mysql mysql-client
CMD java -jar target/*.jar
