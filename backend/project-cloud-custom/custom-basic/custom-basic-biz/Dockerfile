FROM anapsix/alpine-java:8_server-jre_unlimited

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

ARG MODULE_NAME=""
ARG JASYPT_ENCRYPTOR_PASSWORD=""
ENV JAVA_OPTS=""

RUN mkdir -p /${MODULE_NAME}
WORKDIR /${MODULE_NAME}

EXPOSE 3333

# RUN wget https://arthas.aliyun.com/arthas-boot.jar
COPY --from=hengyunabc/arthas:latest /opt/arthas /opt/arthas
COPY target/${MODULE_NAME}*.jar app.jar

RUN echo -e "#!/bin/sh\njava ${JAVA_OPTS} -Djava.awt.headless=true -Djava.security.egd=file:/dev/./urandom -DJASYPT_ENCRYPTOR_PASSWORD=${JASYPT_ENCRYPTOR_PASSWORD} -Dsun.net.httpserver.nodelay=true -jar ./app.jar" > ./entrypoint.sh
RUN chmod +x ./entrypoint.sh

ENTRYPOINT ["./entrypoint.sh"]
