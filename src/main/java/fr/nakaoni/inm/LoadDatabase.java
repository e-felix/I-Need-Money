package fr.nakaoni.inm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.nakaoni.inm.entity.Bank;
import fr.nakaoni.inm.repository.BankRepository;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(InmApplication.class);

    @Bean
    CommandLineRunner initDatabase(BankRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Bank("BoursoBank")));
            log.info("Preloading " + repository.save(new Bank("Crédit Agricole")));
        };
    }

}
