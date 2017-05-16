
public class Vector {
	public final Point origin,direction;
	public Vector(Point origin, Point direction)
	{
		this.origin = origin;
		this.direction = direction;
	}
	
//	public Vector getProjectionOnPerpendicularPlane(Vector dircetion)
//	{
//		Vector perpendicularToDirection = dircetion.getPerpendicularVector();
//		
//		return null;
//	}
	
	public Vector getPerpendicularVector()
	{
		float x = (this.direction.x - this.origin.x);
		float y = (this.direction.y - this.origin.y);
		float z = (this.direction.z - this.origin.z);
		return new Vector(new Point(origin.x, origin.y, origin.z), new Point(1,1,x+y/z));//xa+yb+zc=0, a=1 b=1 c=(x+y)/z
	}
	
	public static float vectorDotProduct(Vector first, Vector second)
	{
		// TODO Auto-generated method stu
		return 0;
	}
	
	public static Vector vectorSubtruct(Vector first, Vector second)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	public Vector vectorScalarProduct(float scalar)
	{
		// TODO Auto-generated method stub
		return this;
	}
	

	public float vectorLength() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public Vector getProjection(Vector vector)
	{
		Vector.vectorSubtruct(this,vector.vectorScalarProduct((Vector.vectorDotProduct(this,vector)/(vector.vectorLength()))));
		return null;
	}
	
	
	
}
