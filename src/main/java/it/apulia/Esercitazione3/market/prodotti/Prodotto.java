package it.apulia.Esercitazione3.market.prodotti;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Prodotto")
public class Prodotto {
    @Id
    Integer _id;
    @Indexed
    String nome;
    Double prezzo;
    String selfUrl;

    public Prodotto(Integer _id, String nome, Double prezzo, String selfUrl) {
        this._id = _id;
        this.nome = nome;
        this.prezzo = prezzo;
        this.selfUrl = selfUrl;
    }

    public Prodotto(String nome, Double prezzo) {
        this.nome = nome;
        this.prezzo = prezzo;
    }

    public Prodotto() {
    }
}
