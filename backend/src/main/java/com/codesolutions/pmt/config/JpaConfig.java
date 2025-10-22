package com.codesolutions.pmt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.codesolutions.pmt.repository")
public class JpaConfig {
}
