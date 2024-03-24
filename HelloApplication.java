package teretana;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloApplication extends Application {
	public static String folderPath="C:\\podaci";
	public static String path="C:\\podaci\\korisnici.txt";
	public static String path2="C:\\podaci\\korisnici2.txt";
	public static String tempPath="C:\\podaci\\temp.txt";
	public static String backupPath="G:\\My Drive\\";
	public static Scene scena;
	
    @Override
    public void start(Stage primaryStage) {
    	VBox p = new VBox();
        scena = new Scene(p, 1300, 800);
      
        MainMenu.prikazi();
        
        scena.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setResizable(false);
        primaryStage.setScene(scena);
        primaryStage.show();
    }


}