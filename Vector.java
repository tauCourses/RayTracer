
public class Vector {
	
	public final float x,y,z;
		
	public Vector(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector(Point origin, Point destination)
	{
		this.x = destination.x - origin.x;
		this.y = destination.y - origin.y;
		this.z = destination.z - origin.z;
	}
	
	public Vector(String x, String y, String z)
	{
		this.x = Float.valueOf(x);
		this.y = Float.valueOf(y);
		this.z = Float.valueOf(z);
	}
	
	public static Vector crossProduct(Vector v1, Vector v2)
	{
		return new Vector(v1.y*v2.z - v1.z*v2.y, v1.z*v2.x-v1.x*v2.z,v1.x*v2.y-v1.y*v2.x);
	}
	
	public static float dotProduct(Vector first, Vector second)
	{
		return (first.x*second.x + first.y*second.y + first.z*second.z);
	}
	
	public Vector toUnitVector()
	{
		return this.scalarProduct(1/this.getLength());
	}
	public Vector subtruct(Vector second)
	{
		return new Vector(this.x - second.x, this.y - second.y, this.z - second.z);
	}
	
	public Vector add(Vector second)
	{
		return new Vector(this.x + second.x, this.y + second.y, this.z + second.z);
	}
	
	public Vector scalarProduct(float scalar)
	{
		return new Vector(scalar*this.x, scalar*this.y, scalar*this.z);
	}
	
	public Vector getProjection(Vector vector)
	{
		float dotproduct = Vector.dotProduct(this,vector);
		dotproduct /= vector.getLengthSquare();
		Vector normalized = vector.scalarProduct(dotproduct);
		return normalized;
	}
	
	public float getLengthSquare()
	{
		return (this.x*this.x + this.y*this.y + this.z*this.z);
	}
	
	public float getLength()
	{
		return (float)Math.sqrt(this.getLengthSquare());
	}
	
	public String toString()
	{
		return ("("+this.x+", "+this.y+", "+this.z+")");
	}
	
	
	
}
