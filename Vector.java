
public class Vector {
	public final Point origin,direction;
	public Vector(Point origin, Point direction)
	{
		this.origin = origin;
		this.direction = direction;
	}
		
	public Vector getPerpendicularVector()
	{
		float x = (this.direction.x - this.origin.x);
		float y = (this.direction.y - this.origin.y);
		float z = (this.direction.z - this.origin.z);
		return new Vector(new Point(origin.x, origin.y, origin.z), new Point(1,1,x+y/z));//xa+yb+zc=0, a=1 b=1 c=(x+y)/z
	}
	
	public static float vectorDotProduct(Vector first, Vector second)
	{
		Vector vectorFirst = first.setToZero();
		Vector vectorSecond = first.setToZero();		
		return (vectorFirst.direction.x*vectorSecond.direction.x + vectorFirst.direction.y*vectorSecond.direction.y + vectorFirst.direction.z*vectorSecond.direction.z);
	}
	
	public static Vector vectorSubtruct(Vector first, Vector second)
	{
		Vector vectorFirst = first.setToZero();
		Vector vectorSecond = first.setToZero();
		Point newDirection = new Point(vectorFirst.direction.y - vectorSecond.direction.x, vectorFirst.direction.y - vectorSecond.direction.y, vectorFirst.direction.z - vectorSecond.direction.z);
		return new Vector(new Point(vectorFirst.origin.x,vectorFirst.origin.y,vectorFirst.origin.z), newDirection);
	}
	
	public Vector vectorScalarProduct(float scalar)
	{
		Vector vector = this.setToZero();
		return new Vector(this.origin, new Point(scalar*vector.direction.x+this.origin.x , scalar*vector.direction.y+this.origin.y, scalar*vector.direction.z+this.origin.z));
	}
	
	
	public Vector getProjection(Vector vector)
	{
		return Vector.vectorSubtruct(this,vector.vectorScalarProduct((Vector.vectorDotProduct(this,vector)/(vector.normalize()))));
	}
	
	public float normalize()
	{
		Vector vector = this.setToZero();
		return (vector.direction.x*vector.direction.x+vector.direction.y*vector.direction.y+vector.direction.z*vector.direction.z);
	}
	
	public Vector setToZero()
	{
		return new Vector(new Point(0,0,0),new Point(this.direction.x-this.origin.x,this.direction.y-this.origin.y,this.direction.z-this.origin.z));
	}
	
	
	
}
