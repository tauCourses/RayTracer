
public class Triangle extends AbstractSurface{
	private Vector first,second,third;
	public Vector normal, firstNormal, secondNormal, thirdNormal;
	private float offset;
	public Triangle(Material material, Vector first, Vector second, Vector third) 
	{
		super(material);
		this.first = first;
		this.second = second;
		this.third = third;
		
		Vector frse = this.first.subtruct(this.second);
		Vector seth = this.second.subtruct(this.third);
		
		this.normal = Vector.crossProduct(frse, seth).toUnitVector();
		this.offset = Vector.dotProduct(this.third, this.normal);
		
		this.firstNormal = Vector.crossProduct(new Vector(this.second, this.first), this.normal);
		this.secondNormal = Vector.crossProduct(new Vector(this.third, this.second), this.normal);
		this.thirdNormal = Vector.crossProduct(new Vector(this.first, this.third), this.normal);
		
	}
	@Override
	public void intersectes(Ray ray) 
	{
		float temp = Vector.dotProduct(this.normal, ray.direction);
		if(temp == 0)
			return;
		float d = -(Vector.dotProduct(this.normal, ray.origin) - this.offset) / temp; 
		if(d<=0 || d > ray.d)
			return;
		
		Vector intersection = ray.origin.add(ray.direction.scalarProduct(d));
		
		Vector C0 = new Vector(this.first, intersection); 
		if (Vector.dotProduct(C0,this.firstNormal) < 0)
			return;
					
		Vector C1 = new Vector(this.second, intersection); 
		if (Vector.dotProduct(C1,this.secondNormal) < 0)
			return;
		
		Vector C2 = new Vector(this.third, intersection); 
		if (Vector.dotProduct(C2,this.thirdNormal) < 0)
			return;
		
		ray.d = d;
		ray.surface = this;
		ray.intersection = intersection;
		
	}
}
