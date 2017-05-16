import java.util.ArrayList;

public class Camara {
	Vector direction;
	Vector up;
	
	public Camara(float positionX, float positionY, float positionZ, float lookAtX, float lookAtY, float lookAtZ, float upX, float upY, float upZ)
	{
		this.direction = new Vector(new Point(positionX, positionY, positionZ), new Point(lookAtX, lookAtY, lookAtZ));
		Vector semiUp = new Vector(new Point(positionX, positionY, positionZ), new Point(upX, upY, upZ));
		this.up = semiUp.getProjection(this.direction);
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
