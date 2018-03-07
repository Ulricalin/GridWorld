import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.io.FileInputStream;

//import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import imagereader.IImageIO;



public class ImplementImageIO implements IImageIO{
    Image img;
    public Image myRead(String filePath) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            //bmp-file header must be 14 byte
            byte bmpFileHead[] = new byte[14];
            //bmp-info header must be 40 byte
            byte bmpInfoHead[] = new byte[40];
            
            fis.read(bmpFileHead, 0, 14);
            fis.read(bmpInfoHead, 0, 40);
            
            //store size of bmpFile in bmpFileHead[5:2];
            int bfSize = (int)((bmpFileHead[5] & 0xff) << 24 | (bmpFileHead[4] & 0xff) << 16
                                | (bmpFileHead[3] & 0xff) << 8 | (bmpFileHead[2] & 0xff));

            //store size of image in bmpInfoHead[23:20]
            int biSizeImage = (int)((bmpInfoHead[23] & 0xff) << 24 |(bmpInfoHead[22] & 0xff) << 16 
                                     | (bmpInfoHead[21] & 0xff) << 8 |(bmpInfoHead[20] & 0xff));
            
            //store the width of image in bmpInfoHead[7:4]
            int biWidth = (int)((bmpInfoHead[7] & 0xff) << 24 |(bmpInfoHead[6] & 0xff) << 16
                                     | (bmpInfoHead[5] & 0xff) << 8 |(bmpInfoHead[4] & 0xff));
            
            //store the height of image in bmpInfoHead[11:8]
            int biHeight = (int)((bmpInfoHead[11] & 0xff) << 24 |(bmpInfoHead[10] & 0xff) << 16
                                     | (bmpInfoHead[9] & 0xff) << 8 |(bmpInfoHead[8] & 0xff));

            //store the biBitCount---how many bit does a pixel need . 
            int biBitCount = (int)((bmpInfoHead[15] & 0xff) << 8 |(bmpInfoHead[14] & 0xff));

            if (biBitCount == 24) {
                int emptyByte = biSizeImage/biHeight - 3*biWidth;
                if (emptyByte == 4) {
                	emptyByte = 0;
                }
                int pixelArray[] = new int [biWidth*biHeight];
                byte bmp[] = new byte[biSizeImage];
                fis.read(bmp, 0, biSizeImage);

                int p = 0;

                for (int i = biHeight - 1; i >= 0; i--) {
                    for (int j = 0; j < biWidth; j++) {
                        pixelArray[ biWidth*i + j] = (0xff << 24 | (bmp[p+2] & 0xff) << 16
                            | (bmp[p+1] & 0xff) << 8 | (bmp[p] & 0xff) );
                        p += 3;
                    }
                    p += emptyByte;
                }
                img = Toolkit.getDefaultToolkit().createImage((ImageProducer) new MemoryImageSource(
                    biWidth, biHeight, pixelArray, 0, biWidth));
            }
            fis.close();
            return img;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return (Image) null;
    }
    public Image myWrite(Image image1, String s) {
    	int width = image1.getWidth(null);
    	int height = image1.getHeight(null);
    	
    	File imageFile = new File(s);
    	
    	BufferedImage bufferImage = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
    	Graphics2D graph = bufferImage.createGraphics();
    	
    	graph.drawImage(image1, 0, 0, null);
    	graph.dispose();
    	
    	try {
			ImageIO.write(bufferImage, "bmp", imageFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return image1;
    }
}
