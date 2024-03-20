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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class HelloApplication extends Application {
	public static String path="C:\\podaci\\korisnici.txt";
	public static String path2="C:\\podaci\\korisnici.txt";
	public static String tempPath="C:\\podaci\\temp.txt";
	public static Scene scena;
	
    @Override
    public void start(Stage primaryStage){
    	
    	/*try {
			FileWriter fw = new FileWriter("G:\\My Drive\\podaci.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/ 
    	/*LocalDate date = java.time.LocalDate.now();
    	LocalDate date2 = java.time.LocalDate.now();
    	date=date.plusDays(3);*/

    	VBox p = new VBox();
        scena = new Scene(p, 1000, 1000);
      
        MainMenu.prikazi();
        
        scena.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scena);
        primaryStage.show();
    }


}