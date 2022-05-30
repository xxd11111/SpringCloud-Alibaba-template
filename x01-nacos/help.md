# nacos-discover帮助文档

## 环境安装

### 单体部署（linux）

````
1.下载nacos-server-2.1.0
下载地址 
官网 https://github.com/alibaba/nacos/releases
linux https://github.com/alibaba/nacos/releases/download/2.1.0/nacos-server-2.1.0.tar.gz
windows https://github.com/alibaba/nacos/releases/download/2.1.0/nacos-server-2.1.0.zip

2.下载后解压文件 

3.进入解压文件的bin/conf，修改配置文件application.properties，配置数据库mysql
    # db mysql
    spring.datasource.platform=mysql
    db.num=1
    db.url.0=jdbc:mysql://localhost:3306/ry-config?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useUnicode=true&useSSL=false&serverTimezone=UTC
    db.user=root
    db.password=123456

4.进入bin，单体模式启动
startup.sh -m standalone

5.进入控制台
http://127.0.0.1:8848/nacos
用户名nacos
密码nacos

其余方式自行探索
docker，docker-compose，集群等

````

## 服务发现-server-discover项目

### 添加依赖

```  
        父级声明依赖
        <spring-cloud.version>2021.0.1</spring-cloud.version>
        <spring-cloud-alibaba.version>2021.0.1.0</spring-cloud-alibaba.version>
        <spring-boot.version>2.6.7</spring-boot.version>
          <!-- SpringCloud 微服务 -->
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>${spring-cloud.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>

            <!-- SpringCloud Alibaba 微服务 -->
            <dependency>
                <groupId>com.alibaba.cloud</groupId>
                <artifactId>spring-cloud-alibaba-dependencies</artifactId>
                <version>${spring-cloud-alibaba.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>

            <!-- SpringBoot 依赖配置 -->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>${spring-boot.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            
        子项目需要依赖
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>
        
        详情看项目内依赖关系，服务发现最简依赖
        spring-cloud-starter-alibaba-nacos-discovery是需要依赖cloud的注解，所以这两个是需要同时存在
        springboot的web-start就不谈了
```

### 启动项目
    进入http://192.168.193.30:8848/nacos 输入用户名nacos，密码nacos
    进入服务管理-服务列表，发现没有服务
    server-discover项目启动后，再次刷新，会发现多了一个服务

## server-服务注册
### 添加依赖
```
    最简依赖
    父依赖
        <spring-cloud.version>2021.0.1</spring-cloud.version>
        <spring-cloud-alibaba.version>2021.0.1.0</spring-cloud-alibaba.version>
        <spring-boot.version>2.6.7</spring-boot.version>
          <!-- SpringCloud 微服务 -->
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>${spring-cloud.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>

            <!-- SpringCloud Alibaba 微服务 -->
            <dependency>
                <groupId>com.alibaba.cloud</groupId>
                <artifactId>spring-cloud-alibaba-dependencies</artifactId>
                <version>${spring-cloud-alibaba.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>

            <!-- SpringBoot 依赖配置 -->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>${spring-boot.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        
        server-config依赖
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-web</artifactId>
            </dependency>
    
            <dependency>
                <groupId>com.alibaba.cloud</groupId>
                <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
            </dependency>
        
        该为服务配置最简依赖，可以使用nacos-config功能，从配置文件读值
```
### 添加配置文件
    进入nacos http://192.168.193.30:8848/nacos
    进入 配置管理 配置列表
    新增配置 DateId 填入 相关服务名  我的填入是 server-config.proterties,并勾选配置格式 proterties
    内容
    user.name = xxx
    user.age = 18  
    保存

### 启动服务
    使用@RefreshScope
    配合@Value("${user.name}")
    便可以动态获取  nacos上相对应的 配置文件内的属性值



