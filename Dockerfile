# 这里是引用的docker镜像, 我是maven项目所以是maven, 其他项目需要的镜像可以在dockerhub上找到
FROM maven

ARG mysql_itpm_password=${mysql_itpm_password}
ARG jwt_secret=${jwt_secret}

ENV CODE /code
ENV WORK /code/work
ENV mysql_itpm_password ${mysql_itpm_password}
ENV jwt_secret ${jwt_secret}
RUN mkdir -p $CODE \
    && mkdir -p $WORK

WORKDIR $WORK

# 这里将项目中./target/*.jar 复制到了 镜像里并命名为app.jar,  为什么是 ./target/*.jar , 因为 maven 打包后的文件就是在该路径, 如果是其他项目,填写对应路径 和名称就行了  
COPY ./target/*.jar app.jar
EXPOSE 8001
# 这是运行jar的命令,  如果是其他项目, 填写对应命令就行了
CMD java -jar app.jar
