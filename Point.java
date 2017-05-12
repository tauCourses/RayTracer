
public class Point {
	public final float x,y,z;
	public Point(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public Point add(Point b)
	{
		return new Point(this.x+b.x,this.y+b.y,this.z+b.z);
	}
	public Point scalarMultiplication(float a)
	{
		return new Point(this.x*a,this.y*a,this.z*a);
	}
}
