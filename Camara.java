import java.util.ArrayList;

public class Camara {
	private Point location;
	private Vector lookAt, up;
	
	private float screenDistance, screenWidth, screenHeight;
	private Point mostLeftUp;
	private Vector pixelWidthDirection, pixelHeightDirection;
	
	public Camara(Point location, Point lookAt, Point up, float screenDistance, float screenWidth)
	{
		this.location = location;
		this.screenDistance = screenDistance;
		this.screenWidth = screenWidth;
		System.out.println("location - " + this.location);
		this.lookAt = new Vector(location, lookAt).toUnitVector();
		System.out.println("look at vector " + this.lookAt);
		Vector upDirection = new Vector(location, up); //not orthogonal with lookAt
		System.out.println("up before - " + upDirection);
		this.up = upDirection.subtruct(upDirection.getProjection(this.lookAt)).toUnitVector();
		System.out.println("new up - " + this.up);
	}
	
	public void createScreen(int x, int y)
	{
		System.out.println("x y - " + x + " " + y);
		this.screenHeight = this.screenWidth*y/x;
		System.out.println("scren distance = " + this.screenDistance);
		System.out.println("screen - " + this.screenWidth + " " + this.screenHeight);
		Point screenCenter = this.location.add(this.lookAt.scalarProduct(this.screenDistance));
		System.out.println("location - " + this.location);
		System.out.println("look at " + this.lookAt);
		this.mostLeftUp = screenCenter.add(this.up.scalarProduct(this.screenHeight/2).add(Vector.crossProduct(this.up, this.lookAt).toUnitVector().scalarProduct(this.screenWidth/2)));
		this.pixelHeightDirection = this.up.scalarProduct((-1)*this.screenHeight/y);
		this.pixelWidthDirection = Vector.crossProduct(this.up, this.lookAt).toUnitVector().scalarProduct((-1)*this.screenWidth/x);
		System.out.println("screen center- " + screenCenter);
		System.out.println("screen left up- " + mostLeftUp);
		System.out.println("screen dir height- " + pixelHeightDirection);
		System.out.println("screen dir width- " + pixelWidthDirection);
	}
	public ArrayList<Ray> getScreenVectors(int SuperSamplingLevel, int i, int j)
	{
		ArrayList<Ray> temp = new ArrayList<>();
		Point pixelPoint = this.mostLeftUp.add(this.pixelWidthDirection.scalarProduct((float)(j+0.5)).add(this.pixelHeightDirection.scalarProduct((float)(i+0.5))));
		temp.add(new Ray(pixelPoint, new Vector(this.location,pixelPoint).toUnitVector()));
		return temp;
	}

}
