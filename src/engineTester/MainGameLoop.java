package engineTester;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Light;
import entities.Player;
import models.RawModel;
import models.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import renderEngine.EntityRenderer;
import shaders.StaticShader;
import terrains.Terrain;
import textures.ModelTexture;
import textures.TerrainTexture;
import textures.TerrainTexturePack;

public class MainGameLoop {
	
	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		
		TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("grass_top"));
		TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("sand"));
		TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("grass"));
		TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("cobblestone"));
		
		TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture, rTexture, gTexture, bTexture);
		TerrainTexture blendMap =new TerrainTexture(loader.loadTexture("blendMap"));
		
		Terrain terrain = new Terrain(0,-1,loader,texturePack,blendMap,"heightMap");
		
		
		RawModel playerRawModel = OBJLoader.loadObjModel("person", loader);
		TexturedModel playerTexturedModel = new TexturedModel(playerRawModel,new ModelTexture(loader.loadTexture("playerTexture")));
		
		RawModel treeRawModel = OBJLoader.loadObjModel("lowPolyTree", loader);
		TexturedModel treeTexturedModel = new TexturedModel(treeRawModel,new ModelTexture(loader.loadTexture("lowPolyTree")));
//		Entity treeEntity = new Entity(treeTexturedModel, new Vector3f(50,terrain.getHeightOfTerrain(50, -50),-50), 0, 0, 0, 1);
		
		List<Entity> entities = new ArrayList<Entity>();
//		entities.add(treeEntity);
		Random random = new Random();
//		entities.add(new Entity(staticModel, new Vector3f(random.nextFloat()*800 - 400,0,random.nextFloat() * -600),0,0,0,3));
		for(int i=0;i<100;i++){
			float xFromRandom=random.nextFloat()*400;
			float zFromRandom=random.nextFloat() * -600;
			entities.add(new Entity(treeTexturedModel, new Vector3f(xFromRandom,terrain.getHeightOfTerrain(xFromRandom,zFromRandom),zFromRandom),0,0,0,1));
		}
		
		Light light = new Light(new Vector3f(20000,20000,2000),new Vector3f(1,1,1));
		
		Terrain terrain2 = new Terrain(-1,-1,loader,texturePack,blendMap,"heightMap");
		
		Player player = new Player(playerTexturedModel, new Vector3f(0, 0, 0),0,120,0, 1);
		
		
		MasterRenderer renderer = new MasterRenderer();
		Camera camera = new Camera(player);	
		while(!Display.isCloseRequested()){
			player.move(terrain);
			camera.move();
			renderer.processEntity(player);
			
			renderer.processTerrain(terrain);
//			renderer.processTerrain(terrain2);
			for(Entity entity:entities){
				renderer.processEntity(entity);
			}
			renderer.render(light, camera);
			DisplayManager.updateDisplay();
		}

		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}

}
