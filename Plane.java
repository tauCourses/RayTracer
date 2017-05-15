
public class Plane {
	Vector normal;
	int offset;
	
	public Plane(Vector normal, int offset)
	{
		this.normal = normal;
		this.offset = offset;
	}
	
	public Vector getProjection(Vector vector)
	{
		Vector.vectorSubtruct(vector,this.normal.vectorScalarProduct((Vector.vectorDotProduct(vector,normal)/(normal.vectorLength()))));
		return null;
	}

}
