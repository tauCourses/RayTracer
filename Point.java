
public class Point {
	public final double x,y,z;
	public Point(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public Point(String x, String y, String z)
	{
		this.x = Double.valueOf(x);
		this.y = Double.valueOf(y);
		this.z = Double.valueOf(z);
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
