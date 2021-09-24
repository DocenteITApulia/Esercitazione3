package it.apulia.Esercitazione3.market.carrello;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class NotaSpesa {
    List<ProdottoSpesa> listaspesa;

    public NotaSpesa(){
        listaspesa = new ArrayList<ProdottoSpesa>();
    }
}
