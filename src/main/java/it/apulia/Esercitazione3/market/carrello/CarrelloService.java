package it.apulia.Esercitazione3.market.carrello;

import java.util.List;

public interface CarrelloService {

    public Carrello addCarrello(NotaSpesa notaSpesa);
    public Carrello getCarrellobyId(Integer id);
    public List<Carrello> getAllCarrelli();
    public List<Carrello> findCarrelliByAnno(Integer anno);
}
