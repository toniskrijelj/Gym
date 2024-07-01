package teretana;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class KlijentEkran extends VBox{
	private static final KlijentEkran klijentEkran = new KlijentEkran();
	
	private Korisnik klijent;
	private int dosaoOd;
	private Label imeLabel, zakazanTreningLabel;
	private Button zakaziTrening, otkaziTrening;
	
	private KlijentEkran(){
		setAlignment(Pos.CENTER);
		setSpacing(10);
		setPadding(new Insets(20));
		
		imeLabel = new Label();
		imeLabel.setStyle("-fx-font-size:60px;");
		
		zakazanTreningLabel = new Label();
		zakazanTreningLabel.setStyle("-fx-font-size:60px;");
		
		Button obrisiBtn = new Button("OBRISI");
		obrisiBtn.setStyle("-fx-background-color: red");
		
		HBox nazadVBox = new HBox();
		nazadVBox.setSpacing(800);
		nazadVBox.setAlignment(Pos.CENTER_LEFT);
		Button nazadBtn = new Button("NAZAD");
        nazadVBox.getChildren().add(nazadBtn);
        nazadVBox.getChildren().add(obrisiBtn);
        
        zakaziTrening = new Button("ZAKAZI TRENING");
        otkaziTrening = new Button("OTKAZI TRENING");
        
        getChildren().add(nazadVBox);
        getChildren().add(imeLabel);
        getChildren().add(zakazanTreningLabel);
        
        setMargin(imeLabel, new Insets(91,0,40,0));

        nazadBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	if(dosaoOd == 0) PrikaziKlijente.prikazi(klijent.idTrener);
            	else PrikaziTreninge.prikazi(klijent.idTrener);
            }
        });
        zakaziTrening.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	ZakaziTrening.prikazi(klijent, dosaoOd);
            }
        });
        otkaziTrening.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	PopupEkran.prikazi("OTKAZATI TRENING ZA " + klijent.ime, 
            		() -> {klijent.zakazanTrening = LocalDate.EPOCH.toString(); Korisnici.save(); KlijentEkran.prikazi(klijent, dosaoOd);},
            		() -> {KlijentEkran.prikazi(klijent, dosaoOd);});
            }
        });
        obrisiBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	PopupEkran.prikazi("OBRISATI KLIJENTA " + klijent.ime, 
            		() -> {int ID=klijent.idTrener; klijent.idTrener = 0; Korisnici.save(); MainMenuTrener.prikazi(ID);},
            		() -> {KlijentEkran.prikazi(klijent, dosaoOd);});
            }
        });
	}
	
	private void init(Korisnik klijent) {
		this.klijent = klijent;

		getChildren().remove(zakaziTrening);
		getChildren().remove(otkaziTrening);
		
		if(!klijent.imaZakazanTrening()) {
			zakazanTreningLabel.setText("Nema zakazan trening!");
			zakazanTreningLabel.setStyle("-fx-background-color: red");
			
			getChildren().add(zakaziTrening);
        }
        else {
        	zakazanTreningLabel.setText("Zakazan " + LocalDate.parse(klijent.zakazanTrening).format(DateTimeFormatter.ofPattern("dd.MM.uuuu.")));
			zakazanTreningLabel.setStyle("-fx-background-color: green");
			
			getChildren().add(otkaziTrening);
        }
		imeLabel.setText(klijent.ime + " " + klijent.prezime + " (" + klijent.id + ")");
	}
	
	public static void prikazi(Korisnik klijent, int dosaoOd) {
		klijentEkran.init(klijent);
		klijentEkran.dosaoOd = dosaoOd;
		HelloApplication.scena.setRoot(klijentEkran);
	}
}
