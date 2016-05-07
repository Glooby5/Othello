package ija.ija2015.othello.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

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
    }

    public BufferedImage getImage() {
        return image;
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
