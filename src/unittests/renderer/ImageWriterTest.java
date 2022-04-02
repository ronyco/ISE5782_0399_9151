package unittests.renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;
import renderer.ImageWriter;

public class ImageWriterTest {

    @Test
    public void testImageWriter() {
        int nX = 800;
        int nY = 500;

        Color yellowColor = new Color(java.awt.Color.YELLOW);
        Color redColor = new Color(java.awt.Color.RED);
        ImageWriter imageWriter = new ImageWriter("testYellow", nX, nY);
        for (int i = 0; i < nX; i++) {
            for (int j = 0; j < nY; j++) {
                //800/50 = 16; 500/50=10
                if (i % 50 == 0 || j % 50 == 0) {
                    imageWriter.writePixel(i, j, redColor);
                } else {
                    imageWriter.writePixel(i, j, yellowColor);
                }
            }
        }
        imageWriter.writeToImage();
    }
}
