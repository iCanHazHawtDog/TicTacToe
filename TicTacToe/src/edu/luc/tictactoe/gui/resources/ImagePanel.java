/**
 * 
 */
package edu.luc.tictactoe.gui.resources;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author matt
 *
 */
@SuppressWarnings("serial")
public class ImagePanel extends JPanel {

    BufferedImage img;

    public ImagePanel(URL url) {
        this.setToolTipText(url.toString());
        try {
            img = ImageIO.read(url);
            this.setPreferredSize(new Dimension(
                img.getWidth(), img.getHeight()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
    }
}
