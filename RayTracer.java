import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.SampleModel;
import java.awt.image.WritableRaster;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 *  Main class for ray tracing exercise.
 */
public class RayTracer {

	public int imageWidth;
	public int imageHeight;
	private Camara camara;
	private ArrayList<Material> materialList; 
	private ArrayList<iSurface> surfacesTemp;
	
	private iSurface[] surfaces;
	private Color backgroundColor;
	private int numberOfShadowRays;
	private int squareNumberOfShadowRays;
	private int maximumRecursionLevel;
	private int superSampelingLevel;
	private Color black;
	//private Color white;
	private ArrayList<Light> lights;
	//private Random generatorRandoms;
	/**
	 * Runs the ray tracer. Takes scene file, output image file and image size as input.
	 */
	public RayTracer()
	{
		this.black = new Color("0","0","0");
		//this.white = new Color("1","1","1");
		this.materialList = new ArrayList<>();
		this.surfacesTemp = new ArrayList<>();
		this.lights = new ArrayList<>();
		//this.generatorRandoms = new Random();
	}
	public static void main(String[] args) throws IOException, RayTracerException {
		//try {
		RayTracer tracer = new RayTracer();
		 	
            // Default values:
			tracer.imageWidth = 500;
			tracer.imageHeight = 500;

			if (args.length < 2)
				throw new RayTracerException("Not enough arguments provided. Please specify an input scene file and an output image file for rendering.");

			String sceneFileName = args[0];
			String outputFileName = args[1];

			if (args.length > 3)
			{
				tracer.imageWidth = Integer.parseInt(args[2]);
				tracer.imageHeight = Integer.parseInt(args[3]);
			}


			// Parse scene file:
			tracer.parseScene(sceneFileName);

			// Render scene:
			tracer.renderScene(outputFileName);

		/*} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (RayTracerException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}*/


	}

	/**
	 * Parses the scene file and creates the scene. Change this function so it generates the required objects.
	 */
	public void parseScene(String sceneFileName) throws IOException, RayTracerException
	{
		FileReader fr = new FileReader(sceneFileName);
		BufferedReader r = new BufferedReader(fr);
		String line = null;
		int lineNum = 0;
		//System.out.println("Started parsing scene file " + sceneFileName);



		while ((line = r.readLine()) != null)
		{
			line = line.trim();
			++lineNum;

			if (line.isEmpty() || (line.charAt(0) == '#'))
			{  // This line in the scene file is a comment
				continue;
			}
			else
			{
				String code = line.substring(0, 3).toLowerCase();
				// Split according to white space characters:
				String[] params = line.substring(3).trim().toLowerCase().split("\\s+");

				if (code.equals("cam"))
				{
					//System.out.println(line);
					
					this.camara = new Camara(	new Vector(params[0],params[1],params[2]),
												new Vector(params[3],params[4],params[5]),
												new Vector(params[6],params[7],params[8]),
												Float.valueOf(params[9]),
												Float.valueOf(params[10]));
					//System.out.println(String.format("Parsed camera parameters (line %d)", lineNum));
				}
				else if (code.equals("set"))
				{
					backgroundColor = new Color(params[0],params[1],params[2]);
					this.numberOfShadowRays = Integer.valueOf(params[3]);
					this.squareNumberOfShadowRays = this.numberOfShadowRays*this.numberOfShadowRays; 
					this.maximumRecursionLevel = Integer.valueOf(params[4]);
					this.superSampelingLevel = Integer.valueOf(params[5]);
					//System.out.println(String.format("Parsed sets parameters (line %d)", lineNum));
					
				}
				else if (code.equals("mtl"))
				{
                    Material material = new Material(new Color(params[0],params[1],params[2]),
                    							new Color(params[3],params[4],params[5]),
                    							new Color(params[6],params[7],params[8]),
                    							Float.valueOf(params[9]), 
                    							Float.valueOf(params[10]));
                    this.materialList.add(material);          
				//	System.out.println(String.format("Parsed material (line %d)", lineNum));
				}
				else if (code.equals("sph"))
				{
					Sphere sphere = new Sphere(this.materialList.get(Integer.valueOf(params[4])-1),
												new Vector(params[0],params[1],params[2]),
												Float.valueOf(params[3]));
					this.surfacesTemp.add(sphere);
			//		System.out.println(String.format("Parsed sphere (line %d)", lineNum));
				}
				else if (code.equals("pln"))
				{
					infinityPlane infinityPlane = new infinityPlane(this.materialList.get(Integer.valueOf(params[4])-1),
                    												new Vector(params[0],params[1],params[2]), 
                    												Float.valueOf(params[3]));
                    this.surfacesTemp.add(infinityPlane);
		//			System.out.println(String.format("Parsed plane (line %d)", lineNum));
				}
				else if (code.equals("trg"))
				{
                    Triangle triangle = new Triangle(this.materialList.get(Integer.valueOf(params[9])-1),
                    								new Vector(params[0],params[1],params[2]), 
                    								new Vector(params[3],params[4],params[5]), 
                    								new Vector(params[6],params[7],params[8]));
                    this.surfacesTemp.add(triangle);
	//				System.out.println(String.format("Parsed cylinder (line %d)", lineNum));
				}
				else if (code.equals("lgt"))
				{
					Light light = new Light(new Vector(params[0], params[1],params[2]), 
											new Color(params[3],params[4],params[5]), 
											Float.valueOf(params[6]), 
											Float.valueOf(params[7]), 
											Float.valueOf(params[8]));
					this.lights.add(light);

					//System.out.println(String.format("Parsed light (line %d)", lineNum));
				}
				else
				{
					System.out.println(String.format("ERROR: Did not recognize object: %s (line %d)", code, lineNum));
				}
			}
		}

                // It is recommended that you check here that the scene is valid,
                // for example camera settings and all necessary materials were defined.

		System.out.println("Finished parsing scene file " + sceneFileName);
		r.close();
	}

