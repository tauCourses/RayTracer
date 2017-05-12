
public class Sphere extends AbstractSurface{
	Point center;
	float radius;
	public Sphere(Material material, Point center, float radius) {
		super(material);
		this.center = center;
		this.radius = radius;
	}

	@Override
	public Point intersectes(Vector v) {
		float a = v.direction.x*v.direction.x + v.direction.y*v.direction.y + v.direction.z*v.direction.z;
		float b = 2*(	v.direction.x*(v.origin.x-this.center.x) + 
						v.direction.y*(v.origin.y-this.center.y) + 
						v.direction.z*(v.origin.z-this.center.z) );
		float c = 	(v.origin.x-this.center.x)*(v.origin.x-this.center.x) +
					(v.origin.y-this.center.y)*(v.origin.y-this.center.y) +
					(v.origin.y-this.center.y)*(v.origin.y-this.center.y);
		float determinante = b*b-4*a*c;
		if(determinante<0)
			return null;
		
		float d = (float) ((-b - Math.sqrt(determinante))/2);
		return v.origin.add(v.direction.scalarMultiplication(d));
	}

}
