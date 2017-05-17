
public class infinityPlane extends AbstractSurface {
	Vector normal;
	float offset;
	Point p;
	
	public infinityPlane(Material material, Vector normal, float offset)
	{
		super(material);
		this.normal = normal.toUnitVector();
		this.offset = offset;
		p = new Point(0,0,0).add(this.normal.scalarProduct(this.offset));
	}
	

	@Override
	public void intersectes(Ray ray) {
		Vector temp = new Vector(p, ray.origin);
		float sigma = Vector.dotProduct(temp, normal)/ Vector.dotProduct(ray.direction, this.normal);
		if(sigma>=0)	
			ray.collisions.add(new Collision(this, ray.origin.add(ray.direction.scalarProduct(sigma)), sigma));
		
	}

}
