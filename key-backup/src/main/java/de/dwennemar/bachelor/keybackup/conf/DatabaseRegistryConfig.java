package de.dwennemar.bachelor.keybackup.conf;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
        basePackages = "de.dwennemar.bachelor.keybackup.registry",
        entityManagerFactoryRef = "registryEntityManager",
        transactionManagerRef = "registryTransactionManager"
)
public class DatabaseRegistryConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean registryEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(registryDataSource());
        em.setPackagesToScan(
                new String[] {"de.dwennemar.bachelor.keybackup.registry"}
        );

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    public DataSource registryDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        StringBuilder sb = new StringBuilder();
        sb.append("jdbc:mysql://dwennemar.de:8600/");
        sb.append("backupRegistry");
        sb.append("?createDatabaseIfNotExist=true");

        dataSource.setUrl(sb.toString());
        //DB-Username && Passwort;

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager registryTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();

        transactionManager.setEntityManagerFactory(registryEntityManager().getObject());

        return transactionManager;
    }









    @Bean
    public DataSource getRegistryDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();

        dataSourceBuilder.url("jdbc:mysql://dwennemar.de:8600/backupRegistry?createDatabaseIfNotExist=true");
        dataSourceBuilder.username("root");
        dataSourceBuilder.password("bachelorThesis");

        return dataSourceBuilder.build();
    }

    public static String newDbName() {
        return RandomStringUtils.randomAlphabetic(15);
    }
}
