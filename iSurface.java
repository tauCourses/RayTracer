
public interface iSurface {
	public Point intersectes(Ray ray); 
	public Color getDiffuseColor();
	public Color getSpecularColor();
	public Color getReflectionColor();
	public float getPhong();
	public float getTransparency();
}
