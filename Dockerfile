# 基于 openjdk:8-jre 为基础镜像进行构建
FROM java:8
# 进入这个容器的目录指定为/kagarino
WORKDIR /kagarino
# 将jar包放入/kagarino目录下，并重新命名为kagarino.jar
ADD ./target/webserver-0.0.1-SNAPSHOT.jar kagarino.jar
# 该项目使用的是8080端口，所有需要向外暴漏8080端口，日后才能通过映射的端口去访问这个暴漏的端口
EXPOSE 8080
# ENTRYPOINT 和 CMD 结合使用，ENTRYPOINT固定命令，CMD根据参数的不同 运行不同的jar包(动态参数)
ENTRYPOINT ["java","-jar","kagarino.jar"]
# 相当于 java -jar xxx.jar
