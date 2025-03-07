package com.kverma.journalApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*

Spring Boot => MERN
Controller => Router/Contoller
Entity => Model
Repository => Mongoose Query Methods  (findById, find => findById, delete etc)
Service => Service Layer (application logic in form of modules and functions)

*/



@SpringBootApplication
@EnableTransactionManagement
@EnableConfigurationProperties
@EnableMongoRepositories(basePackages = "com.kverma.journalApp.repository")
public class JournalApplication {

	public static void main(String[] args) {
		SpringApplication.run(JournalApplication.class, args);
	}

	@Bean
	public PlatformTransactionManager func(MongoDatabaseFactory dbFactory){
		return new MongoTransactionManager(dbFactory);
	}
}

// PlatformTransactionManager
// is implemented by
// MongoTransactionManager