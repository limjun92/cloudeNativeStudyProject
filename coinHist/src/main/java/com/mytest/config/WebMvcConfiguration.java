package com.mytest.config;

//import com.ktds.devpro.common.interceptor.WebInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.*;



@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// 모든 uri에 대해 http://localhost:18080, http://localhost:8180 도메인은 접근을 허용한다.
		registry.addMapping("/**")
		.allowedOriginPatterns("*")
		.allowedHeaders("*")
		.allowedOrigins("http://localhost:8080","http://localhost:8081","http://localhost:8082", "http://localhost:8124" ,"http://14.63.222.111:8087", "http://localhost:8088", "http://ec2-15-165-15-254.ap-northeast-2.compute.amazonaws.com:8080")
		.allowedMethods(HttpMethod.GET.name(),HttpMethod.HEAD.name(),HttpMethod.POST.name(),HttpMethod.PUT.name(),HttpMethod.DELETE.name());
	}
}
