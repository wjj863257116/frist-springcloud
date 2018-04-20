package boot.controller;

import boot.fault.TestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/apitest")
public class ConsumerTest {
  private static final Logger logger= LoggerFactory.getLogger(ConsumerTest.class);

  @Autowired
  private TestClient  testClient;

    @PostMapping(value ="/test")
    @ResponseBody
    public Object testConsumer(@RequestBody Map<String,Object> req){

        logger.info("测试进入此类 数据："+req.toString());
        return testClient.proTest(req);
    }
}
