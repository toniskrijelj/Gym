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
	
	//private ArrayList<Korisnik> korisnici;
	
	private TextField search;
	//private ListView<String> lista;
	
	private PrikaziKorisnike() {
        setAlignment(Pos.CENTER);
        
        TableView<Korisnik> tabela = new TableView<Korisnik>();
        ArrayList<Korisnik> svi = new ArrayList<>();
        svi.add(new Korisnik("ad", "be", 0));
        svi.add(new Korisnik("ggg", "ee", 0));
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
            	KorisnikEkran.prikazi(tabela.getSelectionModel().getSelectedItem());
            }
		});
        /*lista = new ListView<>();
        lista.setMinHeight(600);
        lista.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
            	//KorisnikEkran.prikazi(lista.getSelectionModel().getSelectedItem());
            }
        });*/
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
                for(Korisnik k : svi){//mora arraylist jer "korisnici" se brisu gore
                	System.out.println(k.getIme());
                    if(k.getIme().startsWith(search.getText())){
                        tabela.getItems().add(k);
                    }
                }
            }
        });
        
        setSpacing(10);
	}
	/*private void ucitaj() {
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
            //TODO warn
            System.out.println("Nije uspelo otvaranje fajla");
            e.printStackTrace();
        }
	}*/

	public static void prikazi() {
		//prikaziKorisnike.ucitaj();
		HelloApplication.scena.setRoot(prikaziKorisnike);
	}
}
