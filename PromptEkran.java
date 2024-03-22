package teretana;

import java.time.LocalTime;
import java.util.function.Function;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PromptEkran extends VBox{
	private static final PromptEkran promptEkran = new PromptEkran();
	
	private static Label poruka;
	private static Button yes,no;
	
	private static Runnable noAction, yesAction, okAction;
	private static LocalTime usao;
	private static HBox hbox;
	private static Button okButton;
	private PromptEkran() {
		setAlignment(Pos.CENTER);
		setSpacing(10);
		poruka = new Label();
		poruka.setPadding(new Insets(0,20,0,20));
		poruka.setStyle("-fx-font-size:45px");
		
		okButton = new Button("OK\n");
		okButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent actionEvent) {
            	if(usao.plusSeconds(1).isBefore(LocalTime.now())) okAction.run();
            }
        });
		
		yes = new Button("DA\n");
		yes.setStyle("-fx-background-color: green");
		yes.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent actionEvent) {
            	if(usao.plusSeconds(1).isBefore(LocalTime.now())) yesAction.run();
            }
        });
		no = new Button("NE\n");
		no.setStyle("-fx-background-color: red");
		no.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent actionEvent) {
            	if(usao.plusSeconds(1).isBefore(LocalTime.now())) noAction.run();
            }
        });
		
		hbox = new HBox();
		hbox.getChildren().add(yes);
		hbox.getChildren().add(no);
		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(200);
		
		getChildren().add(poruka);
		
		setMargin(poruka, new Insets(0,0,30,0));
	}
	
	public static void prikazi(String poruka, Runnable yesAction, Runnable noAction) {
		usao = LocalTime.now();
		PromptEkran.poruka.setText(poruka);
		PromptEkran.yesAction=yesAction;
		PromptEkran.noAction=noAction;
		

		promptEkran.getChildren().remove(hbox);
		promptEkran.getChildren().remove(okButton);
		
		promptEkran.getChildren().add(hbox);
		
		HelloApplication.scena.setRoot(promptEkran);
	}
	
	public static void prikazi(String poruka, Runnable okAction) {
		usao = LocalTime.now();
		PromptEkran.poruka.setText(poruka);
		PromptEkran.okAction=okAction;
		
		promptEkran.getChildren().remove(hbox);
		promptEkran.getChildren().remove(okButton);
		
		promptEkran.getChildren().add(okButton);
		
		HelloApplication.scena.setRoot(promptEkran);
	}
}
	