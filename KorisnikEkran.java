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
		
		ime = new Label();
        Button btn = new Button("NAZAD");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	PrikaziKorisnike.prikazi();
            }
        });
        
        getChildren().add(ime);
        getChildren().add(btn);
	}
	
	private void setKorisnik(Korisnik korisnik) {
		
	}
	
	public static void prikazi(Korisnik korisnik) {
		korisnikEkran.setKorisnik(korisnik);
		HelloApplication.scena.setRoot(korisnikEkran);
	}
}
