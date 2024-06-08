package Tester;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Helper {
    public static void save(ImageIcon imageIcon){

        BufferedImage image = new BufferedImage(
                imageIcon.getIconWidth(),
                imageIcon.getIconHeight(),
                BufferedImage.TYPE_INT_ARGB
        );

        image.createGraphics().drawImage(imageIcon.getImage(), 0, 0, null);

        try{
            ImageIO.write(image, "png", new File(
                    "src/main/java/images/ss.png"));
        }catch (IOException e){
            System.out.println("Error: "+ e.getMessage());
        }

    }
}
