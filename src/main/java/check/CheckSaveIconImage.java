package check;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class CheckSaveIconImage {
    public static void main(String[] args) {
        //save draw hexagon image
        //save draw circle image
        //save draw triangle image
        int width = 1024; //width of image
        int height = 1024; //height of image
        File file = new File("./data/images/hexagon.png");
        var img = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        var g = img.createGraphics();

        g.setColor(Color.CYAN);
        int corners = 6;
        int radius = 500;
        int[] xPoints = new int[corners];
        int[] yPoints = new int[corners];
        for (int i = 0; i < corners; i++) {
            xPoints[i] = (int) (width/2 + radius * Math.cos(2 * Math.PI * i / corners));
            yPoints[i] = (int) (height/2 + radius * Math.sin(2 * Math.PI * i / corners));
        }
        g.fillPolygon(xPoints, yPoints, corners);
        g.dispose();
        try {
            ImageIO.write(img, "png", file);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Save icon image successfully");
    }

}
