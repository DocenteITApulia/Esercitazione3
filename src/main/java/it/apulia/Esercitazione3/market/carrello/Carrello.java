package it.apulia.Esercitazione3.market.carrello;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Document(collection = "Carrello")
public class Carrello {
    @Id
    Integer _id;
    List<VoceScontrino> listaspesa;
    //Date datascontrino;
    String datascontrino;
    Double totale = 0.00;
    String selfUrl;

    public Carrello() {
        listaspesa = new ArrayList<VoceScontrino>();
        datascontrino = new Date().toString();
    }

    //public Carrello(Integer _id, List<VoceScontrino> listaspesa, Date datascontrino, Double totale, String selfUrl) {
    public Carrello(Integer _id, List<VoceScontrino> listaspesa, String datascontrino, Double totale, String selfUrl) {
        this._id = _id;
        this.listaspesa = listaspesa;
        this.datascontrino = datascontrino;
        this.totale = totale;
        this.selfUrl = selfUrl;
    }
}
