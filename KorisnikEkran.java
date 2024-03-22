package teretana;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class KorisnikEkran extends VBox{
	private static final KorisnikEkran korisnikEkran = new KorisnikEkran();
	Korisnik korisnik;
	Label ime, clanarina, prijavljen, brojTreninga, clanarinaDo;
	Button prijaviDolazak, uplatiClanarinu;
	
	private KorisnikEkran(){
		setAlignment(Pos.CENTER);
		setSpacing(10);
		setPadding(new Insets(50));
		ime = new Label();
		ime.setStyle("-fx-font-size:60px;");
		clanarina = new Label();
		clanarina.setStyle("-fx-font-size:60px;");
		ime.setPadding(new Insets(0, 10, 0, 10));
		clanarina.setPadding(new Insets(0, 10, 0, 10));
		brojTreninga = new Label();
		brojTreninga.setPadding(new Insets(0,20,0,20));
		clanarinaDo = new Label();
		clanarinaDo.setPadding(new Insets(0,20,0,20));
		VBox nazad = new VBox();
		VBox space = new VBox();
		//nazad.setPadding(new Insets(50));
		nazad.setAlignment(Pos.CENTER_LEFT);
		
		space.setPadding(new Insets(0, 0, 50, 0));
		space.setAlignment(Pos.CENTER);
        Button btn = new Button("NAZAD");
        nazad.getChildren().add(btn);
        space.getChildren().add(clanarina);
        space.getChildren().add(brojTreninga);
        space.getChildren().add(clanarinaDo);
        
        prijaviDolazak = new Button("PRIJAVI DOLAZAK");
        uplatiClanarinu = new Button("UPLATI CLANARINU");
        prijavljen = new Label("VEC PRIJAVLJEN");
        prijavljen.setPadding(new Insets(0,20,0,20));
        prijavljen.setStyle("-fx-font-size:50px;");
        getChildren().add(nazad);
        getChildren().add(ime);
        getChildren().add(space);
        
        setMargin(ime,new Insets(50,0,40,0));

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	PrikaziKorisnike.prikazi();
            }
        });
        
        prijaviDolazak.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	PromptEkran.prikazi("PRIJAVI DOLAZAK ZA " + korisnik.ime + " " + korisnik.prezime + "?", () -> {
            		korisnik.dolazak = LocalDate.now().toString();
                	korisnik.clanarina.brojTreninga--;
                	Korisnici.save();
                	KorisnikEkran.prikazi(korisnik);
            	}, () -> {
            		KorisnikEkran.prikazi(korisnik);
            	});
            }
        });
        
        uplatiClanarinu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	ClanarineEkran.prikazi(korisnik);
            }
        });
	}
	
	private void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
		boolean imaClanarinu = (korisnik.clanarina.brojTreninga > 0 || korisnik.dolazak.equals(LocalDate.now().toString())) && LocalDate.parse(korisnik.clanarina.istice).isAfter(LocalDate.now());
		System.out.print(imaClanarinu);
		getChildren().remove(prijaviDolazak);
		getChildren().remove(uplatiClanarinu);
		getChildren().remove(prijavljen);
		if(!imaClanarinu) {
			getChildren().add(uplatiClanarinu);
			clanarina.setStyle("-fx-background-color: red");
			if(korisnik.clanarina.brojTreninga == 0) clanarina.setText("NEMA VISE TRENINGA");
			else clanarina.setText("NEMA CLANARINU");
			brojTreninga.setVisible(false);
			clanarinaDo.setVisible(false);
        }
        else {
        	System.out.print(korisnik.dolazak+"\n");
        	if(korisnik.dolazak.equals(LocalDate.now().toString())) {
        		getChildren().add(prijavljen);
        	}
        	else {
        		getChildren().add(prijaviDolazak);
        	}
        	brojTreninga.setVisible(true);
        	clanarinaDo.setVisible(true);
        	String[] str=korisnik.clanarina.istice.split("-");
        	clanarinaDo.setText("CLANARINA DO: " + str[2] + "-" + str[1] + "-" + str[0]);
        	if(korisnik.clanarina.brojTreninga<=12) brojTreninga.setText("OSTALO TRENINGA: " + korisnik.clanarina.brojTreninga);
        	else brojTreninga.setText("OSTALO TRENINGA: NEOGRANICENO");
        	
        	clanarina.setStyle("-fx-background-color: green");
        	clanarina.setText("IMA CLANARINU");
        }
		ime.setText(korisnik.ime + " " + korisnik.prezime + " " + korisnik.id);
	}
	
	public static void prikazi(Korisnik korisnik) {
		korisnikEkran.setKorisnik(korisnik);
		HelloApplication.scena.setRoot(korisnikEkran);
	}
}