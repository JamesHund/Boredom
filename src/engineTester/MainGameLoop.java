package engineTester;

import org.lwjgl.opengl.Display;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.RawModel;
import renderEngine.Renderer;

public class MainGameLoop {

	public static void main(String[] args) {
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		
		float[] vertices = {
			    -0.5f, 0.5f, 0f, //vertices that describe two triangles to create a quad
			    -0.5f, -0.5f, 0f,
			    0.5f, -0.5f, 0f,
			    0.5f, -0.5f, 0f,
			    0.5f, 0.5f, 0f,
			    -0.5f, 0.5f, 0f
			  };
		
		RawModel quad = loader.loadToVAO(vertices);
		
		while(!Display.isCloseRequested()) {
			DisplayManager.updateDisplay();
			renderer.prepare();
			renderer.render(quad);
			
		}
		
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}

}
