import javax.swing.*;
import java.awt.*;

public class AddUserView {
    private JTextArea nameContent;
    private JPanel viewPanel;
    private JTextArea emailContent;
    private JTextArea passwordContent;
    private JButton createUserButton;

    public AddUserView(String title) {
        JFrame frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(500, 700));
        frame.setContentPane(this.getViewPanel());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public String getNameContent() {
        return nameContent.getText();
    }

    public String getEmailContent() {
        return emailContent.getText();
    }

    public String getPasswordContent() {
        return passwordContent.getText();
    }

    public JButton getCreateUserButton() {
        return createUserButton;
    }

    public JPanel getViewPanel() {
        return viewPanel;
    }
}
