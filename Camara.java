
public class Camara {
	Vector direction;
	Vector up;
	
	public Camara(double positionX, double positionY, double positionZ, double lookAtX, double lookAtY, double lookAtZ, double upX, double upY, double upZ)
	{
		this.direction = Vector(new Point(positionX, positionY, positionZ), new Point(lookAtX, lookAtY, lookAtZ));
		Vector semiUp = Vector(new Point(positionX, positionY, positionZ), new Point(upX, upY, upZ));
		this.up = getProjection(semiUp, getPerpendicularPlane());
	}
	
	public ArrayList<Vector> getScreenVectors(double screenDistance, double screenWidth, int SuperSamplingLevel, int imageWidth, int imageHeight)
	{
		
		for (int i = 0; i < imageWidth; i++)
		{
			for (int j =0; j < imageHeight; j++)
			{
				
			}
		}
	}

}
