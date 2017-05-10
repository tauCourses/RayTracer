
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
		return null;
	}

}
