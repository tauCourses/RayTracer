import java.util.ArrayList;

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
		this.red = Byte.valueOf(red);
		this.green = Byte.valueOf(green);
		this.blue = Byte.valueOf(blue);
	}
	public static Color average(ArrayList<Color> arr)
	{
		int red=0,green=0,blue=0;
		for(Color c:arr)
		{
			red += c.red&0xff;
			green += c.green&0xff;
			blue += c.blue&0xff;
		}
		return new Color(	(byte)(red/arr.size()),
							(byte)(green/arr.size()),
							(byte)(blue/arr.size()) );
	}
}
