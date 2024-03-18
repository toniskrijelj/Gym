package teretana;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class HelloApplication extends Application {
	public static String path="C:\\podaci\\korisnici.txt";
	public static Scene scena;
	public static MainMenu mainMenu;
	public static NoviKorisnik noviKorisnik;
	public static PrikaziKorisnike prikaziKorisnike;
	
    @Override
    public void start(Stage primaryStage){
    	mainMenu = new MainMenu();
    	noviKorisnik = new NoviKorisnik();
    	prikaziKorisnike = new PrikaziKorisnike();
    	
        Scene scene = new Scene(mainMenu, 500, 500);
        scena=scene;
      
       // gp.setStyle("-fx-background-color: #000000");
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}