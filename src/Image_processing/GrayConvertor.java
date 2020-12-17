package Image_processing;

import java.io.File;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import edu.duke.ImageResource;
import edu.duke.Pixel;

public class GrayConvertor {

	public void doSave() {
		DirectoryResource directoryResource=new DirectoryResource();
		for (File f : directoryResource.selectedFiles()) {
			ImageResource image=new ImageResource(f);
			String fileName = image.getFileName();
			String newname="copy-"+fileName;
			image.setFileName(newname);
			image.draw();
			image.save();
		}
	}
	
	public ImageResource makeGray(ImageResource inImg) {

		ImageResource outImage = new ImageResource(inImg.getWidth(), inImg.getHeight());

		for (Pixel pixel : outImage.pixels()) {

			Pixel inPixel = inImg.getPixel(pixel.getX(), pixel.getY());
			int avg = (inPixel.getRed() + inPixel.getGreen() + inPixel.getBlue()) / 3;
			pixel.setBlue(avg);
			pixel.setGreen(avg);
			pixel.setRed(avg);

		}
		return outImage;

	}
	public void convertManyGrayConvertor() {
		DirectoryResource directoryResource=new DirectoryResource();
		for (File resource : directoryResource.selectedFiles()) {
		
			ImageResource imageResource=makeGray(new ImageResource(resource));
			imageResource.draw();
		}
	}
	
	public void makeInvert() {
		ImageResource inImg=new ImageResource();
		ImageResource outImage = new ImageResource(inImg.getWidth(), inImg.getHeight());

		for (Pixel pixel : outImage.pixels()) {

			Pixel inPixel = inImg.getPixel(pixel.getX(), pixel.getY());
			pixel.setBlue(255-inPixel.getBlue());
			pixel.setGreen(255-inPixel.getGreen());
			pixel.setRed(255-inPixel.getRed());

		}
		outImage.draw();

	}
	public static void main(String[] args) {
		//ImageResource imageResource = new ImageResource(); // popup willopen nd ask which image u want ot choose
		GrayConvertor convertor = new GrayConvertor();

		//ImageResource img = convertor.makeGray(imageResource);
		//img.draw();
		//convertor.convertManyGrayConvertor();
	//convertor.makeInvert();
		
		convertor.doSave();
	}
}
