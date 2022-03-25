FROM openjdk:17-alpine
ENV HOME /app
COPY build/libs/app.jar $HOME/app.jar
EXPOSE 8081
CMD java -jar $HOME/app.jar