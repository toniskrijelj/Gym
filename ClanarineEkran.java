package teretana;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ClanarineEkran extends VBox{
	private static final ClanarineEkran clanarineEkran = new ClanarineEkran();
	
	
	private ClanarineEkran(){
		setAlignment(Pos.CENTER);
		setSpacing(10);
		
		
        Button btn = new Button("NAZAD");
        Button tip1 = new Button("");
        Button tip2 = new Button("");
        Button tip3 = new Button("");
        Button tip4 = new Button("");
        getChildren().add(btn);
        getChildren().add(tip1);
        getChildren().add(tip2);
        getChildren().add(tip3);
        getChildren().add(tip4);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	PrikaziKorisnike.prikazi();
            }
        });
        tip1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	PrikaziKorisnike.prikazi();
            }
        });
        tip2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	PrikaziKorisnike.prikazi();
            }
        });
        tip3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	PrikaziKorisnike.prikazi();
            }
        });
        tip4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	PrikaziKorisnike.prikazi();
            }
        });
        
        
	}
	
	
	
	public static void prikazi(Korisnik korisnik) {
		
		HelloApplication.scena.setRoot(clanarineEkran);
	}
}

