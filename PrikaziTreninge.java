package teretana;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PrikaziTreninge extends VBox{

	private static final PrikaziTreninge prikaziTreninge = new PrikaziTreninge();
	
	private static int broj;
	
	private TextField search;
	private TableView<Korisnik> tabela;
	private ClickDelay clickDelay;
	
	private PrikaziTreninge() {
        setAlignment(Pos.CENTER);
        setPadding(new Insets(20));
        setSpacing(10);
        
        tabela = new TableView<Korisnik>();
        tabela.setMinHeight(500);
        TableColumn<Korisnik,String> prvaKol = new TableColumn<Korisnik,String>("Ime");
        prvaKol.setMinWidth(325);
        prvaKol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().ime));
        
        TableColumn<Korisnik,String> drugaKol = new TableColumn<Korisnik,String>("Prezime");
        drugaKol.setMinWidth(400);
        drugaKol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().prezime));
 
        TableColumn<Korisnik,String> trecaKol = new TableColumn<Korisnik,String>("ID");
        trecaKol.setMinWidth(150);
        trecaKol.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().id)));
 
        TableColumn<Korisnik,String> cetvrtaKol = new TableColumn<Korisnik,String>("Datum");
        cetvrtaKol.setMinWidth(325);
        cetvrtaKol.setCellValueFactory(cellData -> new SimpleStringProperty(LocalDate.parse(cellData.getValue().zakazanTrening).format(DateTimeFormatter.ofPattern("dd.MM.uuuu."))));
 
        
        tabela.getColumns().add(prvaKol);
        tabela.getColumns().add(drugaKol);
        tabela.getColumns().add(trecaKol);
        tabela.getColumns().add(cetvrtaKol);
        tabela.getColumns().forEach(column -> {
        	column.setReorderable(false);
        	column.setResizable(false);
        	column.setSortable(false);
        });
        tabela.setPlaceholder(new Label("Nema rezultata"));
        
        search = new TextField();
        search.setTextFormatter(Utilities.TextFormatter());
        search.setPromptText("Trazi po imenu:");
        search.setPadding(new Insets(10,10,10,10));
     
        
        Button nazadBtn = new Button("NAZAD");
        Label naslov = new Label("TRENINZI");
        naslov.setStyle("-fx-font-size:65px;");
        
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER_LEFT);
        
        hbox.getChildren().add(nazadBtn);
        hbox.getChildren().add(naslov);
        
        HBox.setMargin(naslov, new Insets(0,0,0,300));
        
        getChildren().add(hbox);
        getChildren().add(search);
        getChildren().add(tabela);
        
        setMargin(nazadBtn,new Insets(0,0,20,0));
        setMargin(search, new Insets(0,25,0,25));
        setMargin(tabela, new Insets(0,25,0,25));

        
        nazadBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	MainMenuTrener.prikazi(broj);
            }
        });
        
        search.setOnKeyTyped(new EventHandler<KeyEvent>() {
        	@Override
        	public void handle(KeyEvent keyEvent) {
        		popuniListu();
        	}
        });
        tabela.setOnMousePressed(new EventHandler<MouseEvent>() {
        	@Override
            public void handle(MouseEvent mouseEvent) {
        		if(clickDelay.clickable() && tabela.getSelectionModel().getSelectedItem() != null) {
        			KlijentEkran.prikazi(tabela.getSelectionModel().getSelectedItem(), 1);
        		}
        		tabela.getSelectionModel().clearSelection();
            }
		});
	}
	
	private void init() {
		search.clear();
		popuniListu();
		clickDelay = new ClickDelay(0.45);
	}
	
	private void popuniListu() {
		tabela.getItems().clear();
		for(Korisnik k : Korisnici.lista){
			if(k.imaZakazanTrening() && k.idTrener == broj && k.ime.startsWith(search.getText())){
				tabela.getItems().add(k);
			}
		}
	}

	public static void prikazi(int broj) {
		PrikaziTreninge.broj = broj;
		prikaziTreninge.init();
		HelloApplication.scena.setRoot(prikaziTreninge);
	}
}