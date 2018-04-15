import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.imageio.ImageIO;
import java.io.File;

public class MainApp {
	private static TextPanel tp;
	private static ImportPanel ip;
	private static ImagePanel img;
	private static final Constants c = new Constants();

	public static void main(String [] args) {
		JFrame frame = new JFrame("PUBG Stats Display");
		tp = new TextPanel();
		ip = new ImportPanel();
		img = new ImagePanel(new File("bg.png"), new File("itembg.png"));

		tp.setSaveListener(new SaveFileListener());

		frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(625, 675));
		frame.setResizable(false);
		frame.add(img.getPanel(), BorderLayout.CENTER);
		frame.add(ip.getPanel(), BorderLayout.EAST);
		frame.add(tp.getPanel(), BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
		while(true) {
			int destination = ip.getDestination();
			String username = tp.getInput();
			if(destination != -1) {
				img.updateItem(ip.getFile(), destination);
				destination = -1;
			}
			if(! username.equals("")) {
				try {
					img.drawText(new Player(username));
				} catch(Exception e) {
					JOptionPane.showMessageDialog(null, "Error:\n" + e.toString(),
												  "Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
			img.updateImage();
            try {
                Thread.sleep(5);
            } catch(Exception e) {
                if(c.DEBUG_ENABLED)
                    e.printStackTrace();
            }
        }
	}

	/*private static class ProfileListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			try {
				tp.getText();
			} catch(Exception e) {
				if(c.DEBUG_ENABLED)
					e.printStackTrace();
			}
			img.updateImage();
		}
	}*/

	private static class SaveFileListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			try {
				JFileChooser jfc = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG images", "png");
				jfc.setFileFilter(filter);
		        jfc.setCurrentDirectory(new File("."));
		        jfc.setDialogTitle("Save as...");
				jfc.setApproveButtonText("Save");
				int returnVal = jfc.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
        			File output = jfc.getSelectedFile();
					ImageIO.write(img.getImage(), "png", output);
      			}
			} catch(Exception e) {
				if(c.DEBUG_ENABLED)
					e.printStackTrace();
			}
			img.updateImage();
		}
	}

}
