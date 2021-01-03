package com.carnnjoh.poedatatool.schedulers;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "")
public class ThreadPoolTaskSchedulerConfig {
//	@Bean
//	public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
//		ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
//		threadPoolTaskScheduler.setPoolSize(5);
//		threadPoolTaskScheduler.setThreadNamePrefix("ThreadPoolTaskScheduler");
//		return threadPoolTaskScheduler;
//	}
}
