FROM java:8-jdk-alpine
COPY ./target/c-r-hacienda-rosal-back-end-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "c-r-hacienda-rosal-back-end-0.0.1-SNAPSHOT.jar"]