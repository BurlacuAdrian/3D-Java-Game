package shaders;

import org.lwjgl.util.vector.Matrix4f;

import entities.Camera;
import entities.Light;
import toolbox.Maths;

public class TerrainShader extends ShaderProgram{
	
	private static final String VERTEX_FILE="src/shaders/terrainVertexShader.txt";
	private static final String FRAGMENT_FILE="src/shaders/terrainFragmentShader.txt";
	private int locationTransformationMatrix;
	private int locationProjectionMatrix;
	private int locationViewMatrix;
	private int locationLightPosition;
	private int locationLightColor;
	private int locationShineDamper;
	private int locationReflectivity;
//	private int locationSkyColor; for fog
	private int locationBackgroundTexture;
	private int locationRTexture;
	private int locationGTexture;
	private int locationBTexture;
	private int locationBlendMap;
	
	public TerrainShader() {
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
		//sky
		locationBackgroundTexture= super.getUniformLocation("backgroundTexture");
		locationRTexture= super.getUniformLocation("rTexture");
		locationGTexture= super.getUniformLocation("gTexture");
		locationBTexture= super.getUniformLocation("bTexture");
		locationBlendMap= super.getUniformLocation("blendMap");
	}
	
	public void connectTextureUnits() {
		super.loadInt(locationBackgroundTexture, 0);
		super.loadInt(locationRTexture, 1);
		super.loadInt(locationGTexture, 2);
		super.loadInt(locationBTexture, 3);
		super.loadInt(locationBlendMap, 4);
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