	/**
	 * Renders the loaded scene and saves it to the specified file location.
	 */
	public void renderScene(String outputFileName)
	{
		//this.numberOfShadowRays = 1;
		//	this.superSampelingLevel = 1;
		this.squareNumberOfShadowRays = this.numberOfShadowRays * this.numberOfShadowRays;
		System.out.println("shadow rays - " + this.numberOfShadowRays);
		System.out.println("super sampling - " + this.superSampelingLevel);
		long startTime = System.currentTimeMillis();
		this.camara.createScreen(this.imageWidth, this.imageHeight, this.superSampelingLevel);
		// Create a byte array to hold the pixel data:
		byte[] rgbData = new byte[this.imageWidth * this.imageHeight * 3];
		//System.out.println(this.surfaces.size());
		Ray[] rays = new Ray[this.superSampelingLevel*this.superSampelingLevel];
		Color[] colors = new Color[this.superSampelingLevel*this.superSampelingLevel];
		this.surfaces = new iSurface[this.surfacesTemp.size()];
		for(int i=0; i< surfaces.length; i++)
			this.surfaces[i] = this.surfacesTemp.get(i);
		for(int i=0; i< rays.length; i++)
			rays[i] = new Ray();
		for(int i=0;i<this.imageHeight;i++)
		{
			//System.out.println(i);
			for(int j=0;j<this.imageWidth;j++)
			{
				this.camara.getScreenVectors(rays,i,j);
			
				for(int k=0; k< rays.length; k++)
				{
					for(iSurface surface: surfaces)
						surface.intersectes(rays[k]);
					
					rays[k].getIntersection();
					colors[k] = getColorFromRay(rays[k],1);
				}
				Color c = Color.average(colors);
				rgbData[(i*this.imageWidth + j)*3] = (byte)(c.red*255);
				rgbData[(i*this.imageWidth + j)*3+1] = (byte)(c.green*255);
				rgbData[(i*this.imageWidth + j)*3+2] = (byte)(c.blue*255);
			}
		}
		long endTime = System.currentTimeMillis();
		Long renderTime = endTime - startTime;

        // The time is measured for your own conveniece, rendering speed will not affect your score
        // unless it is exceptionally slow (more than a couple of minutes)
		System.out.println("Finished rendering scene in " + renderTime.toString() + " milliseconds.");

        // This is already implemented, and should work without adding any code.
		saveImage(this.imageWidth, rgbData, outputFileName);

		System.out.println("Saved file " + outputFileName);

	}




	//////////////////////// FUNCTIONS TO SAVE IMAGES IN PNG FORMAT //////////////////////////////////////////

	/*
	 * Saves RGB data as an image in png format to the specified location.
	 */
	public static void saveImage(int width, byte[] rgbData, String fileName)
	{
		try {

			BufferedImage image = bytes2RGB(width, rgbData);
			ImageIO.write(image, "png", new File(fileName));

		} catch (IOException e) {
			System.out.println("ERROR SAVING FILE: " + e.getMessage());
		}

	}

