package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import models.TexturedModel;
import renderEngine.DisplayManager;
import terrains.Terrain;

public class Player extends Entity{
	
	private static final float RUNNING_SPEED=20,//units
			TURNING_SPEED=160;//degrees
	private static final float GRAVITY = -50;
	private static final float JUMP_POWER=30;
	
	private static final float TERRAIN_HEIGHT=0,
			SHIFT_MULTIPLIER=2;
	
	private boolean isInAir=false;
	
	
	private float currentSpeed=0,
			currentTurningSpeed=0,
			upwardsSpeed=0;
	
	

	public Player(TexturedModel model, Vector3f position, float rx, float ry, float rz, float scale) {
		super(model, position, rx, ry, rz, scale);

	}
	
	private void checkInputs() {
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
				this.currentSpeed=RUNNING_SPEED*SHIFT_MULTIPLIER;
			else
				this.currentSpeed=RUNNING_SPEED;
		}else if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			this.currentSpeed=-RUNNING_SPEED;
		}else
			this.currentSpeed=0;
		
		if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			this.currentTurningSpeed=-TURNING_SPEED;
		}else if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			this.currentTurningSpeed=TURNING_SPEED;
		}else
			this.currentTurningSpeed=0;
		
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			jump();
			isInAir=true;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
		}
	}
	
	public void move(Terrain terrain) {
		checkInputs();
	    
	    // Update rotation
	    super.increaseRotation(0, currentTurningSpeed * DisplayManager.getFrameTimeSeconds(), 0);

	    // Calculate movement in world space
	    float distance = currentSpeed * DisplayManager.getFrameTimeSeconds();
	    float dx = (float) (distance * Math.sin(Math.toRadians(super.getRy())));
	    float dz = (float) (distance * Math.cos(Math.toRadians(super.getRy())));

	    // Update position
	    super.increasePosition(dx, 0, dz);

	    // Apply gravity and check for terrain collision
	    upwardsSpeed += GRAVITY * DisplayManager.getFrameTimeSeconds();
	    super.increasePosition(0, upwardsSpeed * DisplayManager.getFrameTimeSeconds(), 0);

	    
	    float terrainHeight = terrain.getHeightOfTerrain(super.getPosition().x, super.getPosition().z);
	    
	    if (super.getPosition().y < terrainHeight) {
	        upwardsSpeed = 0;
	        isInAir = false;
	        super.getPosition().y = terrainHeight;
	    }
	}


	
	private void jump() {
		if(!isInAir) {
			this.upwardsSpeed = JUMP_POWER;
			isInAir=true;
		}
	}
	
	

}
