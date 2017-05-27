import java.util.ArrayList;

public class Ray {
	
	public Vector origin;
	public Vector direction;
	public Vector toCam;
	public float d = Float.MAX_VALUE;
	public iSurface surface;
	public Vector intersection;
	
	/*public Ray(Vector origin, Vector direction)
	{
		this.origin = origin;
		this.direction = direction;
	}*/
	public void getIntersection()
	{
		intersection = origin.add(direction.scalarProduct(d));
	}
	public Vector getNormal()
	{
		if(surface instanceof infinityPlane)
			return ((infinityPlane)(surface)).normal;
		else if(surface instanceof Triangle)
			return ((Triangle)(surface)).normal;
		return this.intersection.subtruct(((Sphere)(surface)).center).toUnitVector();
	}
	public void setNewRay(Vector origin, Vector direction)
	{
		this.origin = origin;
		this.direction = direction.toUnitVector();
		this.d = Float.MAX_VALUE;
		this.toCam = this.direction.scalarProduct(-1f);
	}

}
