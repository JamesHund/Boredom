package renderEngine;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class Renderer {
	
	public void prepare() {
		//clears previous colors on screen
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		GL11.glClearColor(1, 0, 0, 1); 
	}
	
	public void render(RawModel model) {
		GL30.glBindVertexArray(model.getVaoID()); //binds vao in order to manipulate
		GL20.glEnableVertexAttribArray(0); //attribute array currently being modified (RawModel stored in 0)
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, model.getVertexCount()); //render!!!(rendermode, 0, and vertexcount of model)
		GL20.glDisableVertexAttribArray(0); //disables the attribute array after modification
		GL30.glBindVertexArray(0); //unbinds vao
	}
}
