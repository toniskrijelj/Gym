package teretana;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ZakaziTrening extends VBox{

	private static final ZakaziTrening zakaziTrening = new ZakaziTrening();
	
	private Label naslov;
	private Korisnik klijent;
	private TextField datumField;
	private int dosaoOd;
	
	private ZakaziTrening() {
        setAlignment(Pos.CENTER);
        setPadding(new Insets(50,50,50,50));
        setSpacing(10);
        
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.TOP_LEFT);
        
        Button nazadBtn = new Button("NAZAD");
        naslov = new Label("ZAKAZI TRENING ZA ");
        naslov.setStyle("-fx-font-size:65px;");
        
        hbox.getChildren().add(nazadBtn);
        hbox.getChildren().add(naslov);
        
        HBox.setMargin(naslov, new Insets(0,0,0,220));
 
        Label datumLabel = new Label("DATUM:");
        datumLabel.setMinWidth(50);
        
        datumField = new TextField();
        
        Button zakazi = new Button("ZAKAZI");
  
    
        getChildren().add(hbox);
        getChildren().add(datumLabel);
        getChildren().add(datumField);
        getChildren().add(zakazi);
        
        setMargin(nazadBtn, new Insets(0,0,50,0));
        setMargin(datumField, new Insets(0,100,50,100));

        datumField.setTextFormatter(Utilities.DateFormatter());
        
        nazadBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                KlijentEkran.prikazi(klijent, dosaoOd);
            }
        });
        zakazi.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	LocalDate date = null;
            	try {
            		date = LocalDate.parse(datumField.getText(), DateTimeFormatter.ofPattern("d.M.yyyy."));
            	}
            	catch (Exception e) 
            	{
            		System.out.println(e.getMessage());
            	}
            	if(date == null) {
            		PopupEkran.prikazi("MORA SE UNETI ISPRAVAN DATUM\nUNELI STE: " + datumField.getText(), 
            				() -> ZakaziTrening.prikazi(klijent, dosaoOd));
            	}
            	else {
            		final LocalDate date2 = date;
            		PopupEkran.prikazi("SIGURNO ZAKAZATI TRENING: " + datumField.getText() + " za " + klijent.ime + "?",
            			() -> {
            				klijent.zakazanTrening = date2.toString();
            				Korisnici.save();
	    	            	PopupEkran.prikazi("USPESNO", () -> {
	    	            		KlijentEkran.prikazi(klijent, dosaoOd);
	    	            	});}, 
            			() -> ZakaziTrening.prikazi(klijent, dosaoOd));
            	}
            }
        });
	}
	
	private void init(Korisnik klijent) {
		this.klijent = klijent;
		datumField.setText("");
		naslov.setText("ZAKAZI TRENING ZA " + klijent.ime);
	}
	
	public static void prikazi(Korisnik klijent, int dosaoOd) {
		zakaziTrening.dosaoOd = dosaoOd;
		zakaziTrening.init(klijent);
		HelloApplication.scena.setRoot(zakaziTrening);
	}
}