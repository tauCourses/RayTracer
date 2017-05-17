public class Collision implements Comparable<Collision>{
	
	public iSurface surface;
	public Point position;
	public float distance;
	
	public Collision(iSurface surface, Point position, float distance)
	{
		this.surface = surface;
		this.position = position;
		this.distance = distance;
	}


	public int compareTo(Collision c) {
		if(this.distance < c.distance)
			return 1;
		else if(this.distance == c.distance)
			return 0;
		
		return -1;
		
	}

}