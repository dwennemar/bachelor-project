package de.dwennemar.bachelor.keybackup.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
        basePackages = "de.dwennemar.bachelor.keybackup.persist.backup",
        entityManagerFactoryRef = "keyEntityManager",
        transactionManagerRef = "keyTransactionManager"
)
public class KeyBackupConfig {

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean keyEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(keyDataSource());
        em.setPackagesToScan(
                new String[] {"de.dwennemar.bachelor.keybackup.persist.backup"}
        );

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Primary
    @Bean
    public DataSource keyDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        //TODO: call method for creating new Database
        StringBuilder sb = new StringBuilder();
        sb.append("jdbc:mysql://dwennemar.de:8600/");
        sb.append(System.getProperty("currentDatabase.name"));
        sb.append("?createDatabaseIfNotExist=true");

        dataSource.setUrl(sb.toString());
        dataSource.setUsername("root");
        dataSource.setPassword("bachelorThesis");

        return dataSource;
    }

    @Primary
    @Bean
    public PlatformTransactionManager keyTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();

        transactionManager.setEntityManagerFactory(keyEntityManager().getObject());

        return transactionManager;
    }
}
