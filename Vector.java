
public class Vector {
	
	public final float x,y,z;
		
	public Vector(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector getPerpendicularVector()
	{
		return new Vector(1,1,(this.x+this.y/this.z)); //xa+yb+zc=0, a=1 b=1 c=(x+y)/z
	}
	
	public static float vectorDotProduct(Vector first, Vector second)
	{
		return (first.x*second.x + first.y*second.y + first.z*second.z);
	}
	
	public Vector vectorSubtruct(Vector second)
	{
		return new Vector(this.x - second.x, this.y - second.y, this.z - second.z);
	}
	
	public Vector vectorAdd(Vector second)
	{
		return new Vector(this.x + second.x, this.y + second.y, this.z + second.z);
	}
	
	public Vector vectorScalarProduct(float scalar)
	{
		return new Vector(scalar*this.x, scalar*this.y, scalar*this.z);
	}
	
	
	public Vector getProjection(Vector vector)
	{
		float dotproduct = Vector.vectorDotProduct(this,vector);
		dotproduct /= vector.vectorLengthSquare();
		Vector normalized = vector.vectorScalarProduct(dotproduct);
		return normalized;
	}
	
	public float vectorLengthSquare()
	{
		return (this.x*this.x + this.y*this.y + this.z*this.z);
	}
	
	public double vectorLength()
	{
		
		return Math.sqrt(this.vectorLengthSquare());
	}
	
	public String toString()
	{
		return ("("+this.x+", "+this.y+", "+this.z+")");
	}
	
	
	
}
