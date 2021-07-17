package playerGUI;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class GUI implements ActionListener {
    LinkedList<JLabel> musicQueue = new LinkedList<>();
    JFrame frame;
    JLabel label;
    JLabel label1;
    JLabel label2;
    JLabel label3;
    JLabel label4;
    JLabel header;
    JLabel qheader;
    Music music1 = new Music("Reborn", "Kanye West","KIDS SEE GHOSTS", 384);
    Music music2 = new Music("4th Dimension", "Kanye West","KIDS SEE GHOSTS", 384);
    Music music3 = new Music("Feel The Love", "Kanye West","KIDS SEE GHOSTS", 384);
    Music music4 = new Music("Cudi Montage", "Kanye West","KIDS SEE GHOSTS", 384);
    Music music5 = new Music("Kids See Ghostss", "Kanye West","KIDS SEE GHOSTS", 384);

    public GUI() throws IOException {
        //teste
        JProgressBar teste = new JProgressBar();
        JButton testb = new JButton();


        header = new JLabel("MY PLAYLIST");
        header.setFont(new Font("Arial Black", Font.PLAIN, 30)); //SET FONT
        header.setForeground(new Color(0XFFFFFF));

        qheader = new JLabel("QUEUE");
        qheader.setFont(new Font("Arial Black", Font.PLAIN, 30)); //SET FONT
        qheader.setForeground(new Color(0XFFFFFF));

        label = new musicLabel(music1);label1 = new musicLabel(music2);label2 = new musicLabel(music3);label3 = new musicLabel(music4);label4 = new musicLabel(music5);

        JPanel musicas = new JPanel();
        musicas.setBounds(10,10,300,400);
        musicas.setBackground(new Color(0x141414));
        musicas.setLayout(new FlowLayout(FlowLayout.LEFT));
        musicas.add(header);
        musicas.add(label);
        musicas.add(label1);
        musicas.add(label2);
        musicas.add(label3);
        musicas.add(label4);
        /*musicas.setBackground(Color.BLUE);
        musicas.setOpaque(true);*/

        JPanel controls = new JPanel();
        controls.setBounds(10,410,590,100);
        controls.setBackground(Color.GREEN);
        controls.setOpaque(false);
        controls.add(teste);
        controls.add(testb);

        //teste
        JLabel musicInQueue1 = new musicInQueue(music1);
        musicQueue.add(musicInQueue1);

        JPanel queue = new JPanel();
        queue.setBounds(320,10, 200, 400);
        queue.setLayout(new FlowLayout(FlowLayout.LEFT));
        queue.setBackground(new Color(0x141414));
        queue.add(qheader);
        for (JLabel jLabel : musicQueue) {
            queue.add(jLabel);
        }

        frame = new myFrame();
        frame.add(queue);
        frame.add(musicas);
        frame.add(controls);

    }

    public static void main(String[] args) throws IOException {
        new GUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
class musicLabel extends JLabel {
    JButton button;
    musicLabel(Music music) throws IOException {
        button = new addToQueueButton();
        this.add(button);
        BufferedImage myPicture = ImageIO.read(new File("MusicPlayer\\src\\imgs\\image.jpg"));
        ImageIcon albumIcon = new ImageIcon(myPicture);

        //adjust the label size
        this.setBorder(BorderFactory.createEmptyBorder());
        //adjust the thext inside the label
        this.setText("<html>"+music.name+"<br>"+music.artist+"<br>"+music.album+"</html>");
        this.setFont(new Font("Arial Black", Font.PLAIN, 11)); //SET FONT
        this.setForeground(new Color(0XFFFFFF)); // SET FONT COLOR
        //adjust the icon and the text
        this.setIcon(albumIcon);
        this.setHorizontalTextPosition(JLabel.RIGHT);
        this.setVerticalTextPosition(JLabel.CENTER);
        this.setIconTextGap(20);
        //set the label background color
        this.setBackground(Color.BLUE);
        this.setOpaque(false);
        //align the text
        this.setVerticalAlignment(JLabel.CENTER);
        this.setHorizontalAlignment(JLabel.LEFT);
        //adjust the location of the label
        this.setBorder(BorderFactory.createEmptyBorder(2,3,2,297));
        this.setBounds(0, 0, 300, 100);
    }
}
class musicInQueue extends JLabel{
    musicInQueue (Music music){
        this.setBorder(BorderFactory.createEmptyBorder());
        //adjust the thext inside the label
        this.setText("<html>"+music.name+"<br>"+music.artist+"<br>"+music.album+"</html>");
        this.setFont(new Font("Arial Black", Font.PLAIN, 11)); //SET FONT
        this.setForeground(new Color(0XFFFFFF)); // SET FONT COLOR
        //set the label background color
        this.setBackground(Color.BLUE);
        this.setOpaque(false);
        //align the text
        this.setVerticalAlignment(JLabel.CENTER);
        this.setHorizontalAlignment(JLabel.LEFT);
        //adjust the location of the label
        this.setBorder(BorderFactory.createEmptyBorder(2,3,2,297));
        this.setBounds(0, 0, 200, 100);
    }
}
class myFrame extends JFrame {
    myFrame(){

        this.setTitle("Music Player");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(630, 560); //565 110
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
        this.getContentPane().setBackground(new Color(0x141414));
    }
}
class addToQueueButton extends JButton {

    addToQueueButton() throws IOException {
        BufferedImage image = ImageIO.read(new File("MusicPlayer\\src\\imgs\\addButton.png"));
        ImageIcon addButtonIcon = new ImageIcon(image);
        this.setIcon(addButtonIcon);
        this.setBounds(220,21,30,40);
        this.setBackground(new Color(0x141414));
        this.setBorder(null);
        this.setText("ADD");
        this.setHorizontalTextPosition(JButton.CENTER);
        this.setVerticalTextPosition(JButton.BOTTOM);
        this.setIconTextGap(-3);
        this.setFont(new Font("Arial Black", Font.PLAIN, 11));
        this.setForeground((new Color(0xFFFFFF)));
        this.setFocusable(false);
    }
}