package com.itranswarp.learnjava;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.itranswarp.learnjava.service.User;
import com.itranswarp.learnjava.service.UserService;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan
@PropertySource("jdbc.properties")
public class AppConfig {



	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		UserService userService = context.getBean(UserService.class);
//		userService.register("bob@example.com", "password1", "Bob");
//		userService.register("alice@example.com", "password2", "Alice");
//		User bob = userService.getUserByName("xiang");
//		System.out.println(bob);
	userService.updates("kakaxi","gmai@.com",5);

		//通过userServie实例来调用登录方法，验证有无该账号。,一旦有可以分配token，赋予新的接口权限。
//		userService.login("xiang@qq.com","password1s");
//

		userService.updateUser("Alice");
//		User tom = userService.register("tom@example.com", "password3", "Tom");
//		System.out.println(tom);
		System.out.println("Total: " + userService.getUsers());
		for (User u : userService.getUsers(1)) {
			System.out.println(u);
		}
		((ConfigurableApplicationContext) context).close();
		//这里已经进行封装，封装的函数在UserService里面
	}



	@Value("${jdbc.url}")
	String jdbcUrl;

	@Value("${jdbc.username}")
	String jdbcUsername;

	@Value("${jdbc.password}")
	String jdbcPassword;

	@Bean
	JdbcTemplate createJdbcTemplate(@Autowired DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	DataSource createDataSource() {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(jdbcUrl);
		config.setUsername(jdbcUsername);
		config.setPassword(jdbcPassword);
		config.addDataSourceProperty("autoCommit", "true");
		config.addDataSourceProperty("connectionTimeout", "5");
		config.addDataSourceProperty("idleTimeout", "60");
		return new HikariDataSource(config);
	}
}
