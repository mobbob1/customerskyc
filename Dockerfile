FROM openjdk:11
COPY target/hahnassessment.war hahnassessment.war
ENTRYPOINT ["java", "-jar", "/hahnassessment.war"]