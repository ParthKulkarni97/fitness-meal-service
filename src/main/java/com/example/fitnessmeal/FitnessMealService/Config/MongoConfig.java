package com.example.fitnessmeal.FitnessMealService.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.example.fitnessmeal.FitnessMealService.Repo")
public class MongoConfig {
}