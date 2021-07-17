package guiteste;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIteste implements ActionListener {
    JFrame frame;
    JButton button;
    JButton resetButton;
    JPanel panel;
    JLabel label;

    int count = 0;

    public GUIteste() {

        frame = new JFrame();

        button = new JButton("Click Me");
        button.addActionListener(this);
        button.setActionCommand("add");

        resetButton = new JButton("Reset");
        resetButton.addActionListener(this);
        resetButton.setActionCommand("reset");

        label = new JLabel("Clicks: 0");

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 20, 50));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(button);
        panel.add(resetButton);
        panel.add(label);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("GUI");
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new GUIteste();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ("add".equals(command)) {
            count++;
            label.setText("Clicks: " + count);
        }
        if ("reset".equals(command)) {
            count = 0;
            label.setText("Clicks: 0");
        }
    }
}
