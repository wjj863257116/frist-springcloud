package boot.admin.config;


import boot.admin.secority.BootUserDetailsService;
import boot.admin.secority.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import sun.security.krb5.Config;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{



    @Bean
    public LoginSuccessHandler  loginSuccessHandler(){
        return new LoginSuccessHandler();
    }

    @Bean
    public UserDetailsService userDateilsService(){
        return new BootUserDetailsService();
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //springSecurity4.0后，默认开启了CSRD拦截
        //禁用 csrf（跨站请求伪造）
        http.csrf().disable().authorizeRequests()
                .antMatchers("/login").permitAll() //登录界面不需要认证
                .anyRequest().authenticated()  //其他界面都是需要认证
                .and()
                .formLogin().loginPage("/login")//配置登录界面路径，打开没有授权的页面会重定向到这个页面
                .failureUrl("/login?error")//登录失败后跳转的页面
                .successHandler(loginSuccessHandler())////登录成功后可使用loginSuccessHandler()存储用户信息，可选。
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .permitAll();

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/static/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDateilsService()).passwordEncoder(new BCryptPasswordEncoder());
    }

   /* @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("zhangsan").password("123").roles("USER"); }
*/

}
