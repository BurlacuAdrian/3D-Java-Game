package shaders;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;

import entities.Camera;
import entities.Light;
import toolbox.Maths;

public class StaticShader extends ShaderProgram{
	
	private static final String VERTEX_FILE="src/shaders/vertexShader.txt";
	private static final String FRAGMENT_FILE="src/shaders/fragmentShader.txt";
	private int locationTransformationMatrix;
	private int locationProjectionMatrix;
	private int locationViewMatrix;
	private int locationLightPosition;
	private int locationLightColor;
	private int locationShineDamper;
	private int locationReflectivity;
	private int locationNumberOfRows;
	private int locationOffset;
	
	public StaticShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoords");
		super.bindAttribute(2, "normal");
	}

	@Override
	protected void getAllUniformLocations() {
		locationTransformationMatrix = super.getUniformLocation("transformationMatrix");
		locationProjectionMatrix = super.getUniformLocation("projectionMatrix");
		locationViewMatrix= super.getUniformLocation("viewMatrix");
		locationLightPosition= super.getUniformLocation("lightPosition");
		locationLightColor= super.getUniformLocation("lightColor");
		locationShineDamper= super.getUniformLocation("shineDamper");
		locationReflectivity= super.getUniformLocation("reflectivity");
		locationNumberOfRows= super.getUniformLocation("numberOfRows");
		locationOffset= super.getUniformLocation("offset");
	}
	
	public void loadNumberOfRows(int no) {
		super.loadFloat(locationNumberOfRows,no);
	}
	
	public void loadOffset(float x, float y) {
		super.load2DVector(locationOffset, new Vector2f(x,y));
	}
	
	public void loadShineVariables(float damper, float reflectivity) {
		super.loadFloat(locationShineDamper, damper);
		super.loadFloat(locationReflectivity, reflectivity);
	}
	
	public void loadTransformationMatrix(Matrix4f matrix) {
		super.loadMatrix(locationTransformationMatrix, matrix);
	}
	
	public void loadLight(Light light) {
		super.loadVector(locationLightPosition, light.getPosition());
		super.loadVector(locationLightColor, light.getColour());
	}
	
	public void loadProjectionMatrix(Matrix4f projection) {
		super.loadMatrix(locationProjectionMatrix, projection);
	}
	public void loadViewMatrix(Camera camera) {
		Matrix4f viewMatrix = Maths.createViewMatrix(camera);
		super.loadMatrix(locationViewMatrix, viewMatrix);
	}
	
	

}
