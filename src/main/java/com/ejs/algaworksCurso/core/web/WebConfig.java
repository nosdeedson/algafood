package com.ejs.algaworksCurso.core.web;

import javax.servlet.Filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	/* desnecessário de tiver alguma classe implementando CorFilter bean 
	 * e se existir  class WebSecurity extends WebSecurityConfigurerAdapter configurando http.cors().disable()
	 *  no método @Override protected void configure(HttpSecurity http)
	 * */
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/**")
//			.allowedHeaders("*")
//			.allowedMethods("*")
//			.allowedOrigins("*", "http://localhost:4200");
//	}
	
	@Bean
	public Filter shallowEtagHeaderFilter() {
		return new ShallowEtagHeaderFilter();
	}
}
