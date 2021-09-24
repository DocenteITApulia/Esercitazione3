package it.apulia.Esercitazione3.market.prodotti;

import java.util.List;

public interface ProductService {

    public Prodotto addProdotto(Prodotto prodotto);

    public Prodotto getProdottoById(Integer id);

    public Prodotto getProdottoByName(String nome);

    public List<Prodotto> getProdottiNomeSimile(String nome);

    public List<Prodotto> getProdottiPrezzoSuperiore(Double prezzo);

    public void updateProdotto(Prodotto prodotto);

    public void deleteProdottoById(Integer id);


}
