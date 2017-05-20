public class Collision implements Comparable<Collision>{
	
	public iSurface surface;
	public Point position;
	public double distance;
	public Vector normal;
	
	public Collision(iSurface surface, Point position, double distance, Vector normal)
	{
		this.surface = surface;
		this.position = position;
		this.distance = distance;
		this.normal = normal;
	}

	
	public int compareTo(Collision c) {
		if(this.distance > c.distance)
			return 1;
		else if(this.distance == c.distance)
			return 0;
		
		return -1;
		
	}

}