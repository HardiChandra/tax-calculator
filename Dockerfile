FROM gradle:5.5.0-jdk8
EXPOSE 8080
COPY --chown=gradle:gradle . /home/gradle
WORKDIR /home/gradle
RUN gradle build
ENTRYPOINT ["java", "-jar", "./build/libs/tax-calculator-1.0.0-SNAPSHOT.jar"]