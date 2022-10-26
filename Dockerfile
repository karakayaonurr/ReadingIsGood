FROM openjdk:19
EXPOSE 1010
ADD target/getir-reading-is-good-project.jar getir-reading-is-good-project.jar
ENTRYPOINT ["java","-jar","getir-reading-is-good-project.jar"]