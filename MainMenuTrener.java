package teretana;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainMenuTrener extends VBox{

	private static final MainMenuTrener mainMenuTrener = new MainMenuTrener();
	private static int broj;
	
	private MainMenuTrener(){
		setAlignment(Pos.CENTER);
		setPadding(new Insets(50,50,50,50));
        setSpacing(20);
		
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.TOP_LEFT);
		
        Button odjavaBtn = new Button("ODJAVA");
        Label naslov = new Label("TERETANA");
		naslov.setStyle("-fx-font-size:75px");
		
        Button noviBtn = new Button("NOVI KLIJENT");
        Button prikazBtn = new Button("PREGLED KLIJENATA");
        Button treninziBtn = new Button("PREGLED TRENINGA");

        hbox.getChildren().add(odjavaBtn);
        hbox.getChildren().add(naslov);
        
        HBox.setMargin(naslov, new Insets(0,0,0,220));
        
        getChildren().add(hbox);
        getChildren().add(noviBtn);
        getChildren().add(prikazBtn);
        getChildren().add(treninziBtn);
        
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
            	NoviKlijent.prikazi(broj);
            }
        });
        
        prikazBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                PrikaziKlijente.prikazi(broj);
            }
        });
        
        treninziBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                PrikaziTreninge.prikazi(broj);
            }
        });
	}
	
	public static void prikazi(int broj) {
		MainMenuTrener.broj = broj;
		HelloApplication.scena.setRoot(mainMenuTrener);
	}
}
