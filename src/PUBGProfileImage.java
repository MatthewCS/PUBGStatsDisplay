import org.imgscalr.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class PUBGProfileImage {

    private BufferedImage defbg;
    private BufferedImage bg;
    private BufferedImage item1;
    private BufferedImage item2;
    private BufferedImage item3;
    private BufferedImage item4;
    private BufferedImage itemBg;
    private BufferedImage render;
    private final int[] BG_RES = {2048, 2048};
    private final int[] ITEM_RES = {512, 512};
    private final int[] RENDER_RES = {512, 512};
    private static final Constants c = new Constants();

    public PUBGProfileImage(File bgFile, File itemBGFile) {
        try {
            defbg = ImageIO.read(bgFile);
            bg = ImageIO.read(bgFile);
            itemBg = ImageIO.read(itemBGFile);
        } catch(Exception e) {
            if(c.DEBUG_ENABLED)
                e.printStackTrace();
            bg = new BufferedImage(BG_RES[0], BG_RES[1], BufferedImage.TYPE_INT_ARGB);
            itemBg = new BufferedImage(ITEM_RES[0], ITEM_RES[1], BufferedImage.TYPE_INT_ARGB);
        }
        item1 = new BufferedImage(ITEM_RES[0], ITEM_RES[1], BufferedImage.TYPE_INT_ARGB);
        item2 = new BufferedImage(ITEM_RES[0], ITEM_RES[1], BufferedImage.TYPE_INT_ARGB);
        item3 = new BufferedImage(ITEM_RES[0], ITEM_RES[1], BufferedImage.TYPE_INT_ARGB);
        item4 = new BufferedImage(ITEM_RES[0], ITEM_RES[1], BufferedImage.TYPE_INT_ARGB);
        render = new BufferedImage(RENDER_RES[0], RENDER_RES[1], BufferedImage.TYPE_INT_ARGB);
    }

    public boolean setItem(File itemFile, int slot) {
        try {
            BufferedImage newItem = Scalr.resize(ImageIO.read(itemFile), ITEM_RES[0], ITEM_RES[1]);

            if(slot == 1)       item1 = newItem;
            else if(slot == 2)  item2 = newItem;
            else if(slot == 3)  item3 = newItem;
            else if(slot == 4)  item4 = newItem;
            else                return false;

            return true;
        } catch(Exception e) {
            if(c.DEBUG_ENABLED)
                e.printStackTrace();
            return false;
        }
    }

    private BufferedImage scale(BufferedImage bi, int[] res) {
        try {
            //resize
            bi = Scalr.resize(bi, res[0], res[1]);

            //pad
            Graphics2D g = (Graphics2D)bi.getGraphics();
            g.setColor(new Color(0, 0, 0, 0));
            g.fillRect(0, 0, bi.getWidth() + 2 * res[0], bi.getHeight() + 2 * res[1]);
            g.drawImage(bi, res[0], res[1], null);
            g.dispose();
        } catch(Exception e) {
            if(c.DEBUG_ENABLED)
                e.printStackTrace();
        }
        return bi;
    }

    public void drawText(Player p) {
        bg = copy(defbg);
        String avgTime = "" + (p.getAvgTime() / 60) + " MINS";
        String stats = ("RECENT STATISTICS\n\n\n\n" +
                        "NAME: " + p.getName() + "\n\n" +
                        "RECENTLY PLAYED: " + p.numRecent() + " GAMES\n\n" +
                        "AVG. MATCH LENGTH: " + avgTime + "\n\n" +
                        "AVG. RANK (SOLO): " + p.getAvgRanking());
        Graphics2D g = (Graphics2D)bg.getGraphics();
        g.setColor(new Color(255, 255, 255, 191));
        g.setFont(new Font("monospaced", Font.BOLD, 66));
        FontMetrics fontMetrics = g.getFontMetrics();
        int y = 400;
        for(String s: stats.split("\n")) {
            g.drawString(s, BG_RES[0] - fontMetrics.stringWidth(s) - 20, y);
            y += fontMetrics.getHeight();
        }
        g.dispose();
    }

    private BufferedImage blit(BufferedImage bg, BufferedImage overlay, int[] pos) {
        try {
            Graphics2D g = (Graphics2D)bg.getGraphics();
            g.drawImage(overlay, pos[0], pos[1], null);
            g.dispose();
        } catch(Exception e) {
            if(c.DEBUG_ENABLED)
                e.printStackTrace();
        }
        return bg;
    }

    private BufferedImage copy(BufferedImage img) {
        BufferedImage b = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        Graphics2D g = (Graphics2D)b.getGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();
        return b;
    }

    public BufferedImage getRender() {
        return render;
    }

    public int[] getRenderResolution() {
        return RENDER_RES;
    }

    public void render() {
        try {
            BufferedImage preRender = copy(bg);
            preRender = blit(preRender, itemBg, new int[]{20, 350});
            preRender = blit(preRender, item1, new int[]{20, 350});
            preRender = blit(preRender, itemBg, new int[]{552, 494});
            preRender = blit(preRender, item2, new int[]{552, 494});
            preRender = blit(preRender, itemBg, new int[]{20, 882});
            preRender = blit(preRender, item3, new int[]{20, 882});
            preRender = blit(preRender, itemBg, new int[]{552, 1026});
            preRender = blit(preRender, item4, new int[]{552, 1026});
            render = scale(preRender, RENDER_RES);
        } catch(Exception e) {
            if(c.DEBUG_ENABLED)
                e.printStackTrace();
        }

    }

}
