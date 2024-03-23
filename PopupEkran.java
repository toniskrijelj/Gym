package teretana;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class PopupEkran extends VBox{
	private static final PopupEkran popupEkran = new PopupEkran();
	
	private Label poruka;
	private Button yesButton, noButton, okButton;
	private Runnable noAction, yesAction, okAction;
	private HBox hbox;
	private ClickDelay clickDelay;
	
	private PopupEkran() {
		setAlignment(Pos.CENTER);
		setSpacing(10);
		
		poruka = new Label();
		poruka.setStyle("-fx-font-size:45px");
		poruka.setTextAlignment(TextAlignment.CENTER);
		
		okButton = new Button("OK\n");
		okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	if(clickDelay.clickable()) okAction.run();
            }
        });
		
		yesButton = new Button("DA\n");
		yesButton.setId("yesButton");
		yesButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	if(clickDelay.clickable()) yesAction.run();
            }
        });
		
		noButton = new Button("NE\n");
		noButton.setId("noButton");
		noButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	if(clickDelay.clickable()) noAction.run();
            }
        });
		
		hbox = new HBox();
		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(200);
		
		hbox.getChildren().add(yesButton);
		hbox.getChildren().add(noButton);
		
		getChildren().add(poruka);
		
		setMargin(poruka, new Insets(0,0,30,0));
	}
	
	private void init(String poruka, Runnable yesAction, Runnable noAction) {
		clickDelay = new ClickDelay(1);

		this.poruka.setText(poruka);
		this.yesAction = yesAction;
		this.noAction = noAction;
		

		getChildren().remove(hbox);
		getChildren().remove(okButton);
		
		getChildren().add(hbox);
	}
	
	private void init(String poruka, Runnable okAction) {
		clickDelay = new ClickDelay(1);
		
		this.poruka.setText(poruka);
		this.okAction = okAction;
		
		getChildren().remove(hbox);
		getChildren().remove(okButton);
		
		getChildren().add(okButton);
		
	}
	
	public static void prikazi(String poruka, Runnable yesAction, Runnable noAction) {
		popupEkran.init(poruka, yesAction, noAction);
		HelloApplication.scena.setRoot(popupEkran);
	}
	
	public static void prikazi(String poruka, Runnable okAction) {
		popupEkran.init(poruka, okAction);
		HelloApplication.scena.setRoot(popupEkran);
	}
}
	