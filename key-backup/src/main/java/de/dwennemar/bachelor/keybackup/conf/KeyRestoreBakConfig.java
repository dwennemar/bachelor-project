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
        basePackages = "de.dwennemar.bachelor.keybackup.persist.restore.bak",
        entityManagerFactoryRef = "keyEntityManager",
        transactionManagerRef = "keyTransactionManager"
)
public class KeyRestoreBakConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean keyEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(keyDataSource());
        em.setPackagesToScan(
                new String[] {"de.dwennemar.bachelor.keybackup.restore.bak"}
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
    public DataSource keyDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        StringBuilder sb = new StringBuilder();
        sb.append("jdbc:mysql://dwennemar.de:8600/");
        sb.append(System.getProperty("backupDatabase.name"));

        dataSource.setUrl(sb.toString());
        //DB-Passwort / username

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager keyTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();

        transactionManager.setEntityManagerFactory(keyEntityManager().getObject());

        return transactionManager;
    }
}
