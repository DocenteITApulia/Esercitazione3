package it.apulia.Esercitazione3.market.carrello;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarrelloRepo extends MongoRepository<Carrello,Integer> {

    public List<Carrello> findByTotaleGreaterThan(Double totale);
    //si potrebbero implementare anche between e less than


}
