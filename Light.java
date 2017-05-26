
public class Light {
	Vector position;
	Vector lightDirection;
	Color color;
	
	float specularIntensity;
	float shadowIntensity;
	float radius;
	int numberOfShadowRays;
	Ray ray;
	Vector v1,v2;
	
	public Light(Vector position, Color color,float specularIntensity, float shadowIntensity, float radius)
	{
		this.position = position;
		this.color = color;
		this.specularIntensity = specularIntensity;
		this.shadowIntensity = shadowIntensity;
		this.radius = radius;
		this.numberOfShadowRays = numberOfShadowRays;
		this.ray = new Ray();
		v1 = new Vector(1,1,1);
		v2 = new Vector(1,0,0);
	}
	
	public int isHitByLight(Vector intersection, Vector currentLightVector, iSurface[] surfaces, iSurface toIgnure)
	{
		this.ray.setNewRay(currentLightVector, intersection.subtruct(currentLightVector).toUnitVector());
		
		float distanceToIntersection = currentLightVector.subtruct(intersection).getLength();
		this.ray.d = distanceToIntersection;
		
		for (iSurface surface : surfaces)
		{
			if(surface == toIgnure)
				continue;
			surface.intersectes(this.ray);
			if (this.ray.d < distanceToIntersection)
				return 0;
		}
		return 1;
		
	}
	
	public int numOfLightRaysHits(Ray ray, iSurface[] surfaces, int rootNumberOfShadowRays )
	{
		lightDirection =  ray.intersection.subtruct(this.position);
		if(rootNumberOfShadowRays == 1)
			return this.isHitByLight(ray.intersection, this.position, surfaces,ray.surface);
		
		Vector tempX = Vector.crossProduct(lightDirection, this.v1);
		if(tempX.x == 0 && tempX.y == 0 && tempX.z == 0)
			tempX = Vector.crossProduct(lightDirection, this.v2);
		
		Vector tempY = Vector.crossProduct(lightDirection, tempX);
		tempX = tempX.toUnitVector();
		tempY = tempY.toUnitVector();
		Vector mostLeftUp = this.position.add(tempX.scalarProduct(-this.radius/2)).add(tempY.scalarProduct(-this.radius/2));
		tempX = tempX.scalarProduct(this.radius/rootNumberOfShadowRays);
		tempY = tempY.scalarProduct(this.radius/rootNumberOfShadowRays);
		
		int numOfHits = 0;
		for(int i=0;i<rootNumberOfShadowRays;i++)
			for(int j=0;j<rootNumberOfShadowRays;j++)
				numOfHits += this.isHitByLight(ray.intersection, 
						mostLeftUp.add(tempX.scalarProduct(i).add(tempY).scalarProduct(j)), surfaces,ray.surface);
			
		
		
		return numOfHits;
	}
	
	

}
