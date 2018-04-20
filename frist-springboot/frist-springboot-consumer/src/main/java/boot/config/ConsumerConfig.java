package boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class ConsumerConfig {

    @Bean
    public Docket testApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("test")
                .genericModelSubstitutes(DeferredResult.class)
                //.genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping("/")//api测试请求地址
                .select()
                .paths(PathSelectors.regex("/apitest/.*"))//过滤的接口
                .build()
                .apiInfo(testApiInfo());
    }

    private ApiInfo testApiInfo() {
        Contact contact = new Contact("徐春", "https://github.com/xuchun1987", "xuchun1987@yeah.net");
        ApiInfo apiInfo = new ApiInfo("微众银行接口在线API",//大标题
                "http协议，REST风格API",//小标题
                "0.1",//版本
                "",
                contact,//作者
                "主页",//链接显示文字
                ""//网站链接
        );
        return apiInfo;
    }
}
