public class Color {
	public final float red,green,blue; //haha you can't change it !
	
	public Color(float red,float green,float blue)
	{
		this.red = Math.min(1.0f,red);
		this.green =  Math.min(1.0f,green);
		this.blue =  Math.min(1.0f,blue);
	}
	public Color(String red,String green,String blue)
	{		
		this(Float.valueOf(red),Float.valueOf(green),Float.valueOf(blue)) ;
	}
	public static Color average(Color[] arr)
	{
		float red=0,green=0,blue=0;
		for(Color c:arr)
		{
			red += c.red;
			green += c.green;
			blue += c.blue;
		}
		return new Color(red/arr.length,
						green/arr.length,
						blue/arr.length);
	}
	public Color add(Color c)
	{
		return new Color(this.red+c.red,this.green+c.green,this.blue+c.blue);
	}
	
	public Color multiply(Color c)
	{
		return new Color(this.red*c.red, this.green*c.green, this.blue*c.blue);
	}
	
	public Color scalarProduct(float d)
	{
		return new Color(this.red*d,this.green*d,this.blue*d);

	}
	public String toString()
	{
		return ("("+this.red+", "+this.green+", "+this.blue+")");
	}
}