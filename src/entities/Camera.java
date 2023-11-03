package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	
	private float distanceFromPlayer=26;//initial distance
	private float angleAroundPlayer=0;
	
	private Vector3f position = new Vector3f(0,0,0);
	private float pitch = 15f;//high/low
	private float yaw;//left-right
	private float roll;	//tilted
	private static final float INCREMENT=0.02f*100;
	private static final int CAMERA_Y_OFFSET=10;
	
	private Player player;
	
	public Camera(Player player) {
		this.player=player;
	}
	
	public Camera() {
		
	}
	
	public void move() {
		calculateZoom();
		calculatePitch();
		calculateAngleAroundPlayer();
		float horizontalDistance = calculateHorizontalDistance();
		float verticalDistance=calculateVerticalDistance();
		calculateCameraPosition(horizontalDistance,verticalDistance);
		this.yaw=180-(player.getRy()+angleAroundPlayer);
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
	
	private void calculateCameraPosition(float horizDistance,float verticDistance) {
		float theta=player.getRy()+angleAroundPlayer;
		float offsetX=(float) (horizDistance*Math.sin(Math.toRadians(theta)));
		float offsetZ=(float) (horizDistance*Math.cos(Math.toRadians(theta)));
		position.x = player.getPosition().x-offsetX;
		position.z = player.getPosition().z-offsetZ;
		position.y = player.getPosition().y + verticDistance + CAMERA_Y_OFFSET;
	}
	
	private float calculateHorizontalDistance() {
		return (float) (distanceFromPlayer*Math.cos(Math.toRadians(pitch)));
	}
	private float calculateVerticalDistance() {
		return (float) (distanceFromPlayer*Math.sin(Math.toRadians(pitch)));
	}
	
	private void calculateZoom() {
		float zoomLevel = Mouse.getDWheel() * 0.1f;
		distanceFromPlayer-=zoomLevel;
		
		// Ensure distance from player stays within the range [14, 150]
		// Upper limit will be reduced later, but kept as 150 for testing purposes
		if (distanceFromPlayer < 14) {
			distanceFromPlayer = 14;
	    } else if (distanceFromPlayer > 150) {
	    	distanceFromPlayer = 150;
	    }
		
	}
	
	private void calculatePitch() {
		if (Mouse.isButtonDown(1)) {
		    float pitchChange = Mouse.getDY() * 0.1f;
		    pitch -= pitchChange;
		    
		    // Ensure pitch stays within the range [0, 90]
		    if (pitch < 0) {
		        pitch = 0;
		    } else if (pitch > 90) {
		        pitch = 90;
		    }
		    
		}

	}
	
	private void calculateAngleAroundPlayer() {
		if(Mouse.isButtonDown(0)) {
			float angleChange=Mouse.getDX()*0.3f;
			angleAroundPlayer-=angleChange;
		}
	}

}
