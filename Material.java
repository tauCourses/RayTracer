
public class Material {
	private Color diffuse, spectular, reflected;
	private float phong, transparency;
	public Material(Color diffuse, Color spectular, Color reflected, float phong, float transparency)
	{
		this.diffuse = diffuse;
		this.spectular = spectular;
		this.reflected = reflected;
		this.phong = phong;
		this.transparency = transparency;
	}
	public Color getDiffuseColor()
	{
		return this.diffuse;
	}
	public Color getSpecularColor()
	{
		return this.spectular;
	}
	public Color getReflectionColor()
	{
		return this.reflected;
	}
	public float getPhong()
	{
		return this.phong;
	}
	public float getTransparency()
	{
		return this.transparency;
	}
}
