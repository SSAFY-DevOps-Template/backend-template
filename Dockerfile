# BUILD STAGE
FROM adoptopenjdk/openjdk11 AS build
WORKDIR /builder
COPY . .
RUN chmod +x gradlew
RUN ./gradlew bootJar

# RUN STAGE
FROM adoptopenjdk/openjdk11
WORKDIR /app
EXPOSE 8080
COPY --from=build /builder/build/libs/*.jar app.jar
ENTRYPOINT java -jar app.jar