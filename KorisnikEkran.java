package teretana;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class KorisnikEkran extends VBox{
	private static final KorisnikEkran korisnikEkran = new KorisnikEkran();
	Label ime;
	
	private KorisnikEkran(){
		setAlignment(Pos.CENTER);
		setSpacing(10);
		
		ime = new Label();
        Button btn = new Button("NAZAD");
        boolean imaClanarinu = true;
        Label clanarina = new Label();
        Button prijaviDolazak = new Button("PRIJAVI DOLAZAK");
        Button uplatiClanarinu = new Button("UPLATI CLANARINU");
        getChildren().add(btn);
        getChildren().add(ime);
        getChildren().add(clanarina);
        if(!imaClanarinu) {
        	clanarina.setStyle("-fx-background-color: green");
        	clanarina.setText("IMA CLANARINU");
        	getChildren().add(prijaviDolazak);
        }
        else {
        	clanarina.setStyle("-fx-background-color: red");
        	clanarina.setText("NEMA CLANARINU");
        	getChildren().add(uplatiClanarinu);
        }
        //TODO: popup uspesno prijavljen dolazak/dodata clanarina
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	PrikaziKorisnike.prikazi();
            }
        });
	}
	
	private void setKorisnik(Korisnik korisnik) {
		ime.setText(korisnik.ime);
	}
	
	public static void prikazi(Korisnik korisnik) {
		korisnikEkran.setKorisnik(korisnik);
		HelloApplication.scena.setRoot(korisnikEkran);
	}
}
