package guiteste;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUIteste2 {
    JFrame frame;
    JLabel label;

    public GUIteste2() throws IOException {

        BufferedImage myPicture = ImageIO.read(new File("MusicPlayer\\src\\imgs\\image.jpg"));
        JLabel icon = new JLabel(new ImageIcon(myPicture));

        frame=new JFrame();
        frame.add(icon);
        frame.pack();
        frame.setVisible(true);
    }
    public static void main(String[] args) throws IOException {
        new GUIteste2();
    }
}
