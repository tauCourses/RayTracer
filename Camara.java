import java.util.ArrayList;

public class Camara {
	Vector lookAt;
	Vector up;
	float screenDistance, screenWidth;
	
	public Camara(Point location, Point lookAt, Point up, float screenDistance, float screenWidth)
	{
		this.screenDistance = screenDistance;
		this.screenWidth = screenWidth;
		this.direction = new Vector(location, lookAt);
		Vector semiUp = new Vector(location, up);
		//this.up = (new Plane(direction,0)).getProjection(semiUp);
		this.up = semiUp.getProjection(this.direction);

	}
	
	public ArrayList<Vector> getScreenVectors(int SuperSamplingLevel, int i, int j)
	{
		return null;
	}

}
