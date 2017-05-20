import java.util.ArrayList;
import java.util.Random;

public class Camara {
	private Point location;
	private Vector lookAt, up;
	
	private double screenDistance, screenWidth, screenHeight;
	private Point mostLeftUp;
	private Vector pixelWidthDirection, pixelHeightDirection;
	
	Random generatorRandomDoubles;
	
	public Camara(Point location, Point lookAt, Vector up, double screenDistance, double screenWidth)
	{
		this.location = location;
		this.screenDistance = screenDistance;
		this.screenWidth = screenWidth;
		this.lookAt = new Vector(location, lookAt).toUnitVector();
		this.up = up.subtruct(up.getProjection(this.lookAt)).toUnitVector();
		
		generatorRandomDoubles = new Random();
		/*System.out.println("\ncamera :");
		System.out.println("location - " + this.location);
		System.out.println("look at point - " + lookAt);
		System.out.println("look at vector " + this.lookAt);
		System.out.println("up point - " + up);
		System.out.println("up before - " + up);
		System.out.println("new up - " + this.up + "\n");*/
			
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
	
	public ArrayList<Ray> getScreenVectors(int superSamplingLevel, int i, int j)
	{
		ArrayList<Ray> raysArray = new ArrayList<>();
		Point pixelPoint = this.mostLeftUp.add(this.pixelWidthDirection.scalarProduct((double)(j)).add(this.pixelHeightDirection.scalarProduct((double)(i))));
		Vector inPixelWidthDirection = this.pixelWidthDirection.scalarProduct(1/superSamplingLevel);
		Vector inPixelHeightDirection = this.pixelHeightDirection.scalarProduct(1/superSamplingLevel); 
		for(int k=0;k<superSamplingLevel;k++)
		{
			for(int p=0;p<superSamplingLevel;p++)
			{
				Point inPixelPoint = pixelPoint.add(inPixelWidthDirection.scalarProduct((double)(p) + generatorRandomDoubles.nextDouble()).add(inPixelHeightDirection.scalarProduct((double)(k)+ generatorRandomDoubles.nextDouble())));
				raysArray.add(new Ray(inPixelPoint, new Vector(this.location, inPixelPoint).toUnitVector()));
			}
		}
		return raysArray;
	}

}
