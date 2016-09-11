import javafx.scene.Scene;

/**
 * This is the interface implemented by every class that generates a Scene.
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