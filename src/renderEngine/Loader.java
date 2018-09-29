package renderEngine;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class Loader {

	public RawModel loadToVAO(float[] positions) {
		//loads RawModels into VAO for use in the program
		
		int vaoID = createVAO();
		storeDataInAttributeList(0,positions);
		unbindVAO();
		return new RawModel(vaoID,positions.length/3);
	}
	
	private int createVAO() {
		//creates a vao
		
		int vaoID = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vaoID);
		return vaoID;
	}
	
	private void storeDataInAttributeList(int attributeNumber, float[] data) {
		//method to bind vbos to an index of a vao
		
		int vboID = GL15.glGenBuffers(); //generates buffers
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID); 
		FloatBuffer buffer = storeDataInFloatBuffer(data); //stores data in a float buffer
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW); //buffer stored in opengl array buffer
		GL20.glVertexAttribPointer(attributeNumber,3,GL11.GL_FLOAT,false,0,0);  //points vao attribute to data in array buffer and specifies use
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0); //unbind current vbo
		
	}
	
	private void unbindVAO() {
		//unbinds VAO after use
		
		GL30.glBindVertexArray(0);
	}
	
	private FloatBuffer storeDataInFloatBuffer(float[] data) {
		//puts float data in a floatbuffer
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip(); //prepares buffer for reading
		return buffer;
	}
	
}
