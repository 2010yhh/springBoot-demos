package com.ctg.test.springbootswagger.swagger;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.FileReader;
import java.io.IOException;

/**
 * @Description: http://localhost:8090/swagger-ui.html
 * @Author: yanhonghai
 * @Date: 2018/9/28 10:07
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket() throws IOException, XmlPullParserException {
        MavenXpp3Reader mavenXpp3Reader = new MavenXpp3Reader();
        Model model = mavenXpp3Reader.read(new FileReader("pom.xml"));
        //构建api文档的详细信息函数
        ApiInfo apiInfo = new ApiInfoBuilder()
                //页面标题
                .title("springBoot测试使用Swagger2构建RESTful API")
                //创建人
                .contact(new Contact("yhh", "11@qq.com", ""))
                //版本号
                .version(model.getVersion())
                //描述
                .description("API 描述")
                .build();
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                // 当前包路径，可以过滤包名
                .apis(RequestHandlerSelectors.basePackage("com.ctg.test.springbootswagger.controller"))
                .paths(PathSelectors.any()).build();

    }
}
