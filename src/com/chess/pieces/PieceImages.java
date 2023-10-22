package com.chess.pieces;

import java.awt.Color;

import javax.swing.ImageIcon;

public class PieceImages {
	static Color WHITECOLOR = Color.WHITE;
	static Color BLACKCOLOR = Color.BLACK;
	static String PAWN = "♟";
	static String ROOK = "♜";
	static String KNIGHT = "♞";
	static String BISHOP = "♝";
	static String QUEEN = "♛";
	static String KING = "♚";

	static ImageIcon wk;
	static ImageIcon bk;
	static ImageIcon wr;
	static ImageIcon br;
	static ImageIcon wq;
	static ImageIcon bq;
	static ImageIcon wb;
	static ImageIcon bb;
	static ImageIcon wn;
	static ImageIcon bn;
	static ImageIcon wp;
	static ImageIcon bp;

	public PieceImages() {
		wk = new ImageIcon(this.getClass().getResource("/white_king.png"));
		bk = new ImageIcon(this.getClass().getResource("/black.king.png"));
		wr = new ImageIcon(this.getClass().getResource("/white_rook.png"));
		br = new ImageIcon(this.getClass().getResource("/black_rook.png"));
		wq = new ImageIcon(this.getClass().getResource("/white_queen.png"));
		bq = new ImageIcon(this.getClass().getResource("/black_queen.png"));
		wb = new ImageIcon(this.getClass().getResource("/white_bishop.png"));
		bb = new ImageIcon(this.getClass().getResource("/black_bishop.png"));
		wn = new ImageIcon(this.getClass().getResource("/white_knight.png"));
		bn = new ImageIcon(this.getClass().getResource("/black_knight.png"));
		wp = new ImageIcon(this.getClass().getResource("/white_pawn.png"));
		bp = new ImageIcon(this.getClass().getResource("/black_pawn.png"));
	}
}
