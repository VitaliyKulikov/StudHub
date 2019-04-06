FROM gradle:jdk8 as builder
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
CMD gradle bootRun