
public class Vector {
	public final Point origin,direction;
	public Vector(Point origin, Point direction)
	{
		this.origin = origin;
		this.direction = direction;
	}
	
	public Vector getProjectionOnPerpendicularPlane(Vector dircetion)
	{
		Vector perpendicularToDirection = dircetion.getPerpendicularVector();
		
		return null;
	}
	
	public Vector getPerpendicularVector()
	{
		float x = (this.direction.x - this.origin.x);
		float y = (this.direction.y - this.origin.y);
		float z = (this.direction.z - this.origin.z);
		return new Vector(new Point(origin.x, origin.y, origin.z), new Point(1,1,x+y/z));
	}
	
	
}
