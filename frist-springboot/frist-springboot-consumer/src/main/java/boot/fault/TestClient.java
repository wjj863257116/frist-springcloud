package boot.fault;

import boot.fault.hystrix.TestClientHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
/**
 * FeifnClient 通过value 指定servetId(项目模块名称)进入相对应的路径模块，
 * fallback(springcolud 中的熔断器) 当熔断器处于短路的时候调用该指定的路径返回错误信息
 * 熔断器处于短路的原因：value所指定的路径不存在或者是所指定路径有错误
 * 当断路器处于断开的时候 在配置文件中会指定时间再次调用该路径下的模块，
 * 如果调通了此断路器恢复正常，没有调通继续保持短路，等待下一次测试是否正常
 * */
@FeignClient(value = "frist-springboot-provide-test",fallback =TestClientHystrix.class)
public interface TestClient {

    @RequestMapping(method = RequestMethod.POST,value = "/provide/test")
    Object proTest(Map<String,Object> req);
}
