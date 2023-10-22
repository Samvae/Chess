package com.chess.screen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import com.chess.gameplay.Game;
import com.chess.pieces.Piece;

public class Panel extends JPanel {

	private static final long serialVersionUID = 1L;
	Game game;
	public static int xx, yy;
	JPanel panel = this;
	JButton undoButton;

	public Panel() {
		this.setFocusable(true);
		this.addMouseListener(new Listener());
		this.addMouseMotionListener(new Listener());
		game = new Game();

		// Create the undo button
		undoButton = new JButton("Undo Move");
		undoButton.setPreferredSize(new Dimension(100, 30));
		undoButton.addActionListener(e -> {
			Game.board.undoMove();
			repaint();
		});

		// Create the button panel and add the buttons to it
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(227, 206, 118));
		buttonPanel.add(undoButton);

		// Add the button panel to the content pane
		this.setLayout(new BorderLayout());
		this.add(buttonPanel, BorderLayout.SOUTH);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		game.draw(g, xx, yy, this);
	}

	class Listener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (SwingUtilities.isLeftMouseButton(e)) {
				int x = e.getX() / Piece.size;
				int y = e.getY() / Piece.size;
				Game.drag = false;
				game.active = null;
				game.selectPiece(x, y);
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if (!Game.drag && game.active != null) {
				game.active = null;
			}
			if (SwingUtilities.isLeftMouseButton(e)) {
				game.selectPiece(e.getX() / Piece.size, e.getY() / Piece.size);
				Game.drag = true;
				xx = e.getX();
				yy = e.getY();
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			int x = e.getX() / Piece.size;
			int y = e.getY() / Piece.size;
			game.move(x, y);
			Timer timer = new Timer(1000, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					game.randomPlay();
					repaint();
				}
			});
			timer.setRepeats(false);
			timer.start();
			repaint();
		}
	}

}
