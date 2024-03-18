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

public class NoviKorisnik extends VBox implements IEkran{

	public NoviKorisnik() {
        setAlignment(Pos.CENTER);
        Label lIme = new Label("IME:");
        lIme.setMinWidth(50);
        TextField tIme = new TextField();
        Button dodaj = new Button("DODAJ");
        getChildren().add(lIme);
        getChildren().add(tIme);
        getChildren().add(dodaj);
        setSpacing(10);

        dodaj.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	dodajKorisnika(tIme.getText());
                HelloApplication.mainMenu.prikazi();
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
	
	@Override
	public void prikazi() {
		HelloApplication.scena.setRoot(this);
	}
}
