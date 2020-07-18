package com.cde.microprograming;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableCaching
public class InventoryManagementSystemApplication {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(InventoryManagementSystemApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(InventoryManagementSystemApplication.class, args);
		LOGGER.info("================================================>");
		LOGGER.error("-----------------------------------------");
	}

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.cde.microprograming")).build();
	}
	
	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
	   RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("localhost", 6379);
	   return new JedisConnectionFactory(redisStandaloneConfiguration);
	}

}
