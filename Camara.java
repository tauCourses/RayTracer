import java.util.ArrayList;

public class Camara {
	Vector lookAt;
	Vector up;
	float screenDistance, screenWidth;
	Point mostLeftUp;
	Vector widthDirection, highetDirection;
	
	public Camara(Point location, Point lookAt, Point up, float screenDistance, float screenWidth)
	{
		this.screenDistance = screenDistance;
		this.screenWidth = screenWidth;
		this.lookAt = new Vector(location, lookAt);
		Vector semiUp = new Vector(location, up);
		//this.up = (new Plane(direction,0)).getProjection(semiUp);
		this.up = semiUp.getProjection(this.lookAt);

		
	}
	
	public ArrayList<Ray> getScreenVectors(int SuperSamplingLevel, int i, int j)
	{
		return null;
	}

}
