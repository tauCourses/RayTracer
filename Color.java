public class Color {
	public final byte red,green,blue; //haha you can't change it !
	public Color(byte red,byte green,byte blue)
	{
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	public Color(String red,String green,String blue)
	{		
		this.red = (byte) ((int)(Float.valueOf(red)*255) & 0xFF) ;
		this.green = (byte) ((int)(Float.valueOf(green)*255) & 0xFF) ;
		this.blue = (byte) ((int)(Float.valueOf(blue)*255) & 0xFF) ;
	}
	public static Color average(Color[] arr)
	{
		int red=0,green=0,blue=0;
		for(Color c:arr)
		{
			red += c.red&0xff;
			green += c.green&0xff;
			blue += c.blue&0xff;
		}
		return new Color(	(byte)(red/arr.length),
							(byte)(green/arr.length),
							(byte)(blue/arr.length) );
	}
	public Color add(Color c)
	{
		int red=this.red&0xff,green=this.green&0xff,blue=this.blue&0xff;
		
		//System.out.print(this + " " + c);
		red += c.red&0xff;
		green += c.green&0xff;
		blue += c.blue&0xff;
		
		//System.out.println(" " + red + " " + (byte)(red));
		
		Color co =  new Color(	(byte)(red),
							(byte)(green),
							(byte)(blue) );
		//System.out.println("dfggd - " + co);
		return co;
	}
	
	public Color multiply(Color c)
	{
		int red=this.red&0xff,green=this.green&0xff,blue=this.blue&0xff;
		
		red *= c.red&0xff;
		green *= c.green&0xff;
		blue *= c.blue&0xff;
		
		return new Color(	(byte)(red/255),
							(byte)(green/255),
							(byte)(blue/255) );
	}
	
	public Color scalarProduct(float d)
	{
		int red=this.red&0xff,green=this.green&0xff,blue=this.blue&0xff;
		
		
		return new Color(	(byte)(red*d),
							(byte)(green*d),
							(byte)(blue*d) );
	}
	public String toString()
	{
		return ("("+this.red+", "+this.green+", "+this.blue+")");
	}
}