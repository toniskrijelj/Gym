package teretana;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

public class PrikaziKorisnike extends VBox{

	private static final PrikaziKorisnike prikaziKorisnike = new PrikaziKorisnike();
	
	private ArrayList<String> korisnici;
	
	private TextField search;
	private ListView<String> lista;
	
	private PrikaziKorisnike() {
        setAlignment(Pos.CENTER);
        
        search = new TextField();
        lista = new ListView<>();
        Button btn = new Button("NAZAD");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	MainMenu.prikazi();
            }
        });
        
        getChildren().add(btn);
        getChildren().add(search);
        getChildren().add(lista);
        
        korisnici = new ArrayList<>();
        
        search.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                lista.getItems().clear();
                for(String s : korisnici){
                    if(s.startsWith(search.getText())){
                        lista.getItems().add(s);
                    }
                }
            }
        });
        
        setSpacing(10);
	}
	private void ucitaj() {
		try {
			search.clear();
			korisnici.clear();
			lista.getItems().clear();
		
            Scanner fr = new Scanner(new File(HelloApplication.path));
            while(fr.hasNext()){
                String s = ((fr.nextLine()).split(";"))[0];
                korisnici.add(s);
                lista.getItems().add(s);
            }
            fr.close();
        } catch (IOException e){
            //warn
            System.out.println("Nije uspelo otvaranje fajla");
            e.printStackTrace();
        }
	}

	public static void prikazi() {
		prikaziKorisnike.ucitaj();
		HelloApplication.scena.setRoot(prikaziKorisnike);
	}
}
