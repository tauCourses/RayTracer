
public class Light {
	Vector position;
	Color color;
	float specularIntensity;
	float shadowIntensity;
	float radius;
	
	public Light(Vector position, Color color,float specularIntensity, float shadowIntensity, float radius)
	{
		this.position = position;
		this.color = color;
		this.specularIntensity = specularIntensity;
		this.shadowIntensity = shadowIntensity;
		this.radius = radius;
	}
	
	public boolean isHitByLight()
	{
		/*Ray LightRay = new Ray(this.position, new Vector(collisionOnSurface.position));
		float distanceLightTocollision = (new Vector(this.position, collisionOnSurface.position)).getLength();
		collisionOnSurface.surface.intersectes(LightRay);
		float closestDistance = LightRay.collisions.get(0).distance;
		for (Collision currentCollision : LightRay.collisions)
		{
			if (closestDistance > currentCollision.distance)
				closestDistance = currentCollision.distance;
		}
		if ((distanceLightTocollision == closestDistance) & (distanceLightTocollision != 0))
		{
			return true;
		}*/
		return false;
	}
	
	

}
