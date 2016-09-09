import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class UIGenerator {
	public static final String DEFAULT_FONT_FAMILY = "Verdana";
	public static final int DEFAULT_FONT_SIZE = 20;
	public static final double DEFAULT_BUTTON_WIDTH = 100;
	public static final double DEFAULT_BUTTON_HEIGHT = 25;
	
	public static Text createText(String text, double x, double y) {
		return createText(text, x, y, DEFAULT_FONT_SIZE);
	}
	
	public static Text createText(String text, double x, double y, int fontSize) {
		Text textUI = new Text();
		textUI.setText(text);
		textUI.setX(x);
		textUI.setY(y);
		textUI.setFont(Font.font(DEFAULT_FONT_FAMILY, fontSize));
		
		return textUI;
	}
	
	public static Button createButton(String text, double x, double y) {
		return createButton(text, x, y, DEFAULT_BUTTON_WIDTH, DEFAULT_BUTTON_HEIGHT, DEFAULT_FONT_SIZE);
	}
	
	public static Button createButton(String text, double x, double y, double width, double height, int fontSize) {
		Button buttonUI = new Button();
		buttonUI.setText(text);
		buttonUI.setLayoutX(x);
		buttonUI.setLayoutY(y);
		buttonUI.setMinWidth(width);
		buttonUI.setMinHeight(height);
		buttonUI.setFont(Font.font(DEFAULT_FONT_FAMILY, fontSize));
		
		return buttonUI;
	}

}