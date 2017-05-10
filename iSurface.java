
public interface iSurface {
	public Point intersectes(Vector v); 
	public Color getDiffuseColor();
	public Color getSpecularColor();
	public Color getReflectionColor();
	public float getPhong();
	public float getTransparency();
}
