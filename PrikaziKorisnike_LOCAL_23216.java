package teretana;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class PrikaziKorisnike extends VBox{

	private static final PrikaziKorisnike prikaziKorisnike = new PrikaziKorisnike();
	
	private TextField search;
	private ListView<String> lista;
	
	private PrikaziKorisnike() {
        setAlignment(Pos.CENTER);
        
        search = new TextField();
        
        lista = new ListView<>();
        lista.setMinHeight(600);
        lista.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
            	String korisnik = lista.getSelectionModel().getSelectedItem();
            	if(korisnik == null || korisnik.equals("")) return;
            	int id = Integer.parseInt(korisnik.split(" ")[2]);
            	KorisnikEkran.prikazi(Korisnici.find(id));
            }
        });
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
        
        
        search.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                lista.getItems().clear();
                for(Korisnik k : Korisnici.lista) {
                	System.out.println(k.ime);
                    if(k.ime.startsWith(search.getText())){
                        lista.getItems().add(k.ime+" "+k.prezime+" "+k.id);
                    }
                }
            }
        });
        
        setSpacing(10);
	}
	private void ucitaj() {
		search.clear();
		lista.getItems().clear();
		for(Korisnik k : Korisnici.lista) {
        	lista.getItems().add(k.ime+" "+k.prezime+" "+k.id);
        }
	}

	public static void prikazi() {
		prikaziKorisnike.ucitaj();
		HelloApplication.scena.setRoot(prikaziKorisnike);
	}
}
