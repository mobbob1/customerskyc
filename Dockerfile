FROM openjdk:11
COPY target/assessment.war assessment.war
ENTRYPOINT ["java", "-jar", "/assessment.war"]