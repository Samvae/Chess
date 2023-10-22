package com.chess;

import javax.swing.SwingUtilities;

import com.chess.screen.Frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Main extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
	private static final int WINDOW_SIZE = 500;
    private static final int BUTTON_SIZE = 25;
    private JLabel titleLabel;
    private JButton startButton;
    private JButton exitButton;

    public Main() {
        super("Chess");

        // Set window size
        setSize(WINDOW_SIZE, WINDOW_SIZE);
        setResizable(false);

        // Create components
        titleLabel = new JLabel("Chess", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 75));
        titleLabel.setForeground(new Color(167, 124, 55));

        startButton = createButton("Play");
        exitButton = createButton("Exit");

        // Create button panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        buttonPanel.setBackground(new Color(238, 238, 210)); // add background color
        buttonPanel.add(startButton);
        buttonPanel.add(exitButton);

        // Create main panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(238, 238, 210)); // add background color
        mainPanel.add(titleLabel, createConstraints(0, 0, 1.0, 1.0, new Insets(50, 50, 50, 50)));
        mainPanel.add(buttonPanel, createConstraints(0, 1, 1.0, 2.0, new Insets(0, 0, 0, 0)));

        // Add main panel to content pane
        getContentPane().add(mainPanel, BorderLayout.CENTER);

        // Set window properties
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(WINDOW_SIZE / 2, BUTTON_SIZE));
        button.addActionListener(this);
        button.setVerticalTextPosition(SwingConstants.BOTTOM); // align text and icon
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setForeground(Color.WHITE); // change text color
        button.setBackground(new Color(211, 175, 55)); // change background color
        button.setFocusPainted(false); 
        button.setBorderPainted(false); 
        button.addMouseListener(new java.awt.event.MouseAdapter() { // add hover effect
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.GRAY);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(211, 175, 55));
            }
        });
        return button;
    }

    private GridBagConstraints createConstraints(int x, int y, double weightx, double weighty, Insets insets) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
        gbc.insets = insets;
        return gbc;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {     
        	try {
		        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\villahermosas3\\git\\p01-individual-project-Samvae\\Sound\\mouse click.wav").getAbsoluteFile());
		        Clip clip = AudioSystem.getClip();
		        clip.open(audioInputStream);
		        clip.start();
		    } catch (Exception ex) {
		        System.out.println("Error playing sound effect: " + ex.getMessage());
		    }
                SwingUtilities.invokeLater(() -> new Frame());
                dispose();
        } else if (e.getSource() == exitButton) {
        	try {
		        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\villahermosas3\\git\\p01-individual-project-Samvae\\Sound\\mouse click.wav").getAbsoluteFile());
		        Clip clip = AudioSystem.getClip();
		        clip.open(audioInputStream);
		        clip.start();
		    } catch (Exception ex) {
		        System.out.println("Error playing sound effect: " + ex.getMessage());
		    }
            dispose();
        }
    }

    public static void main(String[] args) {
        new Main();
    }

}