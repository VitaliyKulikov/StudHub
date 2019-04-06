FROM gradle:jdk8 as builder
USER root
WORKDIR /home/gradle/src

CMD gradle bootRun