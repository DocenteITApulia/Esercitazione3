package it.apulia.Esercitazione3.market.carrello;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CarrelloRepo extends MongoRepository<Carrello,Integer> {

    public List<Carrello> findByTotaleGreaterThan(Double totale);

    @Query("{ 'datascontrino' : { $regex: ?0 }, 'totale' : {$gt : ?1, $lt : ?2} }")
    public List<Carrello> findByDataAndTotaleRange(String regex, Double min, Double max);

    //@Query("{ 'datascontrino' : {$gt : temp1, $lt : temp2} }")
    @Query("{ 'datascontrino' : { $regex: ?0 } }")
    public List<Carrello> findByDatascontrino(String regex);
    //public List<Carrello> findByDatascontrinoGreaterThanAndLessThan(Date temp1, Date temp2);
    //si potrebbero implementare anche between e less than


}
