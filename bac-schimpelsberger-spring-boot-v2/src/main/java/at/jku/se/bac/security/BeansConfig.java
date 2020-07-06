//package at.jku.se.bac.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class BeansConfig {
//
//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurer() {
//			@Override
//	        public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/**").allowedOrigins("http://localhost:3000","https://www.lakesforestsmountains.at")
//	            	.allowCredentials(true)
//	                .allowedMethods("GET","PUT","POST","DELETE","HEAD","OPTIONS");
//	        }
//		};
//	}
//}
