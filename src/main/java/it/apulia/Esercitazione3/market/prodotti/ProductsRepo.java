package it.apulia.Esercitazione3.market.prodotti;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepo extends MongoRepository<Prodotto,Integer> {

    //serve per l'aggiunta del prodotto
   public Prodotto findByNome(String nome);

    //oppure passando una regex "pura",ad esempio "^A" da quelle che cominciano per A, nota è CASE SENSITIVE
    @Query("{ 'nome' : { $regex: ?0 } }")
    List<Prodotto> findByRegexpName(String regexp); //.latte. dovrebbe funzionare, da testare->test regex https://regex101.com/
    /*
        public List<Prodotto> findByPrezzoGreaterThan(Double prezzo);

        public List<Prodotto> findByPrezzoLessThan(Double prezzo);
    //FUNZIONA, sto testando l'altro
        @Query("{ 'prezzo' : {$gt : ?0, $lt : ?1} }")
        public List<Prodotto> findByPrezzo(Double prezzomin, Double prezzomax);
    */

    public List<Prodotto> findByPrezzoBetween(Double prezzomin, Double prezzomax);
}
