
public class infinityPlane extends AbstractSurface {
	public Vector normal;
	float offset;
	Vector p;
	float b;
	public infinityPlane(Material material, Vector normal, float offset)
	{
		super(material);
		this.normal = normal.toUnitVector();
		this.offset = offset;
		p = this.normal.scalarProduct(this.offset);
		b = Vector.dotProduct(p, normal);
	}
	
	@Override
	public void intersectes(Ray ray) 
	{
		Vector temp = new Vector(ray.origin, p);
		float sigma = Vector.dotProduct(temp, normal)/ Vector.dotProduct(ray.direction, this.normal);
		if(sigma>0 && sigma<ray.d)
		{
			//System.out.println(sigma +" "+ ray.d);			
			ray.d = sigma;
			ray.surface = this;
		}
	}

}
