package unittests.renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;
import renderer.ImageWriter;

public class ImageWriterTest {

    @Test
    public void testImageWriter(){
       ImageWriter imageWriter= new ImageWriter("myFirstImage", 800,500);
       for(int j = 0; j < 500; j++){
           for(int i = 0; i<800; i++){
               if(j%50==0)
                   imageWriter.writePixel(i,j,new Color(2,100,255));
               else
                   if(i%50==0)
                        imageWriter.writePixel(i,j,new Color(2,100,255));
                   else
                        imageWriter.writePixel(i,j,new Color(1,50,60));
           }
       }
       imageWriter.writeToImage();
    }
}
