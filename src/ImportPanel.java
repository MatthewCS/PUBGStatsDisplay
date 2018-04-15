import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.border.EmptyBorder;
import java.io.File;

public class ImportPanel extends JPanel implements ActionListener {

    private JPanel panel;
    private JLabel label;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private File file;
    private int num;
    private static final Constants c = new Constants();

    public ImportPanel() {
        label = new JLabel("Import");
        button1 = new JButton("Item 1");
        button1.addActionListener(this);
        button2 = new JButton("Item 2");
        button2.addActionListener(this);
        button3 = new JButton("Item 3");
        button3.addActionListener(this);
        button4 = new JButton("Item 4");
        button4.addActionListener(this);
        panel = new JPanel();
        file = null;
        num = -1;
        panel.setLayout(new GridLayout(0, 1, c.GAP, c.GAP));
        panel.setBorder(new EmptyBorder(c.GAP, c.GAP, c.GAP, c.GAP));
        panel.add(label);
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
    }

    public JPanel getPanel() {
        return panel;
    }

    public File getFile() {
        return file;
    }

    public int getDestination() {
        int newNum = num;
        num = -1;
        return newNum;
    }

    public void actionPerformed(ActionEvent e) {
        int destination = 1;
        JFileChooser jfc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG images", "png");
        Object source = e.getSource();
        if(source.equals(button1)) {
            destination = 1;
        }
        else if(source.equals(button2)) {
            destination = 2;
        }
        else if(source.equals(button3)) {
            destination = 3;
        }
        else if(source.equals(button4)) {
            destination = 4;
        }

        jfc.setFileFilter(filter);
        jfc.setCurrentDirectory(new File("."));
        jfc.setDialogTitle("Select image...");
        int returnVal = jfc.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            if(c.DEBUG_ENABLED)
                System.out.println(jfc.getSelectedFile().getName());
            file = jfc.getSelectedFile();
            num = destination;
        }
    }

}
