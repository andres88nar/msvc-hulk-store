######################################################################################################################
FROM openjdk:17-jdk-alpine as builder

WORKDIR /app/msvc-course

COPY ./pom.xml /app
COPY ./msvc-course/pom.xml .
COPY ./msvc-course/.mvn ./.mvn
COPY ./msvc-course/mvnw .

#RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
RUN ./mvnw dependency:go-offline

COPY ./msvc-course/src ./src
RUN ./mvnw clean package -DskipTests

######################################################################################################################
FROM openjdk:17-jdk-alpine as deploy

WORKDIR /app
RUN mkdir ./logs 

COPY --from=builder /app/msvc-course/target/msvc-course-0.0.1.jar .

ENV PORT=8002
EXPOSE $PORT

CMD [ "java", "-jar", "msvc-course-0.0.1.jar" ] 