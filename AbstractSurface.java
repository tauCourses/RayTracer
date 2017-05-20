
public abstract class AbstractSurface implements iSurface{
	private Material material;
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
	public double getPhong() {
		return this.material.getPhong();
	}
	@Override
	public double getTransparency() {
		return this.material.getTransparency();
	}
}
