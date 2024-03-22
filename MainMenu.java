package teretana;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MainMenu extends VBox{

	private static final MainMenu mainMenu = new MainMenu();
	
	private MainMenu(){
		setAlignment(Pos.CENTER);
		Label naslov = new Label("TERETANA ENERGYFIT FITNESS");
		naslov.setPadding(new Insets(0,20,0,20));
		naslov.setStyle("-fx-font-size:75px");
        Button b1 = new Button("NOVI KORISNIK"), b2 = new Button("POSTOJECI KORISNIK");
        getChildren().add(naslov);
        getChildren().add(b1);
        getChildren().add(b2);
        setMargin(naslov,new Insets(0,0,100,0));
        setSpacing(20);
        
        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	NoviKorisnik.prikazi();
            }
        });
        
        b2.setOnAction(new EventHandler<ActionEvent>() {
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
