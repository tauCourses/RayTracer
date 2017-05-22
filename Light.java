
public class Light {
	Point position;
	Color color;
	double specularIntensity;
	double shadowIntensity;
	double radius;
	int numberOfShadowRays;
	
	public Light(Point position, Color color,Double specularIntensity, Double shadowIntensity, Double radius, int numberOfShadowRays)
	{
		this.position = position;
		this.color = color;
		this.specularIntensity = specularIntensity;
		this.shadowIntensity = shadowIntensity;
		this.radius = radius;
		this.numberOfShadowRays = numberOfShadowRays;
	}
	
	public boolean isHitByLightRay(Collision collisionOnSurface, Ray lightRay)
	{
		double distanceLightTocollision = (new Vector(lightRay.origin, collisionOnSurface.position)).getLength();
		collisionOnSurface.surface.intersectes(lightRay);
		double closestDistance = lightRay.collisions.get(0).distance;
		for (Collision currentCollision : lightRay.collisions)
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
	
	public int numOfLightRaysHits(Collision collisionOnSurface)
	{
		//Point screenCenter = this.location.add(this.lookAt.scalarProduct(this.screenDistance));
		Ray centerLightRay = new Ray(this.position, new Vector(collisionOnSurface.position));
		Vector upLight = centerLightRay.direction.getPerpendicular();
		Vector right = Vector.crossProduct(upLight, centerLightRay.direction).toUnitVector();
		
		Vector mostLeftUp = new Vector(this.position).add(upLight.scalarProduct(this.radius/2));
		mostLeftUp	= mostLeftUp.add(right.scalarProduct((-1)*this.radius/2));
		
		Vector pixelHeightDirection = upLight.scalarProduct((-1)*this.radius/this.numberOfShadowRays);
		Vector pixelWidthDirection = right.scalarProduct(this.radius/this.numberOfShadowRays);
		
		//this.inPixelWidthDirection = this.pixelWidthDirection.scalarProduct(1/superSamplingLevel);
		//this.inPixelHeightDirection = this.pixelHeightDirection.scalarProduct(1/superSamplingLevel); 
		
		
		
		
		return 0;
	}
	
	

}
