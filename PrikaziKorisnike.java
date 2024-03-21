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
import javafx.scene.control.Label;
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
	TableView<Korisnik> tabela;
	
	private PrikaziKorisnike() {
        setAlignment(Pos.CENTER);
        
        tabela = new TableView<Korisnik>();

        //ObservableList<Korisnik> korisnici = FXCollections.observableArrayList();
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
 
        //tabela.setItems(korisnici);
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
        
        tabela.setPlaceholder(new Label("Nema rezultata"));
        
        Button btn = new Button("NAZAD");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	MainMenu.prikazi();
            }
        });
        
        getChildren().add(btn);
        getChildren().add(search);
        getChildren().add(tabela);
        
        

        
        search.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                tabela.getItems().clear();
                for(Korisnik k : Korisnici.lista){
                    if(k.getIme().startsWith(search.getText())){
                        tabela.getItems().add(k);
                    }
                }
            }
        });
        
        setSpacing(10);
	}
	private void ucitaj() {
		search.clear();
		tabela.getItems().clear();
		for(Korisnik k : Korisnici.lista) {
			tabela.getItems().add(k);
        }
	}

	public static void prikazi() {
		prikaziKorisnike.ucitaj();
		HelloApplication.scena.setRoot(prikaziKorisnike);
		prikaziKorisnike.tabela.getSelectionModel().clearSelection();
		prikaziKorisnike.tabela.applyCss();
        TableHeaderRow header = (TableHeaderRow) prikaziKorisnike.tabela.lookup("TableHeaderRow");
        header.setMouseTransparent(true);
	}
}