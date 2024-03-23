package teretana;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MainMenu extends VBox{

	private static final MainMenu mainMenu = new MainMenu();
	
	private MainMenu(){
		setAlignment(Pos.CENTER);
		setSpacing(20);
		
		Label naslov = new Label("TERETANA ENERGYFIT FITNESS");
		naslov.setStyle("-fx-font-size:75px");
		
        Button noviBtn = new Button("NOVI KORISNIK"), postojeciBtn = new Button("POSTOJECI KORISNIK");
        
        getChildren().add(naslov);
        getChildren().add(noviBtn);
        getChildren().add(postojeciBtn);
        
        setMargin(naslov,new Insets(0,0,100,0));
        
        noviBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	NoviKorisnik.prikazi();
            }
        });
        
        postojeciBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                PrikaziKorisnike.prikazi();
            }
        });
	}
	
	public static void prikazi() {
		HelloApplication.scena.setRoot(mainMenu);
	}
}
