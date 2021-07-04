#FROM adoptopenjdk:14-jre-hotspot
#RUN mkdir /opt/app
#COPY target/locations-spring-solution-0.0.1-SNAPSHOT.jar /opt/app/locations.jar
#CMD ["java", "-jar", "/opt/app/locations.jar"]

FROM adoptopenjdk
WORKDIR /opt/app
COPY target/locations-spring-solution-0.0.1-SNAPSHOT.jar locations.jar
CMD ["java", "-jar", "locations.jar"]
#ENTRYPOINT ["java", "-jar", "locations.jar"]
