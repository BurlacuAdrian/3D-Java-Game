package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	
	private Vector3f position = new Vector3f(0,10,0);
	private float pitch;//high/low
	private float yaw;//left-right
	private float roll;	//tilted
	private static final float INCREMENT=0.02f*100;
	
	public Camera() {
		this.roll=0;
		this.pitch=0;
		this.yaw=0;
	}
	
	public void move() {
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			position.z-=INCREMENT;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			position.z+=INCREMENT;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			position.x+=INCREMENT;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			position.x-=INCREMENT;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			position.y+=INCREMENT;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
			position.y-=INCREMENT;

		}
		if(Keyboard.isKeyDown(Keyboard.KEY_Q)) {
			yaw-=INCREMENT/4;

		}
		if(Keyboard.isKeyDown(Keyboard.KEY_E)) {
			yaw+=INCREMENT/4;

		}
		int dWheel=Mouse.getDWheel();
		if(dWheel>0)
			pitch+=INCREMENT*2;
		else if (dWheel < 0)
			pitch-=INCREMENT*2;
			
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
