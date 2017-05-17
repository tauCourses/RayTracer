import java.util.ArrayList;

public class Ray {
	
	Point origin;
	Vector direction;
	public ArrayList<Collision> collisions;
	public Ray(Point origin, Vector direction)
	{
		this.origin = origin;
		this.direction = direction;
		this.collisions = new ArrayList<>();
	}

}
