package com.shine;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.github.pagehelper.PageHelper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@SpringBootApplication
@MapperScan("com.shine.dao")
public class DemoApplication implements CommandLineRunner {
	@Resource
	private FooService fooService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	/**
	 * 配置mybatis的分页插件pageHelper
	 * @return
	 */
	@Bean
	public PageHelper pageHelper(){
		PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		properties.setProperty("offsetAsPageNum","true");
		properties.setProperty("rowBoundsWithCount","true");
		properties.setProperty("reasonable","true");
		//配置mysql数据库的方言
		properties.setProperty("dialect","mysql");
		pageHelper.setProperties(properties);
		return pageHelper;
	}

	@Override
	public void run(String... args) throws Exception {
      while (true){
		  fooService.oom();
	  }
	}


	@Component
	private class FooService{
		List<String> data = new ArrayList<>();

		public void oom(){
			data.add(IntStream.rangeClosed(1, 10_0000)
					.mapToObj(__->"a")
					.collect(Collectors.joining("")));
		}
	}
}
