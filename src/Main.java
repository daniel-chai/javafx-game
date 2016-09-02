import javafx.application.Application;
import javafx.stage.Stage;
 
public class Main extends Application {
	public static final String TITLE = "My Game";
	public static final int SIZE = 500;
	
    @Override
    public void start(Stage primaryStage) {
    	primaryStage.setTitle(TITLE);
    	
    	SceneManager.init(primaryStage);
    	SceneManager.goToMenuScene();
    }
    
    public static void main(String[] args) {
		launch(args);
	}
}