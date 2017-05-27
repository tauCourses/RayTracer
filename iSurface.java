
public interface iSurface {
	public void intersectes(Ray ray);
	public boolean inDistance(Ray ray, float min, float max);
	public Color getDiffuseColor();
	public Color getSpecularColor();
	public Color getReflectionColor();
	public float getPhong();
	public float getTransparency();
}
