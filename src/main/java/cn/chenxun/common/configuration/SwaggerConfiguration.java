package cn.chenxun.common.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger配置
 *
 * @author chenxun
 * @date 2018/5/22
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    private final ProjectProperties projectProperties;

    @Value(value = "${swagger.enable}")
    private boolean swaggerEnable;

    @Autowired
    public SwaggerConfiguration(ProjectProperties projectProperties) {

        this.projectProperties = projectProperties;
    }

    @Bean
    public Docket api() {

        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        ticketPar.name("Authorization").description("user ticket")//Token 以及Authorization 为自定义的参数，session保存的名字是哪个就可以写成那个
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build(); //header中的ticket参数非必填，传空也可以
        pars.add(ticketPar.build());    //根据每个方法名也知道当前方法在设置什么参数
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ResponseBody.class))
                .apis(RequestHandlerSelectors.basePackage("cn.chenxun.sytemn"))
                .paths(PathSelectors.any())
                .build()
                .enable(swaggerEnable)
                .pathMapping("/").globalOperationParameters(pars);
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact(
                projectProperties.getAuthor().getName(),
                projectProperties.getAuthor().getUrl(),
                projectProperties.getAuthor().getEmail());
        return new ApiInfoBuilder()
                .title(projectProperties.getName())
                .description(projectProperties.getDescription())
                .version(projectProperties.getVersion())
                .contact(contact)
                .build();
    }

}
