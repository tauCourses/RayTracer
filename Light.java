
public class Light {
	Vector position;
	Color color;
	
	float specularIntensity;
	float shadowIntensity;
	float radius;
	int numberOfShadowRays;
	Ray lightRay;
	
	public Light(Vector position, Color color,float specularIntensity, float shadowIntensity, float radius)
	{
		this.position = position;
		this.color = color;
		this.specularIntensity = specularIntensity;
		this.shadowIntensity = shadowIntensity;
		this.radius = radius;
		this.numberOfShadowRays = numberOfShadowRays;
	}
	
	public int isHitByLight(Vector intersection, Vector currentLightVector, iSurface[] surfaces)
	{
		this.lightRay.setNewRay(currentLightVector, intersection);
		float distanceToIntersection = currentLightVector.subtruct(intersection).getLength();
		for (iSurface currentSurface : surfaces)
		{
			currentSurface.intersectes(this.lightRay);
			if (this.lightRay.d < distanceToIntersection)
				return 0;
		}
		return 1;
		
		/*Ray LightRay = new Ray(this.position, new Vector(collisionOnSurface.position));
		float distanceLightTocollision = (new Vector(this.position, collisionOnSurface.position)).getLength();
		collisionOnSurface.surface.intersectes(LightRay);
		float closestDistance = LightRay.collisions.get(0).distance;
		for (Collision currentCollision : LightRay.collisions)
=======
	public boolean isHitByLightRay(Collision collisionOnSurface, Ray lightRay)
	{
		double distanceLightTocollision = (new Vector(lightRay.origin, collisionOnSurface.position)).getLength();
		collisionOnSurface.surface.intersectes(lightRay);
		double closestDistance = lightRay.collisions.get(0).distance;
		for (Collision currentCollision : lightRay.collisions)
>>>>>>> 3c42ebc1b68050de161ee5310774032664d29882
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
	
	/*public int numOfLightRaysHits(Collision collisionOnSurface)
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
	}*/
	
	

}