	/*
	 * Producing a BufferedImage that can be saved as png from a byte array of RGB values.
	 */
	public static BufferedImage bytes2RGB(int width, byte[] buffer) {
	    int height = buffer.length / width / 3;
	    ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_sRGB);
	    ColorModel cm = new ComponentColorModel(cs, false, false,
	            Transparency.OPAQUE, DataBuffer.TYPE_BYTE);
	    SampleModel sm = cm.createCompatibleSampleModel(width, height);
	    DataBufferByte db = new DataBufferByte(buffer, width * height);
	    WritableRaster raster = Raster.createWritableRaster(sm, db, null);
	    BufferedImage result = new BufferedImage(cm, raster, false, null);

	    return result;
	}

	public static class RayTracerException extends Exception {
		private static final long serialVersionUID = 1L;

		public RayTracerException(String msg) {  super(msg); }
	}
	
	private Color getColorFromRay(Ray ray, int time)
	{
		if(ray.d < Float.MAX_VALUE-3 && time < this.maximumRecursionLevel)
		{
	
			//System.out.println(ray.surface.getDiffuseColor());
			Color backGround = this.black;
			if(ray.surface.getTransparency() > 0)
			{
				backGround = backGround.add(getBackGroundColor(ray,time+1).scalarProduct(ray.surface.getTransparency()));
			//	System.out.println(backGround + " " + ray.surface.getTransparency());
			}
			Color diffuzedAndSpecular = this.black;
			for (Light light : this.lights)
			{
				float numOfLightHits = light.numOfLightRaysHits(ray, this.surfaces, this.numberOfShadowRays);
				//System.out.println(numOfLightHits);
				diffuzedAndSpecular = diffuzedAndSpecular.add(getDiffusedColor(ray,light, numOfLightHits));
				if (light.mainRay > 0.001)
					diffuzedAndSpecular = diffuzedAndSpecular.add(getSpecularColor(ray,light));
				
			}
			diffuzedAndSpecular = diffuzedAndSpecular.scalarProduct(1-ray.surface.getTransparency());
			return backGround.add(diffuzedAndSpecular).add(getReflectionColor(ray,time+1));
		}
		else
			return this.backgroundColor;
	}
	
	private Color getBackGroundColor(Ray ray, int time)
	{		
		Ray newRay = new Ray();
		//float cos = Vector.dotProduct(ray.getNormal(), ray.direction);
		
		newRay.setNewRay(ray.intersection,ray.direction);
		
		for(iSurface surface: surfaces)
		{
			if(surface == ray.surface)
				continue;
			surface.intersectes(newRay);
		}
		newRay.getIntersection();
		
		return getColorFromRay(newRay, time);
	}
	
	private Color getDiffusedColor(Ray ray, Light light, float numOfLightHits) //need to be complete:
	{	
		if(Vector.dotProduct(ray.getNormal(), light.direction.getProjection(ray.getNormal())) >0 && ray.surface.getTransparency() < 0.2)
			return this.black;
		
		float cos = Vector.dotProduct(ray.getNormal(), light.direction); //they both unit vector so no need to divide by the length
		
		Color lightIntensity = ray.surface.getDiffuseColor().multiply(light.color);
		
		if (numOfLightHits == 0)
			lightIntensity = lightIntensity.scalarProduct(1-light.shadowIntensity); 
		else	
			lightIntensity = lightIntensity.scalarProduct((float)(numOfLightHits)/this.squareNumberOfShadowRays);
		
		
		//System.out.println("a - " +resultDiffuseColor);
		return lightIntensity.scalarProduct(Math.abs(cos));
	}

	private Color getSpecularColor(Ray ray, Light light) //need to be complete:
	{
		Vector b = light.direction.getProjection(ray.getNormal()).scalarProduct(-2);
		Vector lightReflection = light.direction.add(b).toUnitVector();
		float cos = Vector.dotProduct(ray.toCam, lightReflection);
		//System.out.println(ray.surface.getSpecularColor());
		if(cos>0)
		{
			Color c = ray.surface.getSpecularColor().multiply(light.color).
					scalarProduct((float)(Math.pow(cos, ray.surface.getPhong())*light.specularIntensity));
			return c;
		}
		return this.black;
	}
	
	private Color getReflectionColor(Ray ray, int time)
	{
		Vector b = ray.direction.getProjection(ray.getNormal()).scalarProduct(-2);
		
		iSurface i = ray.surface;
		ray.setNewRay(ray.intersection, ray.direction.add(b));
		
		for(iSurface surface: this.surfaces)
		{
			if(surface == i)
				continue;
			surface.intersectes(ray);
		}
		ray.getIntersection();
		
		return getColorFromRay(ray,time).multiply(i.getReflectionColor());
	}

}
