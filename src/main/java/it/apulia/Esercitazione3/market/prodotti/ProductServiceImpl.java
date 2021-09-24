package it.apulia.Esercitazione3.market.prodotti;

import it.apulia.Esercitazione3.market.errors.MyNotAcceptableException;
import it.apulia.Esercitazione3.market.errors.MyNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService{
    private final ProductsRepo productsRepo;

    private Integer counter =0;// riferito ai prodotti

    @Autowired
    public ProductServiceImpl(ProductsRepo productsRepo){
        this.productsRepo = productsRepo;
    }

    @Override
    public Prodotto addProdotto(Prodotto prodotto) {
        if(productsRepo.findByNome(prodotto.getNome())!=null){
            throw new MyNotAcceptableException("Il prodotto "+prodotto.getNome()+" è già presente all'interno del db");
        }
        this.counter++;
        Prodotto temp = prodotto;
        temp.set_id(counter);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/market/products/"+counter).toUriString());
        temp.setSelfUrl(uri.toString());

        productsRepo.save(temp);
        return temp;
    }

    @Override
    public List<Prodotto> getProdotti() {
        return productsRepo.findAll();
    }

    @Override
    public Prodotto getProdottoById(Integer id) {
        Prodotto temp = productsRepo.findById(id).get();
        if(temp==null)
        {
            throw new MyNotFoundException("Il prodotto con l'id indicato non è presente all'interno del sistema");
        }
        return temp;
    }

    @Override
    public Prodotto getProdottoByName(String nome) {
        return productsRepo.findByNome(nome);
    }

    @Override
    public List<Prodotto> getProdottiNomeSimile(String nome) {
        return productsRepo.findByRegexpName(nome);
    }

    @Override
    public List<Prodotto> getProdottiPrezzoSuperiore(Double prezzo) {
        return productsRepo.findByPrezzoGreaterThan(prezzo);
    }

    @Override
    public void updateProdotto(Prodotto prodotto) {
        if(!productsRepo.findById(prodotto._id).isPresent())
            throw new MyNotFoundException("Prodotto da aggiornare non trovato");
        else
            productsRepo.save(prodotto);

    }

    @Override
    public void deleteProdottoById(Integer id) {
        productsRepo.deleteById(id); //avremo degli id "vuoti" perché non vado a rimpiazzare quelli eliminati
    }

    @Override
    public List<Prodotto> getProdottoInRange(Double minimo, Double massimo) {
        //return productsRepo.findByPrezzo(minimo,massimo);
        return productsRepo.findByPrezzoBetween(minimo, massimo);
    }
}
