package boot.admin.secority;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private final Logger logger = Logger.getLogger(getClass());




/*    @Autowired
    private AuthorityService authorityService;*/

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException
    {

     /*   LsysUser user = (LsysUser) authentication.getPrincipal();

        if (user == null) {
            throw new RuntimeException("获取登录用户失败！");
        }

        List<LsysResource> list=authorityService.getRoleResources("0",user.getRoleIds());
        if(CollectionUtils.isEmpty(list)){
            throw new RuntimeException("登录用户尚未分配任何资源，请联系管理员处理！");
        }
        user.setResources(list);
        request.getSession().setAttribute("user",user);
        //输出登录提示信息
        logger.info("" +
                "\n------------用户：" + user.getUsername() + "登录系统！" +
                "\n------------登录时间：" + new Date() + "" +
                "\n------------IP：" + getIpAddress(request) +
                "\n------------角色组：" + user.getRoleNames());*/
        logger.info("-------------登录成功！");
        Object object=authentication.getPrincipal();
        logger.info("-----------------"+object);
        super.onAuthenticationSuccess(request, response, authentication);
    }





    public String getIpAddress(HttpServletRequest request){

        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
