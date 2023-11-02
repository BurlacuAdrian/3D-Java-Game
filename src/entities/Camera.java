package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	
	private Vector3f position = new Vector3f(0,0,0);
	private float pitch;//high/low
	private float yaw;//left-right
	private float roll;	//tilted
	private static final float INCREMENT=0.02f;
	
	public Camera() {
		
	}
	
	public void move() {
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			position.z-=INCREMENT*10;
			return;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			position.z+=INCREMENT*10;
			return;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			position.x+=INCREMENT;
			return;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			position.x-=INCREMENT;
			return;
		}
	}

	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}
	
	

}
