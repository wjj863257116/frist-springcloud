package boot.controller;

import com.netflix.discovery.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;


@Controller
@RequestMapping("/gitconfig")
public class TestController {
    Logger logger= LoggerFactory.getLogger(getClass());
    @Value("${info.node}")
    private String node;

    @RequestMapping("/test")
    @ResponseBody
    public String gitConfigTest() throws UnsupportedEncodingException {
        String str=new String(node.getBytes(),"UTF-8");
        logger.info("测试数据："+str);
        return str;
    }
}
