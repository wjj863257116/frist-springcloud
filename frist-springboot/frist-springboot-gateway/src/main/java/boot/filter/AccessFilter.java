package boot.filter;

import boot.core.RedisService;
import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Component
public class AccessFilter extends ZuulFilter {
    private static final Logger logger = LoggerFactory.getLogger(AccessFilter.class);
    @Autowired
    private RedisService redisService;


    @Override
    //控制此Filter路由执行的顺序（pre 在被路由以前被执行，
    // error 在出现错误的时候被执行，port 在路由结束以后执行）
    public String filterType() {
        //类型为pre，请求在被路由前执行  //拦截进入该系统的所有现程
        return "pre";
    }

    @Override
    public int filterOrder() {
        //根据值类判断过滤器的执行顺序，类似于优先级权重
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        //过滤器的执行范围，类似拦截的路径范围
        return true;
    }


    //ZuulFilter 进入该类执行该方法来处理 Request 的数据（此类中要执行的代码）
    @Override
    public Object run() {

        RequestContext rct = RequestContext.getCurrentContext();

        HttpServletRequest request = rct.getRequest();

        logger.info("获得method：" + request.getMethod() + "获得Url:" + request.getRequestURL().toString());

        String agencyId = request.getParameter("agencyId");

        logger.info("商户名称：" + agencyId);

        redisService.setHash("frist", "test", "第一次使用redis");

        if (!agencyId.equals("2022900005")) {
            rct.setSendZuulResponse(false);
            String str = "调用被阻止运行";
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("code", "失败");
            map.put("msg", "接口调用失败");
            logger.info("转换为json类型：" + JSON.toJSONString(map));
            logger.info("将MAP打印：" + map.toString());
            try {
                rct.setResponseBody(new String(JSON.toJSONString(map).getBytes(), "ISO-8859-1"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            return null;
        }
        return null;
    }

}
