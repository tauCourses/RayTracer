
public class Point {
	public final float x,y,z;
	public Point(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public Point(String x, String y, String z)
	{
		this.x = Float.valueOf(x);
		this.y = Float.valueOf(y);
		this.z = Float.valueOf(z);
	}
	public Point add(Vector v)
	{
		return new Point(this.x+v.x,this.y+v.y,this.z+v.z);
	}
	
	public String toString()
	{
		return ("("+this.x+", "+this.y+", "+this.z+")");
	}

}
