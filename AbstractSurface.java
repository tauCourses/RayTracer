
public abstract class AbstractSurface implements iSurface{
	private Material material;
	static float epsilon = 0.001f;
	public AbstractSurface(Material material)
	{
		this.material = material;
	}
	
	public Color getDiffuseColor() {
		return this.material.getDiffuseColor();
	}
	@Override
	public Color getSpecularColor() {
		return this.material.getSpecularColor();
	}
	@Override
	public Color getReflectionColor() {
		return this.material.getReflectionColor();
	}
	@Override
	public float getPhong() {
		return this.material.getPhong();
	}
	@Override
	public float getTransparency() {
		return this.material.getTransparency();
	}
}
