import java.util.Random;

public class Camara {
	private Vector location;
	private Vector lookAt, up;
	
	private float screenDistance, screenWidth, screenHeight;
	private Vector mostLeftUp;
	private Vector pixelWidthDirection, pixelHeightDirection	, backToStart;
	private Vector inPixelWidthDirection, inPixelHeightDirection;
	private int superSamplingLevel;
	//private int currentPosition = 0;
	//private int width;
	Random generatorRandoms;
	
	public Camara(Vector location, Vector lookAt, Vector up, float screenDistance, float screenWidth)
	{
		this.location = location;
		this.screenDistance = screenDistance;
		this.screenWidth = screenWidth;
		this.lookAt = new Vector(location, lookAt).toUnitVector();
		this.up = up.subtruct(up.getProjection(this.lookAt)).toUnitVector();
		
//		generatorRandomDoubles = new Random();
		/*System.out.println("\ncamera :");
		System.out.println("location - " + this.location);
		System.out.println("look at point - " + lookAt);
		System.out.println("look at vector " + this.lookAt);
		System.out.println("up point - " + up);
		System.out.println("up before - " + up);
		System.out.println("new up - " + this.up + "\n");*/
			
	}
	
	public void createScreen(int x, int y, int superSamplingLevel)
	{
		//TODO - I THINK WE USE X AND Y NOT GOOD HERE!!!!
		//this.width = y;
		if (y < x) //TO CHECK!!!!
			this.screenHeight = this.screenWidth*x/y; 
		else
			this.screenHeight = this.screenWidth*y/x; 
		
		Vector screenCenter = this.location.add(this.lookAt.scalarProduct(this.screenDistance));
		
		Vector right = Vector.crossProduct(this.up, this.lookAt).toUnitVector();
		
		this.mostLeftUp = screenCenter.add(this.up.scalarProduct(this.screenHeight/2));
		this.mostLeftUp	= this.mostLeftUp.add(right.scalarProduct((-1)*this.screenWidth/2));
		
		
		this.pixelHeightDirection = this.up.scalarProduct((-1)*this.screenHeight/y);
		this.pixelWidthDirection = right.scalarProduct(this.screenWidth/x);
		
		this.backToStart = this.pixelWidthDirection.scalarProduct(-x);
		
		this.inPixelWidthDirection = this.pixelWidthDirection.scalarProduct(1/superSamplingLevel);
		this.inPixelHeightDirection = this.pixelHeightDirection.scalarProduct(1/superSamplingLevel); 
		
		this.mostLeftUp = this.mostLeftUp.add(this.inPixelWidthDirection.scalarProduct(0.5f));				
		this.mostLeftUp = this.mostLeftUp.add(this.inPixelHeightDirection.scalarProduct(0.5f));

		this.superSamplingLevel = superSamplingLevel;
		this.generatorRandoms = new Random();
	}
		
		
	public void getScreenVectors(Ray rays[], int i, int j)
	{
		int t=0;
		Vector pixelPoint = this.mostLeftUp.add(this.pixelWidthDirection.scalarProduct((float)(j)).add(this.pixelHeightDirection.scalarProduct((float)(i))));
		
		float random = generatorRandoms.nextFloat();
		for(int k=0;k<this.superSamplingLevel;k++)
		{
			for(int p=0;p<this.superSamplingLevel;p++)
			{
				while (random < 0.6)
					random = generatorRandoms.nextFloat();
				Vector inPixelPoint = pixelPoint.add(this.inPixelWidthDirection.scalarProduct((float)(p)+random).add(this.inPixelHeightDirection.scalarProduct((float)(k)+random)));
				rays[t].setNewRay(inPixelPoint, new Vector(this.location, pixelPoint).toUnitVector());
				t++;
			}
		}
		
	}

}
