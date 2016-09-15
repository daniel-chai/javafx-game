import javafx.scene.Scene;

/**
 * This is the Interface implemented by every class that generates a Scene. The purpose
 * of this Interface is to standardize the way Scenes are designed across the game. The
 * consistency that this Interface provides is an additional design feature.
 * 
 * @author Daniel Chai (dhc10)
 * @version 1.0
 */
interface SceneInterface {
	/**
	 * @param width the width of the Scene
	 * @param height the height of the Scene
	 * @return Scene with the specified attributes
	 */
	Scene init(int width, int height);
}