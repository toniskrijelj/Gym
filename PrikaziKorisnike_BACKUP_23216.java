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
import javafx.scene.control.skin.TableHeaderRow;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class PrikaziKorisnike extends VBox{

	private static final PrikaziKorisnike prikaziKorisnike = new PrikaziKorisnike();
	
	private TextField search;
<<<<<<< HEAD
	private ListView<String> lista;
=======
	static //private ListView<String> lista;
	TableView<Korisnik> tabela;
>>>>>>> origin/main
	
	private PrikaziKorisnike() {
        setAlignment(Pos.CENTER);
        
<<<<<<< HEAD
        search = new TextField();
        
        lista = new ListView<>();
=======
        tabela = new TableView<Korisnik>();
        ArrayList<Korisnik> svi = new ArrayList<>();
        svi.add(new Korisnik("ad", "be",0));
        svi.add(new Korisnik("ggg", "ee",0));
        ObservableList<Korisnik> korisnici = FXCollections.observableArrayList(svi);
        TableColumn prvaKol = new TableColumn("Ime");
        prvaKol.setMinWidth(200);
        prvaKol.setCellValueFactory(
                new PropertyValueFactory<Korisnik, String>("ime"));
 
        TableColumn drugaKol = new TableColumn("Prezime");
        drugaKol.setMinWidth(200);
        drugaKol.setCellValueFactory(
                new PropertyValueFactory<Korisnik, String>("prezime"));
 
        TableColumn trecaKol = new TableColumn("ID");
        trecaKol.setMinWidth(200);
        trecaKol.setCellValueFactory(
                new PropertyValueFactory<Korisnik, String>("id"));
 
        tabela.setItems(korisnici);
        tabela.getColumns().addAll(prvaKol, drugaKol, trecaKol);
        
        
        
        search = new TextField();
        
        tabela.setOnMouseClicked(new EventHandler<MouseEvent>() {
        	@Override
            public void handle(MouseEvent mouseEvent) {
        		if(tabela.getSelectionModel().getSelectedItem() != null) {
        			KorisnikEkran.prikazi(tabela.getSelectionModel().getSelectedItem());
        		}
        		
            }
		});
        /*lista = new ListView<>();
>>>>>>> origin/main
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
<<<<<<< HEAD
                lista.getItems().clear();
                for(Korisnik k : Korisnici.lista) {
                	System.out.println(k.ime);
                    if(k.ime.startsWith(search.getText())){
                        lista.getItems().add(k.ime+" "+k.prezime+" "+k.id);
=======
                tabela.getItems().clear();
                for(Korisnik k : svi){//mora arraylist jer "korisnici" se brisu gore
                    if(k.getIme().startsWith(search.getText())){
                        tabela.getItems().add(k);
>>>>>>> origin/main
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
		tabela.getSelectionModel().clearSelection();
		tabela.applyCss();
        TableHeaderRow header = (TableHeaderRow) tabela.lookup("TableHeaderRow");
        header.setMouseTransparent(true);
	}
}