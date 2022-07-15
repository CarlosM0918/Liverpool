# FROM selenium/standalone-chrome:latest

# USER root

# # COPY [".", "/usr/src/liverpool"]
# RUN apt update -y && apt upgrade -y && apt install git

# RUN cd /usr/src && git clone https://github.com/CarlosM0918/Liverpool.git



# WORKDIR /usr/src/liverpool

# RUN ./mvnw clean test
# FROM maven:latest

# COPY [".", "/usr/src/liverpool"]

# #Update the OS ubuntu image
# RUN apt-get -y update

# #Install packages
# RUN apt-get -y install firefox \
# && apt-get -y install vim

# WORKDIR /usr/src/liverpool

# CMD ["mvn", "clean", "test"]

FROM jlesage/firefox

# COPY [".", "/usr/src/liverpool"]

FROM maven:latest

#Update the OS ubuntu image
RUN apt-get -y update

#Install packages
RUN apt-get -y install firefox \
&& apt-get -y install git

WORKDIR /usr/src

RUN git clone https://github.com/CarlosM0918/Liverpool.git

WORKDIR /usr/src/Liverpool

RUN echo "dir $(pwd)"

# CMD ["cat", "pom.xml"]
# CMD [ "cd", "/usr/src/liverpool" ]

# RUN ["mvn", "clean", "test"]
 