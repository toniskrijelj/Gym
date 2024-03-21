package teretana;

import java.time.LocalDate;

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
	Korisnik korisnik;
	Label ime, clanarina;
	Button prijaviDolazak, uplatiClanarinu;
	
	private KorisnikEkran(){
		setAlignment(Pos.CENTER);
		setSpacing(10);
		
		ime = new Label();
        Button btn = new Button("NAZAD");
        clanarina = new Label();
        
        prijaviDolazak = new Button("PRIJAVI DOLAZAK");
        uplatiClanarinu = new Button("UPLATI CLANARINU");
        
        getChildren().add(btn);
        getChildren().add(ime);
        getChildren().add(clanarina);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	PrikaziKorisnike.prikazi();
            }
        });
        
        prijaviDolazak.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	korisnik.dolazak = LocalDate.now().toString();
            	Korisnici.save();
            	PrikaziKorisnike.prikazi();
            }
        });
        
        uplatiClanarinu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	korisnik.clanarina = new Clanarina(LocalDate.now().toString(), 0);
            	Korisnici.save();
            	PrikaziKorisnike.prikazi();
            }
        });
	}
	
	private void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
		boolean imaClanarinu = korisnik.clanarina.brojTreninga > 0 && LocalDate.parse(korisnik.clanarina.istice).isAfter(LocalDate.now());
		System.out.print(imaClanarinu);
		getChildren().remove(prijaviDolazak);
		getChildren().remove(uplatiClanarinu);
		if(!imaClanarinu) {
			getChildren().add(uplatiClanarinu);
			clanarina.setStyle("-fx-background-color: red");
	    	clanarina.setText("NEMA CLANARINU");
        }
        else {
        	getChildren().add(prijaviDolazak);
        	clanarina.setStyle("-fx-background-color: green");
        	clanarina.setText("IMA CLANARINU");
        }
		ime.setText(korisnik.ime);
	}
	
	public static void prikazi(Korisnik korisnik) {
		korisnikEkran.setKorisnik(korisnik);
		HelloApplication.scena.setRoot(korisnikEkran);
	}
}
