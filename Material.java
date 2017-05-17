
public class Material {
	private Color diffuse, spectular, reflected;
	private double phong, transparency;
	public Material(Color diffuse, Color spectular, Color reflected, double phong, double transparency)
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
	public double getPhong()
	{
		return this.phong;
	}
	public double getTransparency()
	{
		return this.transparency;
	}
}
