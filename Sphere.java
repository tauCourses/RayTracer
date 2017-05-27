
public class Sphere extends AbstractSurface{
	public Vector center;
	float radius;
	float squaredRadius;
	public Sphere(Material material, Vector center, float radius) {
		super(material);
		this.center = center;
		this.radius = radius;
		this.squaredRadius = this.radius*this.radius;
	}

	@Override
	public void intersectes(Ray ray) {
		float a = ray.direction.getLengthSquare();
	//	System.out.println(ray.origin);
		float b = 2*(Vector.dotProduct(ray.direction, ray.origin.subtruct(this.center)));
		float c = 	new Vector(ray.origin, this.center).getLengthSquare() - this.squaredRadius;
		float determinante = b*b-4*a*c;
		if(determinante>=0)
		{		
			float d = (float)((-b - Math.sqrt(determinante))/2);
			if(d>this.epsilon && d + this.epsilon < ray.d)
			{
				ray.d = d;
				ray.surface = this;
			}
		}
	}

}
