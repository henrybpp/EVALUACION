package com.hry.evaluacion.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.hry.core.common.base.BaseConfig;

@Configuration
@EnableMongoRepositories("com.hry.persistence.mongo.repository*")
@PropertySource(value = "file:${app.properties.path}backend-dev.properties", ignoreResourceNotFound = true)
public class AppConfig extends BaseConfig{

}
