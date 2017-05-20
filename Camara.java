import java.util.ArrayList;

public class Camara {
	private Point location;
	private Vector lookAt, up;
	
	private double screenDistance, screenWidth, screenHeight;
	private Point mostLeftUp;
	private Vector pixelWidthDirection, pixelHeightDirection;
	
	public Camara(Point location, Point lookAt, Point up, double screenDistance, double screenWidth)
	{
		this.location = location;
		this.screenDistance = screenDistance;
		this.screenWidth = screenWidth;
		this.lookAt = new Vector(location, lookAt).toUnitVector();
		Vector upDirection = new Vector(location, up); //not orthogonal with lookAt
		this.up = upDirection.subtruct(upDirection.getProjection(this.lookAt)).toUnitVector();
		
		System.out.println("\ncamera :");
		System.out.println("location - " + this.location);
		System.out.println("look at vector " + this.lookAt);
		System.out.println("up before - " + upDirection);
		System.out.println("new up - " + this.up + "\n");
			
	}
	
	public void createScreen(int x, int y)
	{
		this.screenHeight = this.screenWidth*y/x;
		Point screenCenter = this.location.add(this.lookAt.scalarProduct(this.screenDistance));
		
		Vector right = Vector.crossProduct(this.up, this.lookAt).toUnitVector();
		
		this.mostLeftUp = screenCenter.add(this.up.scalarProduct(this.screenHeight/2));
		this.mostLeftUp	= this.mostLeftUp.add(right.scalarProduct((-1)*this.screenWidth/2));
		
		this.pixelHeightDirection = this.up.scalarProduct((-1)*this.screenHeight/y);
		this.pixelWidthDirection = right.scalarProduct(this.screenWidth/x);
	}
	
	public ArrayList<Ray> getScreenVectors(int SuperSamplingLevel, int i, int j)
	{
		ArrayList<Ray> raysArray = new ArrayList<>();
		Point pixelPoint = this.mostLeftUp.add(this.pixelWidthDirection.scalarProduct((float)(j+0.5)).add(this.pixelHeightDirection.scalarProduct((float)(i+0.5))));
		raysArray.add(new Ray(pixelPoint, new Vector(this.location, pixelPoint).toUnitVector()));
		return raysArray;
	}

}
