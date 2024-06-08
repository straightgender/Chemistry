package Tester;


import org.openqa.selenium.WebDriver;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class GUI extends JFrame {
    static WebDriver driver;
    protected GUI(){
        super("Compound Viewer");
        setup();
        setSize(1250, 672);
        setVisible(true);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> driver.quit()));
        add();
    }

    public static void setup(){
        driver = Viewer.setup();
    }

    private void add(){
        JLabel threeD = new JLabel();
        threeD.setBounds(620, 110, 610, 550);
        JButton searchButton = new JButton();
        searchButton.setIcon(new ImageIcon("src/main/java/images/search.png"));
        searchButton.setBounds(1010, 10, 100, 100);
        JTextArea waste = new JTextArea();
        waste.setBounds(1099, 0, 1, 1);
        waste.requestFocusInWindow();
        SwingUtilities.invokeLater(waste::requestFocus);
        JLabel twoD = new JLabel();
        twoD.setBounds(5, 110, 610, 550);
        JTextField searchBar = new JTextField();
        searchBar.setBounds(5, 10, 1000, 100);
        searchBar.setFont(new Font("Dialog", Font.BOLD, 40));
        searchBar.setText("Enter Compound Name: ");
        searchBar.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchBar.getText().equals("Enter Compound Name: ")){
                    searchBar.setText("");
                }
                searchBar.setCaretPosition(searchBar.getText().length());
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (searchBar.getText().isEmpty()){
                    searchBar.setText("Enter Compound Name: ");
                }
            }
        });
        searchButton.addActionListener(e -> {
            ImageIcon[] structures = Viewer.view(searchBar.getText(), driver);
            twoD.setIcon(structures[0]);
            threeD.setIcon(structures[1]);
        });
        add(threeD);
        add(searchButton);
        add(waste);
        add(twoD);
        add(searchBar);
    }
}