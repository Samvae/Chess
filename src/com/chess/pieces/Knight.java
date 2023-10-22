package com.chess.pieces;

import com.chess.board.Board;

public class Knight extends Piece {

	// Possible knight moves
	private static final int[][] KNIGHT_MOVES = { { 1, 2 }, { 1, -2 }, { -1, 2 }, { -1, -2 }, { 2, 1 }, { 2, -1 },
			{ -2, 1 }, { -2, -1 } };

	public Knight(int x, int y, boolean isWhite, Board board, int value) {
		super(x, y, isWhite, board, value);
		this.pieceImage = PieceImages.KNIGHT;
	}

	// Set the image of the Knight based on its side
	public void intializeSide(int value) {
		super.intializeSide(value);
		if (isWhite()) {
			image = PieceImages.wn;
		} else {
			image = PieceImages.bn;
		}
	}

	// Check if the Knight can move to a certain position on the board
	public boolean canMove(int toX, int toY, Board board) {

		// Cannot move to a square occupied by a piece of the same side
		if (board.getPiece(toX, toY) != null && board.getPiece(toX, toY).isWhite() == isWhite()) {
			return false;
		}

		// Check all possible moves of the Knight
		for (int[] move : KNIGHT_MOVES) {
			int x = xCord + move[0];
			int y = yCord + move[1];

			// If the destination square matches a possible move, it is a valid move
			if (toX == x && toY == y) {
				return true;
			}
		}

		// If the Knight can't move to the destination square using any of its moves,
		// it's an invalid move
		return false;
	}

}
