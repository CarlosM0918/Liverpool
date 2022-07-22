FROM ubuntu:latest

WORKDIR /srv


ADD . /srv
RUN apt-get -y update
RUN apt-get install wget -y
RUN apt-get install zip -y
RUN apt-get install unzip -y
RUN apt-get install maven -y
RUN apt-get install curl -y
RUN apt-get install gnupg2 -y  && apt-get install gnupg -y


# RUN pip install -r requirements.txt


# Install chromedriver
RUN wget -N https://chromedriver.storage.googleapis.com/103.0.5060.53/chromedriver_linux64.zip -P ~/
RUN unzip ~/chromedriver_linux64.zip -d ~/
RUN rm ~/chromedriver_linux64.zip
RUN mv -f ~/chromedriver /usr/local/bin/chromedriver
RUN chown root:root /usr/local/bin/chromedriver
RUN chmod 0755 /usr/local/bin/chromedriver


# Install chrome broswer
RUN curl -sS -o - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add -
RUN echo "deb http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list
RUN apt-get -y update
RUN apt-get -y install google-chrome-stable

RUN mvn compile

CMD ["mvn", "clean", "test", "-P", "Lambda"]
