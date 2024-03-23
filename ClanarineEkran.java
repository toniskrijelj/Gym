package teretana;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ClanarineEkran extends VBox{
	private static final ClanarineEkran clanarineEkran = new ClanarineEkran();
	
	private Korisnik korisnik;
	private Label naslov;
	
	private ClanarineEkran(){
		setAlignment(Pos.CENTER);
		setPadding(new Insets(20,20,20,20));
        setSpacing(20);
		
		VBox nazadVBox = new VBox();
		nazadVBox.setAlignment(Pos.CENTER_LEFT);
        Button nazadBtn = new Button("NAZAD");
        nazadVBox.getChildren().add(nazadBtn);
        
        naslov = new Label();
        naslov.setStyle("-fx-font-size:60px;");

        Button[] tipBtn = new Button[4];
        tipBtn[0] = new Button("DAN");
        tipBtn[1] = new Button("MESEC 12 TRENINGA");
        tipBtn[2] = new Button("MESEC NEOGRANICENO");
        tipBtn[3] = new Button("GODISNJA");
        
        getChildren().add(nazadVBox);
        getChildren().add(naslov);
        for(int i=0; i<4; i++)  getChildren().add(tipBtn[i]);

        setMargin(naslov,new Insets(100,0,50,0));
        
        nazadBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	KorisnikEkran.prikazi(korisnik);
            }
        });
        for(int i=0; i<4; i++) {
	    	final int br=i;
	    	tipBtn[i].setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent actionEvent) {
	            	PopupEkran.prikazi(buttonText(br), 
	            		() -> {
		            		korisnik.clanarina = new Clanarina(br);
		            		Korisnici.save();
		            		PopupEkran.prikazi("USPESNO STE UPLATILI CLANARINU " + Clanarina.opis[br] + " ZA\n" 
		            							+ korisnik.ime + " " + korisnik.prezime + " " + korisnik.id, 
		            							() -> KorisnikEkran.prikazi(korisnik));
		            	}, 
	            		() -> ClanarineEkran.prikazi(korisnik)
	            	);
	            }
	        });
	    }
	}
	
	private String buttonText(int i) {
		return "UPLATITI " + Clanarina.opis[i] + " ZA " + korisnik.ime + " " + korisnik.prezime + " " + korisnik.id + " ?";
	}
	
	
	public static void prikazi(Korisnik korisnik) {
		clanarineEkran.korisnik = korisnik;
		clanarineEkran.naslov.setText("UPLATA ZA: " + korisnik.ime + " " + korisnik.prezime + " " + korisnik.id);
		HelloApplication.scena.setRoot(clanarineEkran);
	}
}

