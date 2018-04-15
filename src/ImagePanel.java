import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.io.File;

public class ImagePanel extends JPanel {

    private PUBGProfileImage pImage;
    private BufferedImage bImage;
    private JPanel panel;
    private static final Constants c = new Constants();

    public ImagePanel(File bgFile, File itemBGFile) {
        pImage = new PUBGProfileImage(bgFile, itemBGFile);
        pImage.render();
        bImage = pImage.getRender();
        panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, c.GAP, c.GAP));
        panel.setBorder(new EmptyBorder(c.GAP, c.GAP, c.GAP, c.GAP));
        panel.setSize(new Dimension(bImage.getWidth(), bImage.getHeight()));
    }

    public JPanel getPanel() {
        return panel;
    }

    public BufferedImage getImage() {
        return bImage;
    }

    public void updateItem(File itemFile, int slot) {
        pImage.setItem(itemFile, slot);
        pImage.render();
    }

    public void updateImage() {
        try {
            pImage.render();
            bImage = pImage.getRender();
            panel.getGraphics().drawImage(bImage, 0, 0, null);
        } catch(Exception e) {
            if(c.DEBUG_ENABLED)
                e.printStackTrace();
        }
    }

    public void drawText(Player p) {
        pImage.drawText(p);
    }


}
