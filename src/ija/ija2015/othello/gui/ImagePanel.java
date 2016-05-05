package ija.ija2015.othello.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Ondřej on 5. 5. 2016.
 */

public class ImagePanel extends JPanel {
    private BufferedImage image;

    public ImagePanel(BufferedImage img) {
        super();
        setLayout(new BorderLayout());
        setOpaque(false);
        image = img;
       /* addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("Do Something Clicked on disk.");
            }
        });*/
    }

    public void changeImage(URL Path) {
        try {
            image = ImageIO.read(Path);
        }
        catch(IOException ioe) {
            System.out.println("Chyba při načítání obrázku z resources.");
            ioe.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        if(g != null && image != null)
            g.drawImage(image, 0, 0, this);
    }

    @Override
    public Dimension getPreferredSize()
    {
        return (new Dimension(image.getWidth(), image.getHeight()));
    }
}
