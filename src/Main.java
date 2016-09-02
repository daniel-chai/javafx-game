import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
 
public class Main extends Application {
	public static final String TITLE = "My Game";
	public static final int SIZE = 500;
	
	private static Stage stage;
	
    @Override
    public void start(Stage primaryStage) {
    	stage = primaryStage;
    	
    	stage.setTitle(TITLE);
    	
    	Menu menu = new Menu();
    	Scene menuScene = menu.init(SIZE, SIZE);
    	stage.setScene(menuScene);
    	
    	stage.show();
    }
    
    public static void main(String[] args) {
		launch(args);
	}
    
    public static Stage getStage() {
    	return stage;
    }
}