FROM java:8

RUN  \
  export DEBIAN_FRONTEND=noninteractive && \
  sed -i 's/# \(.*multiverse$\)/\1/g' /etc/apt/sources.list && \
  apt-get update && \
  apt-get -y upgrade && \
  apt-get install -y wget curl git maven

RUN mkdir /terminal-api
ADD . /terminal-api

WORKDIR /terminal-api

ENTRYPOINT mvn spring-boot:run
EXPOSE 8080