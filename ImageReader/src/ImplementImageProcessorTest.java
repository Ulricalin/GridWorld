import static org.junit.Assert.*;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.imageio.ImageIO;

import org.junit.Test;

public class ImplementImageProcessorTest {
	
	
	@Test
	public void testShowChanelR() {
		try {
			FileInputStream fis = new FileInputStream("../bmptest/goal/1_red_goal.bmp");
			BufferedImage outputfile = ImageIO.read(fis);
			
			FileInputStream fis2 = new FileInputStream("../bmptest/mySave/1_red");
			BufferedImage red = ImageIO.read(fis2);
  	
	    	assertEquals(red.getWidth(), outputfile.getWidth());
	    	assertEquals(red.getHeight(), outputfile.getHeight());
	    	
	    	for (int i = 0; i < red.getWidth(); i++) {
	    		for (int j = 0; j < red.getHeight(); j++) {
	    			assertEquals(red.getRGB(i, j), outputfile.getRGB(i, j));
	    		}
	    	}
	    	fis.close();
	    	fis2.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	public void testShowChanelG() {
		try {
			FileInputStream fis = new FileInputStream("../bmptest/goal/1_green_goal.bmp");
			BufferedImage outputfile = ImageIO.read(fis);
			
			FileInputStream fis2 = new FileInputStream("../bmptest/mySave/1_green");
			BufferedImage green = ImageIO.read(fis2);
  	
	    	assertEquals(green.getWidth(), outputfile.getWidth());
	    	assertEquals(green.getHeight(), outputfile.getHeight());
	    	
	    	for (int i = 0; i < green.getWidth(); i++) {
	    		for (int j = 0; j < green.getHeight(); j++) {
	    			assertEquals(green.getRGB(i, j), outputfile.getRGB(i, j));
	    		}
	    	}
	    	fis.close();
	    	fis2.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testShowChanelB() {
		try {
			FileInputStream fis = new FileInputStream("../bmptest/goal/1_blue_goal.bmp");
			BufferedImage outputfile = ImageIO.read(fis);
			
			FileInputStream fis2 = new FileInputStream("../bmptest/mySave/1_blue");
			BufferedImage blue = ImageIO.read(fis2);
  	
	    	assertEquals(blue.getWidth(), outputfile.getWidth());
	    	assertEquals(blue.getHeight(), outputfile.getHeight());
	    	
	    	for (int i = 0; i < blue.getWidth(); i++) {
	    		for (int j = 0; j < blue.getHeight(); j++) {
	    			assertEquals(blue.getRGB(i, j), outputfile.getRGB(i, j));
	    		}
	    	}
	    	fis.close();
	    	fis2.close();
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testShowGray() {
		try {
			FileInputStream fis = new FileInputStream("../bmptest/goal/2_gray_goal.bmp");
			BufferedImage outputfile = ImageIO.read(fis);
			
			FileInputStream fis2 = new FileInputStream("../bmptest/mySave/2_gray");
			BufferedImage gray = ImageIO.read(fis2);
  	
	    	assertEquals(gray.getWidth(), outputfile.getWidth());
	    	assertEquals(gray.getHeight(), outputfile.getHeight());
	    	
	    	for (int i = 0; i < gray.getWidth(); i++) {
	    		for (int j = 0; j < gray.getHeight(); j++) {
	    			assertEquals(gray.getRGB(i, j), outputfile.getRGB(i, j));
	    		}
	    	}
	    	fis.close();
	    	fis2.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
