package teretana;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MainMenu extends VBox implements IEkran{
	
	public MainMenu(){
		setAlignment(Pos.CENTER);
        Button b1 = new Button("NOVI KORISNIK"), b2 = new Button("POSTOJECI KORISNIK");
        getChildren().add(b1);
        getChildren().add(b2);
        setSpacing(10);
        
        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	HelloApplication.noviKorisnik.prikazi();
            }
        });
        
        b2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                HelloApplication.prikaziKorisnike.prikazi();
            }
        });
	}

	@Override
	public void prikazi() {
		HelloApplication.scena.setRoot(this);
	}
}
