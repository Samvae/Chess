package com.chess.pieces;

import com.chess.board.Board;

public class Bishop extends Piece {

	public Bishop(int x, int y, boolean isWhite, Board board, int value) {
		super(x, y, isWhite, board, value);
		this.pieceImage = PieceImages.BISHOP;
	}

	@Override
	public void intializeSide(int value) {
		super.intializeSide(value);
		if (isWhite()) {
			image = PieceImages.wb;
		} else {
			image = PieceImages.bb;
		}
	}

	@Override
	public boolean canMove(int x, int y, Board board) {
		if (board.getPiece(x, y) != null && board.getPiece(x, y).isWhite() == isWhite()) {
			return false;
		}

		if (Math.abs(x - xCord) != Math.abs(y - yCord)) {
			return false;
		}

		return bishopMoves(x, y, board);
	}

	private boolean bishopMoves(int x, int y, Board board) {
		if (x > xCord && y > yCord) {
			return checkMoves(board, xCord + 1, yCord + 1, x, y, 1, 1);
		} else if (x < xCord && y < yCord) {
			return checkMoves(board, xCord - 1, yCord - 1, x, y, -1, -1);
		} else if (x > xCord && y < yCord) {
			return checkMoves(board, xCord + 1, yCord - 1, x, y, 1, -1);
		} else if (x < xCord && y > yCord) {
			return checkMoves(board, xCord - 1, yCord + 1, x, y, -1, 1);
		}
		return false;
	}

	private boolean checkMoves(Board board, int i, int j, int x, int y, int xIncrement, int yIncrement) {
		while (i != x && j != y) {
			if (board.getPiece(i, j) != null) {
				return false;
			}
			i += xIncrement;
			j += yIncrement;
		}
		return true;
	}

}
