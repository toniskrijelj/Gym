package teretana;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class NoviKorisnik extends VBox{

	private static final NoviKorisnik noviKorisnik = new NoviKorisnik();
	private NoviKorisnik() {
        setAlignment(Pos.CENTER);
        Label lIme = new Label("IME:");
        lIme.setMinWidth(50);
        TextField tIme = new TextField();
        Button dodaj = new Button("DODAJ");
        Button nazad = new Button("NAZAD");
        getChildren().add(nazad);
        getChildren().add(lIme);
        getChildren().add(tIme);
        getChildren().add(dodaj);
        setSpacing(10);

        dodaj.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	dodajKorisnika(tIme.getText());
                MainMenu.prikazi();
            }
        });
        nazad.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                MainMenu.prikazi();
            }
        });
	}

	private void dodajKorisnika(String korisnik) {
		try {
            Scanner fr = new Scanner(new File(HelloApplication.path));
            StringBuilder postojeci= new StringBuilder();
            while(fr.hasNext()){
                postojeci.append(fr.nextLine()+'\n');
            }
            fr.close();
            FileWriter fw = new FileWriter(HelloApplication.path);
            if(!korisnik.equals("")) {
                fw.write(postojeci + korisnik+";\n");
            }
            else throw new IOException();
            fw.close();
        } catch (IOException e){
            //warn
            System.out.println("Nije uspelo upisivanje");
            e.printStackTrace();
        }
	}
	
	public static void prikazi() {
		HelloApplication.scena.setRoot(noviKorisnik);
	}
}
