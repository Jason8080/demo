# 拉取amazoncorretto:8作为基础镜像
FROM 10.37.5.104:1180/library/java:8
# 作者
MAINTAINER gmlee
# 将本地文件夹挂在到当前容器
VOLUME /tmp
# 复制文件到容器
ADD target/k8s_app_demo.jar app.jar
# 时区设置
ENV TZ="Asia/Shanghai"
# 声明需要暴露的端口
EXPOSE 8080
# 配置容器启动后执行的命令
ENTRYPOINT ["sh", "-c", "java -jar $PARAMS -Djava.security.egd=file:/dev/urandom -jar app.jar $JAR_PARAMS"]
