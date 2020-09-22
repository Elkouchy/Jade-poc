FROM maven:3-jdk-8

MAINTAINER Zouhair EL KOUCHI <zouhair.elkouchi@gmail.com>

VOLUME /tmp

WORKDIR /app
COPY . /app
RUN mvn -v
RUN mvn clean install -DskipTests

  
ADD target/*.jar  jade-poc.jar


EXPOSE 8080
CMD ["java", "-jar", "jade-poc.jar"]

