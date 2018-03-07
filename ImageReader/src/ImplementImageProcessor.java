import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;

import imagereader.IImageProcessor;

public class ImplementImageProcessor implements IImageProcessor
{
	public static final int RED = 1;
	public static final int GREEN = 2;
	public static final int BLUE = 3;
	public static final int GRAY = 4;
	@Override
	public Image showChanelB(Image sourceImage) {
		ColorFilter blueFilter = new ColorFilter(BLUE);
		Image img = Toolkit.getDefaultToolkit().createImage(
						new FilteredImageSource(sourceImage.getSource(), blueFilter));
		return img;
	}

	@Override
	public Image showChanelR(Image sourceImage) {
		ColorFilter redFilter = new ColorFilter(RED);
		Image img = Toolkit.getDefaultToolkit().createImage(
						new FilteredImageSource(sourceImage.getSource(), redFilter));
		return img;
	}

	@Override
	public Image showChanelG(Image sourceImage) {
		ColorFilter greenFilter = new ColorFilter(GREEN);
		Image img = Toolkit.getDefaultToolkit().createImage(
						new FilteredImageSource(sourceImage.getSource(), greenFilter));
		return img;
	}

	@Override
	public Image showGray(Image sourceImage) {
		ColorFilter grayFilter = new ColorFilter(GRAY);
		Image img = Toolkit.getDefaultToolkit().createImage(
						new FilteredImageSource(sourceImage.getSource(), grayFilter));
		return img;
	}
	class ColorFilter extends RGBImageFilter {
		int num;
		
		ColorFilter(int x) {
			num = x;
			canFilterIndexColorModel = true;
		}

		@Override
		public int filterRGB(int x, int y, int rgb) {
			if (num == RED) {
				return (rgb & 0xffff0000);
			} else if (num == GREEN) {
				return (rgb & 0xff00ff00);
			} else if (num == BLUE) {
				return (rgb & 0xff0000ff);
			} else {
				int gray = (int)(((rgb & 0x00ff0000) >> 16)*0.299 + ((rgb & 0x0000ff00) >> 8)*0.587
							+ (rgb & 0x000000ff)*0.114);
				return (rgb & 0xff000000) + (gray << 16) + (gray << 8) + gray;
			}
		}
		
	}
}
