package it.apulia.Esercitazione3.market.carrello;

import it.apulia.Esercitazione3.market.prodotti.ProductsRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CarrelloServiceImpl implements CarrelloService{
    private final CarrelloRepo carrelloRepo;
    private final ProductsRepo productsRepo;

    private Integer counter =0;// riferito ai Carrelli

    @Override
    public Carrello addCarrello(NotaSpesa notaSpesa) {
        this.counter++;
        Carrello temp = new Carrello();
        temp.set_id(counter);
        List<ProdottoSpesa> listaspesa = notaSpesa.getListaspesa();
        //scorro la lista ed effettuo il calcolo dei parziali
        listaspesa.forEach(prodottoinlista -> {
            Double prezzo = productsRepo.findByNome(prodottoinlista.getNome()).getPrezzo();
            VoceScontrino voceScontrino = new VoceScontrino(prodottoinlista.getNome(), prodottoinlista.getQuantity(),
                    Double.valueOf(prodottoinlista.getQuantity().toString())*prezzo);
            temp.getListaspesa().add(voceScontrino);
        });
        //adesso devo sommare i parziali
        Double temptot = 0.00;
        //https://www.baeldung.com/java-stream-sum
        temptot = temp.getListaspesa().stream().map(x -> x.getSubtot()).reduce(0.00,Double::sum);
        log.info("Il totale ottenuto è {}", temptot.toString());
        temp.setTotale(temptot);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/market/carrelli/"+counter).toUriString());
        temp.setSelfUrl(uri.toString());
        carrelloRepo.save(temp);

        return temp;
    }

    @Override
    public Carrello getCarrellobyId(Integer id) {
        return carrelloRepo.findById(id).get();
    }

    @Override
    public List<Carrello> getAllCarrelli() {
        return carrelloRepo.findAll();
    }

    @Override
    public RicercaCarrello findCarrelliByAnno(Integer anno) {
/*
        Date temp1 = new Date(anno+1,1,1);
        Date temp2 = new Date(anno-1,1,1);

        List<Carrello> temp = carrelloRepo.findByDatascontrinoGreaterThanAndLessThan(temp2,temp1);*/
        List<Carrello> temp = carrelloRepo.findByDatascontrino(anno.toString()+"$");
        Double tot = temp.stream().map(x -> x.getTotale()).reduce(0.00,Double::sum);
        RicercaCarrello tempresult = new RicercaCarrello(anno,temp,tot);
        return tempresult;

    }
}
