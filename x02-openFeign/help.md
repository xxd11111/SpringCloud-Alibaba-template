# 远程调用

## springboot的RestTemplate
### 调用方和服务提供方 项目依赖
```
    几乎没有什么依赖，只需要springboot最基本的就行了
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
            
```
### 配置类
```
@Configuration
public class RestTemplateConfig {
    /**
     * 没有实例化RestTemplate时，初始化RestTemplate
     * @return
     */
    @ConditionalOnMissingBean(RestTemplate.class)
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
```

### 使用
``` 
    提供方
    @RestController
    public class DemoController {
        @GetMapping()
        public String test(){
            return "这是server2的信息";
        }
    }
    调用方
    @Resource
    private RestTemplate restTemplate;
    @GetMapping()
    public String test(){
        return restTemplate.getForObject("http://localhost:9202/", String.class);
    }
```

## openFeign使用
### 调用方依赖
    一般搭配nacos服务发现使用，这样只需要指定服务名和rest api就可以使用了，避免了查询ip的过程

```
父依赖
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
子依赖
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-loadbalancer</artifactId>
        </dependency>
        
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>
```
    spring-cloud-starter-openfeign 
    spring-cloud-starter-loadbalancer
    这两个是openFeign使用的所需依赖
    spring-cloud-starter-alibaba-nacos-discovery 
    调用方注册到nacos中，从nacos中获取相关信息

### 服务提供方依赖
``` 
    父依赖相同
    子依赖
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>
```
    spring-cloud-starter-alibaba-nacos-discovery
    服务提供方只需要注册到nacos中就行了

### 正常使用
``` 
提供方启动类
@SpringBootApplication
public class OpenFeign2Application {
    public static void main(String[] args) {
        SpringApplication.run(OpenFeign2Application.class, args);
    }
}
提供方对外接口
@RestController
public class Demo2Controller {
    @GetMapping("/test")
    public String test() {
        return "这是服务2的接口" ;
    }
}
``` 

``` 
调用方启动类，需要@EnableFeignClients注解
@SpringBootApplication
@EnableFeignClients
public class OpenFeign1Application {
    public static void main(String[] args) {
        SpringApplication.run(OpenFeign1Application.class, args);
    }
}

调用方服务类，远程调用其他服务， "openFeign-server2"服务名
@Service
@FeignClient(name = "openFeign-server2")
public interface DemoService {
    //调用服务提供方的输出接口
    @GetMapping(value = "/test")
    String test();
}

调用方对外接口
@RestController
public class Demo1Controller {
    @Resource
    DemoService demoService;

    @GetMapping("/test2")
    public String test() {
        return demoService.test();
    }
}
```