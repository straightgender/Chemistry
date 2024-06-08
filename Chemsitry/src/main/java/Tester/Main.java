package Tester;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Opening a chrome tab, don't freak out, just hover it in your taskbar");
        System.out.println("Refer to the Compound Names text file for some complex structures");
        SwingUtilities.invokeLater(GUI::new);
    }
}
