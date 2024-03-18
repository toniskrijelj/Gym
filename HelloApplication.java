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
	final String path="C:\\podaci\\korisnici.txt";
	
	
    @Override
    public void start(Stage primaryStage){
        try{
            VBox root = new VBox();
            root.setAlignment(Pos.CENTER);
            Button b1 = new Button("NOVI KORISNIK"), b2 = new Button("POSTOJECI KORISNIK");
            root.getChildren().add(b1);
            root.getChildren().add(b2);

            Scene scene = new Scene(root, 500, 500);
            VBox nk = new VBox();
            nk.setAlignment(Pos.CENTER);
            Label lIme = new Label("IME:");
            lIme.setMinWidth(50);
            TextField tIme = new TextField();
            Button dodaj = new Button("DODAJ");
            nk.getChildren().add(lIme);
            nk.getChildren().add(tIme);
            //
            nk.getChildren().add(dodaj);
            
            VBox pk = new VBox();
            pk.setAlignment(Pos.CENTER);
            TextField search = new TextField();
            ListView<String> l = new ListView<>();
            pk.getChildren().add(search);
            pk.getChildren().add(l);
            ArrayList<String> korisnici = new ArrayList<>();
            root.setSpacing(10);
            nk.setSpacing(10);
            pk.setSpacing(10);

            b1.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    scene.setRoot(nk);
                }
            });
            b2.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    try {
                        Scanner fr = new Scanner(new File(path));
                        while(fr.hasNext()){
                            String s = ((fr.nextLine()).split(";"))[0];
                            korisnici.add(s);
                            l.getItems().add(s);
                        }
                        fr.close();
                    } catch (IOException e){
                        //warn
                        System.out.println("Nije uspelo otvaranje fajla");
                        e.printStackTrace();
                    }
                    scene.setRoot(pk);
                }
            });
            search.setOnKeyTyped(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    l.getItems().clear();
                    for(String s : korisnici){
                        if(s.startsWith(search.getText())){
                            l.getItems().add(s);
                        }
                    }
                }
            });
            dodaj.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    try {
                        Scanner fr = new Scanner(new File(path));
                        StringBuilder postojeci= new StringBuilder();
                        while(fr.hasNext()){
                            postojeci.append(fr.nextLine()+'\n');
                        }
                        fr.close();
                        FileWriter fw = new FileWriter(path);
                        if(!tIme.getText().equals("")) {
                            fw.write(postojeci + tIme.getText()+";\n");
                        }
                        else throw new IOException();
                        fw.close();
                    } catch (IOException e){
                        //warn
                        System.out.println("Nije uspelo upisivanje");
                        e.printStackTrace();
                    }
                    scene.setRoot(root);
                }
            });
           // gp.setStyle("-fx-background-color: #000000");
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


}