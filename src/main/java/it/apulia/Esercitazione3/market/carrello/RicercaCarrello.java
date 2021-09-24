package it.apulia.Esercitazione3.market.carrello;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class RicercaCarrello {
    Integer anno;
    List<Carrello> carrelli;
    Integer quantity;
    Double totale;

    public RicercaCarrello(Integer anno, List<Carrello> carrelli, Double totale) {
        this.anno = anno;
        this.carrelli = carrelli;
        this.quantity = carrelli.size();
        this.totale = totale;
    }
}
