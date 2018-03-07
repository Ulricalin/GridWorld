import imagereader.Runner;
public class ImageReaderRunner {
	public static void main (String [] args) {
		ImplementImageIO imageioer = new ImplementImageIO();
		ImplementImageProcessor imageProcessor = new ImplementImageProcessor();
		Runner.run(imageioer, imageProcessor);
	}
}
