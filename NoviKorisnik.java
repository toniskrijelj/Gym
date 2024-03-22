package teretana;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.function.UnaryOperator;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.VBox;

public class NoviKorisnik extends VBox{

	private static final NoviKorisnik noviKorisnik = new NoviKorisnik();
	private static TextField tIme, tPrezime;
	
	private NoviKorisnik() {
        setAlignment(Pos.CENTER);
        setPadding(new Insets(50,50,50,50));
        VBox NAZAD = new VBox();
        NAZAD.setAlignment(Pos.TOP_LEFT);
 
        Label lIme = new Label("IME:");
        lIme.setMinWidth(50);
        lIme.setPadding(new Insets(0,20,0,20));
        Label lPrezime = new Label("PREZIME:");
        lPrezime.setMinWidth(50);
        lPrezime.setPadding(new Insets(0,20,0,20));
        tIme = new TextField();
        tPrezime = new TextField();
        Button dodaj = new Button("DODAJ");
        Button nazad = new Button("NAZAD");
        NAZAD.getChildren().add(nazad);
        getChildren().add(NAZAD);
        getChildren().add(lIme);
        getChildren().add(tIme);
        getChildren().add(lPrezime);
        getChildren().add(tPrezime);
        getChildren().add(dodaj);
        
        setMargin(nazad,new Insets(0,0,50,0));
        setMargin(tIme,new Insets(0,100,50,100));
        setMargin(tPrezime, new Insets(0,100,50,100));
        
        setSpacing(10);
        UnaryOperator<TextFormatter.Change> doubleFilter = change -> {
            String input = change.getText();

            if ((input.matches("[a-zA-Z*]")) || change.isDeleted()) {
                return change;
            }
            return null;
        };
        tIme.setTextFormatter(new TextFormatter<String> (doubleFilter));
        tPrezime.setTextFormatter(new TextFormatter<String> (doubleFilter));
        dodaj.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	int id=Korisnici.dodaj(tIme.getText(), tPrezime.getText());
            	PromptEkran.prikazi("ID KORISNIKA " + tIme.getText() + " " + tPrezime.getText() + " JE: " + id, () -> {
            		MainMenu.prikazi();
            	});
            }
        });
        nazad.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                MainMenu.prikazi();
            }
        });
	}
	
	public static void prikazi() {
		tIme.setText("");
		tPrezime.setText("");
		HelloApplication.scena.setRoot(noviKorisnik);
	}
}
