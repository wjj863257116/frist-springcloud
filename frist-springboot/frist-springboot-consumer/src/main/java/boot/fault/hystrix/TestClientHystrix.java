package boot.fault.hystrix;

import boot.fault.TestClient;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TestClientHystrix implements TestClient{


    public Object getDate() {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("code","系统出现错误");
        return map;

    }

    @Override
    public Object proTest(Map<String, Object> req) {
        return getDate();
    }
}
