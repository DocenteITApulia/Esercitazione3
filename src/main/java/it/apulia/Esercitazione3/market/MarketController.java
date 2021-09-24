package it.apulia.Esercitazione3.market;

import it.apulia.Esercitazione3.market.carrello.Carrello;
import it.apulia.Esercitazione3.market.carrello.CarrelloService;
import it.apulia.Esercitazione3.market.carrello.NotaSpesa;
import it.apulia.Esercitazione3.market.carrello.RicercaCarrello;
import it.apulia.Esercitazione3.market.errors.MyNotAcceptableException;
import it.apulia.Esercitazione3.market.errors.MyNotFoundException;
import it.apulia.Esercitazione3.market.prodotti.Prodotto;
import it.apulia.Esercitazione3.market.prodotti.ProductService;
import it.apulia.Esercitazione3.market.utils.OggettoRegex;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/market")
public class MarketController {

    private final ProductService productService;
    private final CarrelloService carrelloService;

    @Autowired
    public MarketController(ProductService productService, CarrelloService carrelloService){
        this.productService = productService;
        this.carrelloService = carrelloService;
    }

    @PostMapping("/prodotti")
    ResponseEntity<?> addNewProduct(@RequestBody Prodotto prodotto){
        try{
            Prodotto temp = productService.addProdotto(prodotto);
            return ResponseEntity.created(URI.create(temp.getSelfUrl())).body(temp);
        }catch(MyNotAcceptableException excp){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(excp.getMessage());
        }
    }

    @GetMapping("/prodotti")
    ResponseEntity<List<Prodotto>> getAllProducts() {
        return ResponseEntity.ok().body(productService.getProdotti());
    }

    @GetMapping("/prodotti/{productId}")
    ResponseEntity<?> getProductById(@PathVariable Integer productId){
        try{
            Prodotto temp = productService.getProdottoById(productId);
            return ResponseEntity.ok().body(temp);

        }catch(MyNotFoundException excp){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(excp.getMessage());
        }
    }

    @PutMapping("/prodotti/{productId}")
    ResponseEntity<?> updateProdotto(@PathVariable Integer productId, @RequestBody Prodotto prodotto){
        try {
            productService.updateProdotto(prodotto);
            return ResponseEntity.ok().build();
        }catch(MyNotFoundException excp){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(excp.getMessage());
        }
    }

    @DeleteMapping("/prodotti/{productId}")
    ResponseEntity<?> deleteProdotto(@PathVariable Integer productId){
        productService.deleteProdottoById(productId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/prodotti/deepsearch")
    ResponseEntity<?> findProdottoBySubstring(@RequestBody OggettoRegex regex){
        return ResponseEntity.ok().body(productService.getProdottiNomeSimile(regex.getRegex()));
    }

    @GetMapping("/prodotti/prezzo")
    ResponseEntity<List<Prodotto>> findProdottoPerPrezzo(@RequestParam(defaultValue = "0.00") Double min,@RequestParam(defaultValue = "1000000.00") Double max){
        return ResponseEntity.ok().body(productService.getProdottoInRange(min, max));
    }

    @GetMapping("/carrelli")
    ResponseEntity<List<Carrello>> getAllCarrelli(){
        return ResponseEntity.ok().body(carrelloService.getAllCarrelli());
    }

    @PostMapping("/carrelli")
    ResponseEntity<Carrello> doSpesa(@RequestBody NotaSpesa notaSpesa){
        Carrello temp = carrelloService.addCarrello(notaSpesa);
        return ResponseEntity.created(URI.create(temp.getSelfUrl())).body(temp);
    }

    @GetMapping("/carrelli/{idcarrello}")
    ResponseEntity<Carrello> getCarrelloById(@PathVariable Integer idcarrello){
        return ResponseEntity.ok().body(carrelloService.getCarrellobyId(idcarrello));
    }

    @GetMapping("/carrelli/bilancio")
    ResponseEntity<RicercaCarrello> getRisultatoPerAnno(@RequestParam Integer anno){
        return ResponseEntity.ok().body(carrelloService.findCarrelliByAnno(anno));
    }

    @GetMapping("/carrelli/bilanciocustom")
    ResponseEntity<RicercaCarrello> getRisultatoPerAnnoeFiltrato(@RequestParam Integer anno,
                                                                 @RequestParam(defaultValue = "0.00") Double min, @RequestParam(defaultValue = "1000000.00") Double max){
        return ResponseEntity.ok().body(carrelloService.findCarrelliByDataAndTotaleRange(anno,min,max));
    }
}
