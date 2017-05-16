
public class Sphere extends AbstractSurface{
	Point center;
	float radius;
	public Sphere(Material material, Point center, float radius) {
		super(material);
		this.center = center;
		this.radius = radius;
	}

	@Override
	public Point intersectes(Ray ray) {
		float a = ray.direction.x*ray.direction.x + ray.direction.y*ray.direction.y + ray.direction.z*ray.direction.z;
		float b = 2*(	ray.direction.x*(ray.origin.x-this.center.x) + 
						ray.direction.y*(ray.origin.y-this.center.y) + 
						ray.direction.z*(ray.origin.z-this.center.z) );
		float c = 	(ray.origin.x-this.center.x)*(ray.origin.x-this.center.x) +
					(ray.origin.y-this.center.y)*(ray.origin.y-this.center.y) +
					(ray.origin.y-this.center.y)*(ray.origin.y-this.center.y);
		float determinante = b*b-4*a*c;
		if(determinante<0)
			return null;
		
		float d = (float) ((-b - Math.sqrt(determinante))/2);
		return ray.origin.add(ray.direction.vectorScalarProduct(d));
		
	}

}
