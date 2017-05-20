
public class Triangle extends AbstractSurface{
	private Point first,second,third;
	private Vector normal, firstNormal, secondNormal, thirdNormal;
	private double offset;
	public Triangle(Material material, Point first, Point second, Point third) 
	{
		super(material);
		this.first = first;
		this.second = second;
		this.third = third;
			
		this.normal = Vector.crossProduct(new Vector(this.second, this.first), new Vector(this.third,this.second));
		this.offset = Vector.dotProduct(new Vector(this.third), this.normal);
		
		this.firstNormal = Vector.crossProduct(new Vector(this.second, this.first), this.normal);
		this.secondNormal = Vector.crossProduct(new Vector(this.third, this.second), this.normal);
		this.thirdNormal = Vector.crossProduct(new Vector(this.first, this.third), this.normal);
		
	}
	@Override
	public void intersectes(Ray ray) 
	{
		double temp = Vector.dotProduct(this.normal, ray.direction);
		if(temp == 0)
			return;
		double d = -(Vector.dotProduct(this.normal, new Vector(ray.origin)) - this.offset) / temp; 
		if(d<0)
		{
			System.out.println("negative d triangle - " + d);
			return;
		}
		Point intersection = ray.origin.add(ray.direction.scalarProduct(d));
		
		Vector C0 = new Vector(this.first, intersection); 
		if (Vector.dotProduct(C0,this.firstNormal) < 0)
			return;
					
		Vector C1 = new Vector(this.second, intersection); 
		if (Vector.dotProduct(C1,this.secondNormal) < 0)
			return;
		
		Vector C2 = new Vector(this.third, intersection); 
		if (Vector.dotProduct(C2,this.thirdNormal) < 0)
			return;
		
		
		ray.collisions.add(new Collision(this, intersection, d));
		
	}
}
