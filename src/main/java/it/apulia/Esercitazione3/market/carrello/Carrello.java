package it.apulia.Esercitazione3.market.carrello;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Document(collection = "Carrello")
@AllArgsConstructor
public class Carrello {
    @Id
    Integer _id;
    List<VoceScontrino> listaspesa;
    Date datascontrino;
    Double totale = 0.00;
    String selfUrl;

    public Carrello() {
        listaspesa = new ArrayList<VoceScontrino>();
        datascontrino = new Date();
    }

}
