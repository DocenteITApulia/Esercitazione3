package it.apulia.Esercitazione3.market;

import it.apulia.Esercitazione3.market.prodotti.Prodotto;
import it.apulia.Esercitazione3.market.prodotti.ProductsRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MarketConfig {

    @Bean
    CommandLineRunner commandLineRunner(ProductsRepo repository) {
        return args -> {
            Prodotto libro1 = new Prodotto(10001,"Latte",1.20, "http://localhost:8080/market/prodotti/10001");
            Prodotto libro2 = new Prodotto(10002,"Uova",2.00, "http://localhost:8080/market/prodotti/10002");

            List<Prodotto> temp= new ArrayList<>();
            temp.add(libro1);
            temp.add(libro2);


            repository.deleteAll();

            repository.saveAll(
                    temp
            );
        };

    }
}
