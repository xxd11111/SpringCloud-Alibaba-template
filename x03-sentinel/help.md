### sentinel容错处理
    远程调用过程中，sneinel会限流熔断等，这时候调用方可以自己进行异常处理
```
服务提供方
@RestController
public class Demo2Controller {

    @GetMapping("/test1")
    public String test1() {
        return "这是服务2的接口" ;
    }

    @GetMapping("/test2")
    public String test2() throws Exception {
        throw new Exception();
    }
}
```
```
服务调用方
@RestController
public class Demo1Controller {
    @Resource
    DemoService demoService;

    @GetMapping("/test1")
    public String test1() {
        return demoService.test1();
    }
    @GetMapping("/test2")
    public String test2() {
        return demoService.test2();
    }
}

@Service
@FeignClient(name = "openFeign-server2", fallbackFactory = DemoServiceFallbackFactory.class)
public interface DemoService {
    //调用服务提供方的输出接口
    @GetMapping(value = "/test1")
    String test1();
    @GetMapping(value = "/test2")
    String test2();
}

public class DemoServiceFallback implements DemoService {
    private Throwable throwable;

    DemoServiceFallback(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public String test1() {
        return "test1调用错误:" + throwable.getMessage();
    }

    @Override
    public String test2() {
        return "test2调用错误:" + throwable.getMessage();
    }
}

@Component
public class DemoServiceFallbackFactory implements FallbackFactory<DemoServiceFallback> {
    @Override
    public DemoServiceFallback create(Throwable throwable) {
        return new DemoServiceFallback(throwable);
    }
}
```
    通过DemoServiceFallbackFactory注册和DemoServiceFallback实现异常处理