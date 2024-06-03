import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import javax.swing.*;

public class Menu {

    private static JTextArea textArea;
    private static JFrame frame;

    public static void main(String[] args) {
        frame = new JFrame("Menu Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1500);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        menuBar.add(menu);

        JMenuItem menuItem1 = new JMenuItem("Show Date and Time");
        JMenuItem menuItem2 = new JMenuItem("Save to File");
        JMenuItem menuItem3 = new JMenuItem("Change Background Color");
        JMenuItem menuItem4 = new JMenuItem("Exit");

        menu.add(menuItem1);
        menu.add(menuItem2);
        menu.add(menuItem3);
        menu.add(menuItem4);

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.setJMenuBar(menuBar);

        menuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDateTime();
            }
        });

        menuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveToFile();
            }
        });

        menuItem3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeBackgroundColor();
            }
        });

        menuItem4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        frame.setVisible(true);
    }

    private static void showDateTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        textArea.setText(dtf.format(now));
    }

    private static void saveToFile() {
        try (FileWriter writer = new FileWriter("log.txt")) {
            writer.write(textArea.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void changeBackgroundColor() {
        Random rand = new Random();
        float hue = rand.nextFloat(); // Random hue for any color
        float saturation = 0.5f + rand.nextFloat() * 0.5f; // Saturation between 0.5 and 1.0
        float brightness = 0.7f + rand.nextFloat() * 0.3f; // Brightness between 0.7 and 1.0
        Color randomColor = Color.getHSBColor(hue, saturation, brightness);
        textArea.setBackground(randomColor);
    }
}
