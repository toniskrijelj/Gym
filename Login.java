package teretana;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Login extends VBox{

	private static final Login login = new Login();
	
	private TextField imeField, sifraField;
	
	private Login(){
		setAlignment(Pos.CENTER);
        setPadding(new Insets(50,50,50,50));
        setSpacing(10);
 
        Label imeLabel = new Label("IME:");
        Label sifraLabel = new Label("SIFRA:");
        imeLabel.setMinWidth(50);
        sifraLabel.setMinWidth(50);
        
        imeField = new TextField();
        sifraField = new TextField();
        
        Button prijava = new Button("PRIJAVA");
  
    
        getChildren().add(imeLabel);
        getChildren().add(imeField);
        getChildren().add(sifraLabel);
        getChildren().add(sifraField);
        getChildren().add(prijava);
        
        setMargin(imeField, new Insets(0,100,50,100));
        setMargin(sifraField, new Insets(0,100,50,100));

        imeField.setTextFormatter(Utilities.TextFormatter());
        sifraField.setTextFormatter(Utilities.TextFormatter());
        
        prijava.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	int status = Sifre.login(imeField.getText(), sifraField.getText());
            	if(status == 0) {
            		MainMenu.prikazi();
            	}
            	else if(status > 0) {
            		MainMenuTrener.prikazi(status);
            	}
            	else {
            		PopupEkran.prikazi("NEUSPESAN LOGIN", 
            				() -> Login.prikazi());
            	}
            }
        });
	}
	
	private void init() {
		sifraField.setText("");
	}
	
	public static void prikazi() {
		login.init();
		HelloApplication.scena.setRoot(login);
	}
}
