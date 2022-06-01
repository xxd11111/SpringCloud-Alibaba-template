# sentinel帮助文档
## sentinel使用
### 环境
```
nacos-server（略，详看x01-nacos文档）

sentinel-dashbord 
下载地址 https://github.com/alibaba/Sentinel/releases/download/1.8.4/sentinel-dashboard-1.8.4.jar
输入 java -Dserver.port=8081 -Dcsp.sentinel.dashboard.server=localhost:8081 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard-1.8.4.jar
```
### 依赖
``` 
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
            
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>

        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
        </dependency>
```
### 使用
``` 
@RestController
public class Demo1Controller {

    @GetMapping("/test1")
    public String test1() {
        return "test1";
    }
}
@SpringBootApplication
public class SentinelApplication {
    public static void main(String[] args) {
        SpringApplication.run(SentinelApplication.class, args);
    }
}

配置文件
server:
  port: 9301

spring:
  application:
    name: service-consumer
  cloud:
    nacos:
      discovery:
        server-addr: 192.168.193.30:8848
    sentinel:
      transport:
        dashboard: 192.168.193.30:8081
        port: 8719
        client-ip: 192.168.193.31

feign:
  sentinel:
    enabled: true
```
    如果sentinel与服务不在同一台机器下，
    务必配置 client-ip: 192.168.193.31 , 否则无法监控相关服务
    进入浏览器控制台可以进行配置
    http://192.168.193.30:8081/
    进入控制台配置，内容较简单，自行探索
    调用接口 http://192.168.193.31:9301/test1 ,调用一次以后就可以在sentinel控制台看见相关信息