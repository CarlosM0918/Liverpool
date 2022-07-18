FROM selenium/standalone-chrome:latest

User root

RUN apt-get update && apt-get -y install maven

COPY [".", "/usr/src/Liverpool"]

# USER root

#Update the OS ubuntu image
#RUN apt-get -y update
#
##Install packages
#RUN apt-get -y install git
#
#RUN apt -y install default-jdk
#
#WORKDIR /usr/src
#
#RUN git clone https://github.com/CarlosM0918/Liverpool.git
#
# WORKDIR /usr/src/Liverpool
#
#RUN chmod +x -R $(pwd) && ./mvnw clean test

# RUN ["mvn", "clean", "-DtestngFile='LocalSuite.xml'"]
