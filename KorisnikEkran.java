package teretana;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class KorisnikEkran extends VBox{
	private static final KorisnikEkran korisnikEkran = new KorisnikEkran();
	
	private Korisnik korisnik;
	private Label imeLabel, clanarinaLabel, brojTreningaLabel, clanarinaDoLabel;
	private VBox prijavljenBox;
	private Button prijaviDolazak, uplatiClanarinu;
	private ClickDelay clickDelay;
	
	private KorisnikEkran(){
		setAlignment(Pos.CENTER);
		setSpacing(10);
		setPadding(new Insets(20));
		
		imeLabel = new Label();
		imeLabel.setStyle("-fx-font-size:60px;");
		
		clanarinaLabel = new Label();
		clanarinaLabel.setStyle("-fx-font-size:60px;");
		
		brojTreningaLabel = new Label();
		clanarinaDoLabel = new Label();
		
		VBox nazadVBox = new VBox();
		nazadVBox.setAlignment(Pos.CENTER_LEFT);
		Button nazadBtn = new Button("NAZAD");
        nazadVBox.getChildren().add(nazadBtn);
		
		VBox opisClanarine = new VBox();
		opisClanarine.setPadding(new Insets(0, 0, 50, 0));
		opisClanarine.setAlignment(Pos.CENTER);
        opisClanarine.getChildren().add(clanarinaLabel);
        opisClanarine.getChildren().add(clanarinaDoLabel);
        opisClanarine.getChildren().add(brojTreningaLabel);
        
        prijaviDolazak = new Button("PRIJAVI DOLAZAK");
        uplatiClanarinu = new Button("UPLATI CLANARINU");
        Label prijavljenLabel = new Label("VEC PRIJAVLJEN");
        prijavljenLabel.setStyle("-fx-font-size:50px;");
        prijavljenBox = new VBox();
        prijavljenBox.setAlignment(Pos.CENTER);
        prijavljenBox.getChildren().add(prijavljenLabel);
        prijavljenBox.setMinSize(0, 112);
        
        getChildren().add(nazadVBox);
        getChildren().add(imeLabel);
        getChildren().add(opisClanarine);
        
        setMargin(imeLabel, new Insets(91,0,40,0));

        nazadBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	PrikaziKorisnike.prikazi();
            }
        });
        prijaviDolazak.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	if(!clickDelay.clickable()) return;
            	PopupEkran.prikazi("PRIJAVI DOLAZAK ZA " + korisnik.ime + " " + korisnik.prezime + " (" + korisnik.id + ") ?",
            	() -> {
            		PopupEkran.prikazi("USPESNO STE PRIJAVILI DOLAZAK ZA\n" + korisnik.ime + " " + korisnik.prezime + " (" + korisnik.id + ")",
        				() -> {
        					korisnik.dolazak = LocalDate.now().toString();
                        	korisnik.clanarina.brojTreninga--;
                        	Korisnici.save();
                        	KorisnikEkran.prikazi(korisnik);
        				});
            	}, 
            	() -> {
            		KorisnikEkran.prikazi(korisnik);
            	});
            }
        });
        uplatiClanarinu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	if(!clickDelay.clickable()) return;
            	ClanarineEkran.prikazi(korisnik);
            }
        });
	}
	
	private void init(Korisnik korisnik) {
		this.korisnik = korisnik;

		getChildren().remove(prijaviDolazak);
		getChildren().remove(uplatiClanarinu);
		getChildren().remove(prijavljenBox);
		
		if(!korisnik.imaClanarinu()) {
			clanarinaLabel.setStyle("-fx-background-color: red");
			if(korisnik.clanarina.brojTreninga == 0) clanarinaLabel.setText("NEMA VISE TRENINGA");
			else clanarinaLabel.setText("NEMA CLANARINU");
			
			clanarinaDoLabel.setVisible(false);
			brojTreningaLabel.setVisible(false);
			
			getChildren().add(uplatiClanarinu);
        }
        else {
        	clanarinaLabel.setStyle("-fx-background-color: green");
        	clanarinaLabel.setText("IMA CLANARINU");
        	
        	clanarinaDoLabel.setVisible(true);
        	brojTreningaLabel.setVisible(true);
        	
        	clanarinaDoLabel.setText("CLANARINA DO: " + korisnik.clanarina.istice.format(DateTimeFormatter.ofPattern("dd.MM.uuuu.")));
        	
        	if(korisnik.clanarina.brojTreninga <= 12) brojTreningaLabel.setText("OSTALO TRENINGA: " + korisnik.clanarina.brojTreninga);
        	else brojTreningaLabel.setText("OSTALO TRENINGA: NEOGRANICENO");
        	
        	if(korisnik.dolazak.equals(LocalDate.now().toString())) getChildren().add(prijavljenBox);
        	else getChildren().add(prijaviDolazak);
        }
		imeLabel.setText(korisnik.ime + " " + korisnik.prezime + " (" + korisnik.id + ")");
		clickDelay = new ClickDelay(0.4);
	}
	
	public static void prikazi(Korisnik korisnik) {
		korisnikEkran.init(korisnik);
		HelloApplication.scena.setRoot(korisnikEkran);
	}
}