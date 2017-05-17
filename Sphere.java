
public class Sphere extends AbstractSurface{
	Point center;
	double radius;
	public Sphere(Material material, Point center, double radius) {
		super(material);
		this.center = center;
		this.radius = radius;
	}

	@Override
	public void intersectes(Ray ray) {
		double a = ray.direction.x*ray.direction.x + ray.direction.y*ray.direction.y + ray.direction.z*ray.direction.z;
		double b = 2*(	ray.direction.x*(ray.origin.x-this.center.x) + 
						ray.direction.y*(ray.origin.y-this.center.y) + 
						ray.direction.z*(ray.origin.z-this.center.z) );
		double c = 	(ray.origin.x-this.center.x)*(ray.origin.x-this.center.x) +
					(ray.origin.y-this.center.y)*(ray.origin.y-this.center.y) +
					(ray.origin.z-this.center.z)*(ray.origin.z-this.center.z) - this.radius*this.radius;
		double determinante = b*b-4*a*c;
		if(determinante>=0)
		{		
			double d = ((-b - Math.sqrt(determinante))/2);
			System.out.println(d);
			if(d>=0)
				ray.collisions.add(new Collision(this, ray.origin.add(ray.direction.scalarProduct(d)), d));
		}
	}

}
