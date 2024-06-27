FROM gradle:7.6-jdk17 AS build

ARG PROJECT_DIR
WORKDIR /home/gradle/project

COPY .  /home/gradle/project/

RUN gradle war


# ステージ2: 実行環境
FROM tomcat:10.0-jdk17

COPY --from=build /home/gradle/project/build/libs/back.war /usr/local/tomcat/webapps/back.war

CMD ["catalina.sh", "run"]
