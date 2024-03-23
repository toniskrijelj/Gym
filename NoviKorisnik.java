package teretana;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class NoviKorisnik extends VBox{

	private static final NoviKorisnik noviKorisnik = new NoviKorisnik();
	
	private TextField imeField, prezimeField;
	
	private NoviKorisnik() {
        setAlignment(Pos.CENTER);
        setPadding(new Insets(50,50,50,50));
        setSpacing(10);
        
        VBox nazadVBox = new VBox();
        nazadVBox.setAlignment(Pos.TOP_LEFT);
        
        Button nazadBtn = new Button("NAZAD");
        nazadVBox.getChildren().add(nazadBtn);
 
        Label imeLabel = new Label("IME:");
        Label prezimeLabel = new Label("PREZIME:");
        imeLabel.setMinWidth(50);
        prezimeLabel.setMinWidth(50);
        
        imeField = new TextField();
        prezimeField = new TextField();
        
        Button dodaj = new Button("DODAJ");
  
    
        getChildren().add(nazadVBox);
        getChildren().add(imeLabel);
        getChildren().add(imeField);
        getChildren().add(prezimeLabel);
        getChildren().add(prezimeField);
        getChildren().add(dodaj);
        
        setMargin(nazadBtn, new Insets(0,0,50,0));
        setMargin(imeField, new Insets(0,100,50,100));
        setMargin(prezimeField, new Insets(0,100,50,100));

        imeField.setTextFormatter(Utilities.TextFormatter());
        prezimeField.setTextFormatter(Utilities.TextFormatter());
        
        nazadBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                MainMenu.prikazi();
            }
        });
        dodaj.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	if(imeField.getText().equals("") || prezimeField.getText().equals("")) {
            		PopupEkran.prikazi("MORA SE UNETI I IME I PREZIME\nUNELI STE: " + imeField.getText() + " " + prezimeField.getText(), 
            				() -> NoviKorisnik.prikazi());
            	}
            	else {
            		PopupEkran.prikazi("SIGURNO DODATI KORISNIKA: " + imeField.getText() + " " + prezimeField.getText() + "?",
            			() -> {
	            			int id = Korisnici.dodaj(imeField.getText(), prezimeField.getText());
	    	            	PopupEkran.prikazi("ID KORISNIKA " + imeField.getText() + " " + prezimeField.getText() + " JE: " + id, () -> {
	    	            		MainMenu.prikazi();
	    	            	});}, 
            			() -> NoviKorisnik.prikazi());
            	}
            }
        });
	}
	
	private void init() {
		imeField.setText("");
		prezimeField.setText("");
	}
	
	public static void prikazi() {
		noviKorisnik.init();
		HelloApplication.scena.setRoot(noviKorisnik);
	}
}
