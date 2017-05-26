
public class Light {
	Vector position;
	Vector lightDirection;
	Color color;
	
	float specularIntensity;
	float shadowIntensity;
	float radius;
	int numberOfShadowRays;
	
	public Light(Vector position, Color color,float specularIntensity, float shadowIntensity, float radius)
	{
		this.position = position;
		this.color = color;
		this.specularIntensity = specularIntensity;
		this.shadowIntensity = shadowIntensity;
		this.radius = radius;
		this.numberOfShadowRays = numberOfShadowRays;
	}
	

	public int isHitByLight(Vector in, Vector ci, iSurface[] surfaces)
	{
		
		return 0;
	}
	
	public int numOfLightRaysHits(Ray ray, iSurface[] surfaces, int rootNumberOfShadowRays )
	{
		lightDirection =  ray.intersection.subtruct(this.position);
		if(rootNumberOfShadowRays == 1)
			return this.isHitByLight(ray.intersection, this.position, surfaces);
		
		Vector temp;
		//if(this.lightDirection.x )
		
		
		
		return 0;
	}
	
	

}
