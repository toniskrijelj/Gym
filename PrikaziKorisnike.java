package teretana;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.control.TextFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
import java.util.function.UnaryOperator;

public class PrikaziKorisnike extends VBox{

	private static final PrikaziKorisnike prikaziKorisnike = new PrikaziKorisnike();
	
	private TextField search;
	TableView<Korisnik> tabela;
	private static LocalTime usao;
	
	private PrikaziKorisnike() {
        setAlignment(Pos.CENTER);
        this.setPadding(new Insets(20,20,20,20));
        
        tabela = new TableView<Korisnik>();
        tabela.setMinHeight(700);
        //ObservableList<Korisnik> korisnici = FXCollections.observableArrayList();
        TableColumn prvaKol = new TableColumn("Ime");
        prvaKol.setMinWidth(325);
        prvaKol.setCellValueFactory(
                new PropertyValueFactory<Korisnik, String>("ime"));
 
        TableColumn drugaKol = new TableColumn("Prezime");
        drugaKol.setMinWidth(400);
        drugaKol.setCellValueFactory(
                new PropertyValueFactory<Korisnik, String>("prezime"));
 
        TableColumn trecaKol = new TableColumn("ID");
        trecaKol.setMinWidth(200);
        trecaKol.setCellValueFactory(
                new PropertyValueFactory<Korisnik, String>("id"));
 
        //tabela.setItems(korisnici);
        tabela.getColumns().addAll(prvaKol, drugaKol, trecaKol);
        
        
        
        search = new TextField();
        UnaryOperator<TextFormatter.Change> doubleFilter = change -> {
            String input = change.getText();

            if ((input.matches("[a-zA-Z*]")) || change.isDeleted()) {
                return change;
            }
            return null;
        };
        search.setTextFormatter(new TextFormatter<String> (doubleFilter));
        search.setPromptText("Trazi po imenu:");
        search.setPadding(new Insets(10,10,10,10));
        tabela.setOnMousePressed(new EventHandler<MouseEvent>() {
        	@Override
            public void handle(MouseEvent mouseEvent) {
        		if(usao.plusSeconds(1).isBefore(LocalTime.now()) && tabela.getSelectionModel().getSelectedItem() != null) {
        			KorisnikEkran.prikazi(tabela.getSelectionModel().getSelectedItem());
        		}
        		prikaziKorisnike.tabela.getSelectionModel().clearSelection();
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
        VBox NAZAD = new VBox();
        NAZAD.setAlignment(Pos.CENTER_LEFT);
        NAZAD.getChildren().add(btn);
   
        
        getChildren().add(NAZAD);
        setMargin(btn,new Insets(0,0,20,0));
        getChildren().add(search);
        setMargin(search, new Insets(0,25,0,25));
        getChildren().add(tabela);
        setMargin(tabela, new Insets(0,25,0,25));
        
        

        
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
        usao = LocalTime.now();
	}
}