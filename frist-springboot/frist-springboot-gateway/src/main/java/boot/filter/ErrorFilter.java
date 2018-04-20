package boot.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class ErrorFilter extends ZuulFilter {


    public ErrorFilter(){
        super();
    }
    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return 9;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext  rct=RequestContext.getCurrentContext();
        Throwable throwable=rct.getThrowable();
        return null;
    }
}
