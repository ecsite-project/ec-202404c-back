package com.example.security;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 全てのパスに対してCORSを設定する
                .allowedOrigins("*")  // 許可するオリジン、"*"はすべてのオリジンを許可することを意味します
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // 許可するHTTPメソッド
                .allowCredentials(true);  // 許可するクレデンシャル情報（Cookieや認証情報など）
    }
}
