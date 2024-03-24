package teretana;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class IstekleClanarine extends VBox {
	private static final IstekleClanarine istekleClanarine = new IstekleClanarine();
	
	private TableView<Korisnik> tabela;
	
	private IstekleClanarine() {
		setAlignment(Pos.CENTER);
        setPadding(new Insets(20));
        setSpacing(10);
        
        tabela = new TableView<Korisnik>();
        tabela.setMinHeight(500);
        TableColumn<Korisnik,String> prvaKol = new TableColumn<Korisnik,String>("Ime");
        prvaKol.setMinWidth(320);
        prvaKol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().ime));
        
        TableColumn<Korisnik,String> drugaKol = new TableColumn<Korisnik,String>("Prezime");
        drugaKol.setMinWidth(350);
        drugaKol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().prezime));
 
        TableColumn<Korisnik,String> trecaKol = new TableColumn<Korisnik,String>("ID");
        trecaKol.setMinWidth(150);
        trecaKol.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().id)));
 
        TableColumn<Korisnik,String> cetvrtaKol = new TableColumn<Korisnik,String>("Datum Isteka");
        cetvrtaKol.setMinWidth(300);
        cetvrtaKol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().clanarina.istice.toString()));
        
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
        
        Button nazadBtn = new Button("NAZAD");
        Label naslov = new Label("ISTEKLE CLANARINE");
        naslov.setStyle("-fx-font-size:55px;");
        
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.getChildren().add(nazadBtn);
        hbox.getChildren().add(naslov);
        
        HBox.setMargin(naslov, new Insets(0,0,0,225));
   
        
        getChildren().add(hbox);
        getChildren().add(tabela);
        
        setMargin(nazadBtn,new Insets(0,0,20,0));
        setMargin(tabela, new Insets(0,25,0,25));

        
        nazadBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	MainMenu.prikazi();
            }
        });
        tabela.setOnMousePressed(new EventHandler<MouseEvent>() {
        	@Override
            public void handle(MouseEvent mouseEvent) {
        		tabela.getSelectionModel().clearSelection();
            }
		});
	}
	
	private void init() {
		tabela.getItems().clear();
		for(Korisnik k : Korisnici.lista) {
			if(!k.imaClanarinu()) tabela.getItems().add(k);
        }
		tabela.getItems().sort((Korisnik k, Korisnik k2) -> {
			if(k.clanarina.istice.isEqual(k2.clanarina.istice)) return 0;
			if(k.clanarina.istice.isAfter(k2.clanarina.istice)) return -1;
			return 1;
		});
	}

	public static void prikazi() {
		istekleClanarine.init();
		HelloApplication.scena.setRoot(istekleClanarine);
	}
}
