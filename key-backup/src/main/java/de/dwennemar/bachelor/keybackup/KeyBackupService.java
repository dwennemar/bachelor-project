package de.dwennemar.bachelor.keybackup;

import de.dwennemar.bachelor.keybackup.conf.DatabaseRegistryConfig;
import de.dwennemar.bachelor.keybackup.registry.RegistryRepository;
import de.dwennemar.bachelor.keybackup.registry.impl.Registry;
import de.dwennemar.bachelor.keybackup.services.BackupService;
import de.dwennemar.bachelor.keybackup.services.RestoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class KeyBackupService {

    private final Logger log = LoggerFactory.getLogger(KeyBackupService.class);
    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        System.setProperty("currentDatabase.name", DatabaseRegistryConfig.newDbName());
        SpringApplication.run(KeyBackupService.class, args);
    }

    public static void restartForRestore(String dbName) {
        ApplicationArguments args = context.getBean(ApplicationArguments.class);

        Thread thread = new Thread(() -> {
            context.close();
            System.setProperty("backupDatabase.name", dbName);
            context = SpringApplication.run(KeyBackupService.class, args.getSourceArgs());
        });

        thread.setDaemon(false);
        thread.start();
    }

    @Bean
    public String registerBackupDb(RegistryRepository registry) {
        String dbName = System.getProperty("currentDatabase.name");

        Registry r = new Registry();
        r.setDbName(dbName);
        r.setCreated(LocalDate.now());

        registry.save(r);

        return dbName;
    }

    @Bean
    public boolean deleteOldDb(RegistryRepository registry, DatabaseRegistryConfig config) {
        LocalDate d = LocalDate.now().minusDays(30L);

        Iterable<Registry> registryList = registry.findAll();
        registryList.forEach(entry -> {
            if(entry.getCreated().isBefore(d)) {
                try {
                    Statement s = config.getRegistryDataSource().getConnection().createStatement();
                    s.execute("DROP SCHEMA " + entry.getDbName());

                    registry.delete(entry);
                } catch (SQLException e) {
                    log.error(e.toString());
                }
            }
        });

        return true;
    }


    public boolean startBackupProcess(BackupService backupService) {
        backupService.backupAll();
        return true;
    }

    public boolean startRestoreProcess(RegistryRepository repo, RestoreService restoreService) {

        List<Registry> r = repo.findByCreated(LocalDate.now());

        if (r.size() == 1) {
            restartForRestore(r.get(0).getDbName());
            restoreService.restoreAll();
        } else {
            log.error("Multiple databases found!");
        }

        return true;
    }
}
