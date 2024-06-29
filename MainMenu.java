package teretana;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainMenu extends VBox{

	private static final MainMenu mainMenu = new MainMenu();
	
	private MainMenu(){
		setAlignment(Pos.CENTER);
		setPadding(new Insets(50,50,50,50));
        setSpacing(20);
		
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.TOP_LEFT);
		
		Label naslov = new Label("TERETANA");
		naslov.setStyle("-fx-font-size:75px");
		Button odjavaBtn = new Button("ODJAVA");
		
        Button noviBtn = new Button("NOVI KORISNIK"), postojeciBtn = new Button("POSTOJECI KORISNIK");
        Button istekliBtn = new Button("PREGLED ISTEKLIH CLANARINA");
        
        hbox.getChildren().add(odjavaBtn);
        hbox.getChildren().add(naslov);
        
        HBox.setMargin(naslov, new Insets(0,0,0,220));
        
        getChildren().add(hbox);
        getChildren().add(noviBtn);
        getChildren().add(postojeciBtn);
        getChildren().add(istekliBtn);
        
        setMargin(naslov,new Insets(0,0,100,0));
        setMargin(odjavaBtn, new Insets(0,0,50,0));
        
        odjavaBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	Login.prikazi();
            }
        });
        
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
        
        istekliBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                IstekleClanarine.prikazi();
            }
        });
	}
	
	public static void prikazi() {
		HelloApplication.scena.setRoot(mainMenu);
	}
}
