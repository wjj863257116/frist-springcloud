package boot.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ResposeFiter extends ZuulFilter {

    private static final Logger logger= LoggerFactory.getLogger(ResposeFiter.class);
    @Override
    public String filterType() {
        //类型为 post ，在路由结束以后被执行（拦截）
        return "post";
    }

    @Override
    public int filterOrder() {
        //控制此类执行的顺序,数字越小优先权越大
        return 10;
    }

    @Override
    public boolean shouldFilter() {
        //过滤器的执行范围，类似拦截的路径范围
        //此Filter是否执行，返回true为执行，返回false为不执行
        return true;
    }


    //Zuul 执行的代码（filter具体的逻辑）
    @Override
    public Object run() {
        logger.info("路由结束调用该Filter");
        RequestContext request=RequestContext.getCurrentContext();



        return "";
    }
}
