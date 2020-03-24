package com.katsueitlab.katsueserver.configurations;

import com.katsueitlab.katsueserver.staff.model.Staff;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "staffEntityManagerFactory",
        basePackages = { "com.ktasueitlab.katsueserver.staff.repository",
                "com.ktasueitlab.katsueserver.staff.exception",
                "com.ktasueitlab.katsueserver.staff.service",
                "com.ktasueitlab.katsueserver.staff.controller",  }
)
public class DataBaseStaffConfiguration {

    @Bean
    @ConfigurationProperties("app.jpa.staff")
    public JpaProperties staffJpaProperties() {
        return new JpaProperties();
    }

    @Primary
    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.staff-datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "staffEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean staffEntityManagerFactory(final EntityManagerFactoryBuilder builder,
                                                                            @Qualifier("staffDataSource")
                                                                            final DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages(Staff.class)
                .persistenceUnit("staff")
                .properties(staffJpaProperties().getProperties())
                .build();
    }

    @Primary
    @Bean(name = "staffTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("staffEntityManagerFactory") EntityManagerFactory
                    entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}