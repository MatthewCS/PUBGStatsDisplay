import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TextPanel extends JPanel implements ActionListener {

    private JPanel panel;
    private JLabel label;
    private JTextField usernameField;
    private JButton confirmButton;
    private JButton saveButton;
    private String input;
    private static final Constants c = new Constants();

    public TextPanel() {
        label = new JLabel("Player statistics");
        usernameField = new JTextField(30);
        confirmButton = new JButton("Generate");
        panel = new JPanel();
        saveButton = new JButton("Save as...");
        input = "";
        usernameField.setText("Username");
        confirmButton.addActionListener(this);
        panel.setLayout(new GridLayout(0, 1, c.GAP, c.GAP));
        panel.setBorder(new EmptyBorder(c.GAP, c.GAP, c.GAP, c.GAP));
        panel.add(label);
        panel.add(usernameField);
        panel.add(confirmButton);
        panel.add(saveButton);
    }

    public JPanel getPanel() {
        return panel;
    }

    public String getInput() {
        String copyInput = "" + input;
        input = "";
        return copyInput;
    }

    public void setSaveListener(ActionListener al) {
        saveButton.addActionListener(al);
    }

    public void actionPerformed(ActionEvent e) {
        if(c.DEBUG_ENABLED)
            input = usernameField.getText();
    }

}
