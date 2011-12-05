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

    public ImagePanel(String name) {
        super(true);
        this.setToolTipText(name);
        this.add(new JLabel(name));
        try {
            img = ImageIO.read(new File(name));
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
