
public class Light {
	Point position;
	Color color;
	double specularIntensity;
	double shadowIntensity;
	double radius;
	
	public Light(Point position, Color color,Double specularIntensity, Double shadowIntensity, Double radius)
	{
		this.position = position;
		this.color = color;
		this.specularIntensity = specularIntensity;
		this.shadowIntensity = shadowIntensity;
		this.radius = radius;
	}
	
	public boolean isHitByLight(Collision collisionOnSurface)
	{
		Ray LightRay = new Ray(this.position, new Vector(collisionOnSurface.position));
		double distanceLightTocollision = (new Vector(this.position, collisionOnSurface.position)).getLength();
		collisionOnSurface.surface.intersectes(LightRay);
		double closestDistance = LightRay.collisions.get(0).distance;
		for (Collision currentCollision : LightRay.collisions)
		{
			if (closestDistance > currentCollision.distance)
				closestDistance = currentCollision.distance;
		}
		if ((distanceLightTocollision == closestDistance) & (distanceLightTocollision != 0))
		{
			return true;
		}
		return false;
	}
	
	

}
