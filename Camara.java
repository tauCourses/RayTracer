import java.util.ArrayList;

public class Camara {
	Vector lookAt;
	Vector up;
	
	public Camara(float positionX, float positionY, float positionZ, float lookAtX, float lookAtY, float lookAtZ, float upX, float upY, float upZ)
	{
		this.lookAt = new Vector(lookAtX - positionX, lookAtY - positionY, lookAtZ - positionZ);
		Vector upDirection = new Vector(upX - positionX, upY - positionY, upZ - positionZ);
		this.up = upDirection.vectorSubtruct(upDirection.getProjection(this.lookAt));
	}
	
	public ArrayList<Vector> getScreenVectors(double screenDistance, double screenWidth, int SuperSamplingLevel, int imageWidth, int imageHeight)
	{
		
		for (int i = 0; i < imageWidth; i++)
		{
			for (int j =0; j < imageHeight; j++)
			{
				
			}
		}
		return null;
	}

}
