package teretana;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ClanarineEkran extends VBox{
	private static final ClanarineEkran clanarineEkran = new ClanarineEkran();
	Korisnik korisnik;
	Label naslov;
	
	private ClanarineEkran(){
		setAlignment(Pos.CENTER);
		this.setPadding(new Insets(20,20,20,20));
		
		VBox NAZAD = new VBox();
		NAZAD.setAlignment(Pos.CENTER_LEFT);
        Button btn = new Button("NAZAD");
        NAZAD.getChildren().add(btn);
        naslov = new Label();
        naslov.setStyle("-fx-font-size:60px;");
        naslov.setPadding(new Insets(0,20,0,20));
        /*ime = new Label();
        ime.setStyle("-fx-font-size:60px;");
        ime.setPadding(new Insets(0,20,0,20));*/
        Button tip0 = new Button("DAN");
        Button tip1 = new Button("MESEC 12 TRENINGA");
        Button tip2 = new Button("MESEC NEOGRANICENO");
        Button tip3 = new Button("GODISNJA");
        getChildren().add(NAZAD);
        getChildren().add(naslov);
        //getChildren().add(ime);
        getChildren().add(tip0);
        getChildren().add(tip1);
        getChildren().add(tip2);
        getChildren().add(tip3);

        setMargin(naslov,new Insets(100,0,50,0));
        //setMargin(ime,new Insets(0,0,20,0));
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	KorisnikEkran.prikazi(korisnik);
            }
        });
        tip0.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	PromptEkran.prikazi("UPLATITI DAN ZA " + korisnik.ime + " " + korisnik.prezime + " " + korisnik.id + " ?", () -> {
            		korisnik.clanarina = new Clanarina(0);
            		Korisnici.save();
                	KorisnikEkran.prikazi(korisnik);
            	}, () -> {
            		ClanarineEkran.prikazi(korisnik);
            	});
            }
        });
        tip1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	PromptEkran.prikazi("UPLATITI MESEC 12 ZA " + korisnik.ime + " " + korisnik.prezime + " " + korisnik.id + " ?", () -> {
            		korisnik.clanarina = new Clanarina(1);
            		Korisnici.save();
                	KorisnikEkran.prikazi(korisnik);
            	}, () -> {
            		ClanarineEkran.prikazi(korisnik);
            	});
            }
        });
        tip2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	PromptEkran.prikazi("UPLATITI MESEC NEOGRANICENO ZA " + korisnik.ime + " " + korisnik.prezime + " " + korisnik.id + " ?", () -> {
            		korisnik.clanarina = new Clanarina(2);
            		Korisnici.save();
                	KorisnikEkran.prikazi(korisnik);
            	}, () -> {
            		ClanarineEkran.prikazi(korisnik);
            	});
            }
        });
        tip3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	PromptEkran.prikazi("UPLATITI GODISNJU ZA " + korisnik.ime + " " + korisnik.prezime + " " + korisnik.id + " ?", () -> {
            		korisnik.clanarina = new Clanarina(3);
            		Korisnici.save();
                	KorisnikEkran.prikazi(korisnik);
            	}, () -> {
            		ClanarineEkran.prikazi(korisnik);
            	});
            }
        });
        
        setSpacing(20);
	}
	
	
	
	public static void prikazi(Korisnik korisnik) {
		clanarineEkran.korisnik = korisnik;
		clanarineEkran.naslov.setText("UPLATA ZA: " + korisnik.ime + " " + korisnik.prezime + " " + korisnik.id);
		HelloApplication.scena.setRoot(clanarineEkran);
	}
}

