package me.ifight.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2 //开启springfox的Swagger2
@Configuration
//@Profile("dev") //只有在测试环境才开启Swaggerk
public class SwaggerConfig {
    //是否开启swagger，正式环境一般是需要关闭的，可根据springboot的多环境配置进行设置
    //若配置类上写了使用了@Profile 也可以达到类似效果 二选一 此处默认值为true
    @Value(value = "${swagger.enable:true}")
    private Boolean swaggerEnable;

    /**
     * 创建一个Docket 并且注册到Spring容器里即可完成配置
     *
     * @return Docket
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                //是否开启
                .enable(swaggerEnable)
                //设置API描述信息
                .apiInfo(apiInfo())
                .select()
                //扫描的的包 一般定位到controller那即可（若有接口层，此处报名需要注意）
                .apis(RequestHandlerSelectors.basePackage("me.ifight.controller"))
                // 指定路径处理：PathSelectors.any()代表所有的路径
                // 也可以指定某些接口不要对外暴露 这里定义一些规则就行 如正则表达式
                .paths(PathSelectors.any())
                .build()
                //base，最终调用接口后会和paths拼接在一起
                .pathMapping("/");
    }

    //设置API信息 一些简单的描述信息而已
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API Document for ifight")
                .description("API描述信息")
                .version("1.0.0")
                .build();
    }
}
