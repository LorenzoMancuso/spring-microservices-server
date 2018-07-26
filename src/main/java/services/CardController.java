/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author lorenzo
 */
@RestController
@RequestMapping("/cards")
public class CardController {
    
    //1) aggiunta nuova card con eventuale multimedia (card+cardCategory+Multimedia+User)
    //2) aggiunta nuovo commento (card+Comment+User)
    //3) aggiunta nuova valutazione (card+Rating+User)
    //4) (NON NECESSARIA) modifica della card
    //5) scaricare tutte le card di una determinata categoria (card+cardCategory+Multimedia+Rating)
    //6) (NON NECESSARIA) rimuovere una card
    //7) scaricare la lista di card relativa ai followed legate alle categorie di interesse dell'user (card+cardCategory+Multimedia+Rating+User)
    //8) card più popolari (card+cardCategory+Multimedia+Rating)
    
}
