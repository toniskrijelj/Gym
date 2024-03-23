package teretana;

import java.util.function.UnaryOperator;

import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;

public class Utilities {

	public static TextFormatter<String> TextFormatter() {
		UnaryOperator<Change> typeFilter = change -> {
	        String input = change.getText();
	        if ((input.matches("[a-zA-Z*]")) || change.isDeleted()) {
	            return change;
	        }
	        return null;
	    };
	    return new TextFormatter<String>(typeFilter);
	}
}
