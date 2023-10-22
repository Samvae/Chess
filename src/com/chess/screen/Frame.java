package com.chess.screen;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Dimension;

public class Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 400;
	public static final int HEIGHT = 440;

	public Frame() {
		this.setContentPane(new Panel());
		this.getContentPane().setBackground(Color.WHITE);
		this.setTitle("Chess");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
