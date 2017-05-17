
public class infinityPlane extends AbstractSurface {
	Vector normal;
	double offset;
	Point p;
	
	public infinityPlane(Material material, Vector normal, double offset)
	{
		super(material);
		this.normal = normal.toUnitVector();
		this.offset = offset;
		p = new Point(0,0,0).add(this.normal.scalarProduct(this.offset));
	}
	

	@Override
	public void intersectes(Ray ray) {
		Vector temp = new Vector(ray.origin, p);
		double sigma = Vector.dotProduct(temp, normal)/ Vector.dotProduct(ray.direction, this.normal);
		if(sigma>=0)	
			ray.collisions.add(new Collision(this, ray.origin.add(ray.direction.scalarProduct(sigma)), sigma));
		
	}

}
