package de.dwennemar.bachelor.keybackup.conf;

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
        basePackages = "de.dwennemar.bachelor.keybackup.persist.restore.prod",
        entityManagerFactoryRef = "keyEntityManager",
        transactionManagerRef = "keyTransactionManager"
)
public class KeyRestoreConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean keyEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(keyDataSource());
        em.setPackagesToScan(
                new String[] {"de.dwennemar.bachelor.keybackup.persist.restore.prod"}
        );

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    public DataSource keyDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");

        dataSource.setUrl("jdbc:postgresql://dwennemar.de:5401/key_db");
        

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager keyTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();

        transactionManager.setEntityManagerFactory(keyEntityManager().getObject());

        return transactionManager;
    }
}
