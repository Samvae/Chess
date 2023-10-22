package com.chess.pieces;

import com.chess.board.Board;
import com.chess.board.Move;
import com.chess.gameplay.Game;

public class King extends Piece {
	private boolean hasMoved;
	private Rook rook = null;

	public King(int x, int y, boolean isWhite, Board board, int value) {
		super(x, y, isWhite, board, value);
		hasMoved = false;
		this.pieceImage = PieceImages.KING;
		initializeSide(value);
	}

	private void initializeSide(int value) {
		if (isWhite()) {
			image = PieceImages.wk;
		} else {
			image = PieceImages.bk;
		}
		super.intializeSide(value);
	}

	public boolean makeMove(int x, int y, Board board) {
		// Check if the piece is alive
		if (!alive()) {
			return false;
		}

		// Check if the move is legal
		Move move = new Move(xCord, yCord, x, y, this);
		if (!moves.contains(move)) {
			return false;
		}

		// Update the board and the piece's coordinates
		getRook(x, board);
		board.updatePieces(xCord, yCord, x, y, this);
		xCord = x;
		yCord = y;

		// Check for castling move
		if (rook != null && !hasMoved && !rook.HasMoved()) {
			if (x == rook.getXcord() - 1 || x == rook.getXcord() + 2) {
				rook.castleDone(xCord, board);
			}
		}

		// Update the piece's status and return true
		hasMoved = true;
		return true;
	}

	@Override
	public boolean canMove(int x, int y, Board board) {

		// Calculate the absolute difference in x and y coordinates
		int i = Math.abs(xCord - x);
		int j = Math.abs(yCord - y);

		// Check if the move is one square diagonally or one square
		// vertically/horizontally
		if (j == 1 && i == 1 || (i + j) == 1) {

			// Check if the destination square is empty or occupied by an opponent's piece
			if (board.getPiece(x, y) == null) {
				return true;
			} else {
				return board.getPiece(x, y).isWhite() != isWhite();
			}
		}

		// Check if the move is a castling move
		getRook(x, board);
		if (rook != null && (rook.HasMoved() || this.hasMoved)) {
			// The castling move cannot be made if the king or rook has moved before
			return false;
		} else if (rook != null) {
			// Check if the path between the king and rook is clear of pieces and enemy
			// attacks
			for (int k = xCord + 1; k < rook.getXcord(); k++) {
				if (board.getPiece(k, yCord) != null) {
					return false;
				}
				for (Move m : Game.allEnemysMove) {
					if ((m.getToX() == k || m.getToX() == xCord) && m.getToY() == yCord) {
						return false;
					}
				}
			}
			if (x == rook.getXcord() - 1 && y == yCord) {
				// The castling move can be made to the left side of the board
				return true;
			}

			for (int k = xCord - 1; k > rook.getXcord(); k--) {
				if (board.getPiece(k, yCord) != null) {
					return false;
				}
				for (Move m : Game.allEnemysMove) {
					if ((m.getToX() == k || m.getToX() == xCord) && m.getToY() == yCord) {
						return false;
					}
				}
			}
			if (x == rook.getXcord() + 2 && y == yCord) {
				// The castling move can be made to the right side of the board
				return true;
			}
		}
		// The move is not valid if it does not satisfy any of the above conditions
		return false;
	}

	// Method to find the rook on the same side of the king as the given x
	// coordinate
	private void getRook(int x, Board board) {
		if (isWhite()) {
			// If x is greater or equal to the king's x coordinate, search for the rook on
			// the right side of the board
			if (x >= xCord) {
				// If there is a piece at (7, 7) and it's a rook, set rook to that piece
				if (board.getPiece(7, 7) != null && board.getPiece(7, 7) instanceof Rook) {
					rook = (Rook) board.getPiece(7, 7);
				}
			}
			// If x is less than the king's x coordinate, search for the rook on the left
			// side of the board
			else {
				// If there is a piece at (0, 7) and it's a rook, set rook to that piece
				if (board.getPiece(0, 7) != null && board.getPiece(0, 7) instanceof Rook) {
					rook = (Rook) board.getPiece(0, 7);
				}
			}
		} else {
			// If x is greater or equal to the king's x coordinate, search for the rook on
			// the right side of the board
			if (x >= xCord) {
				// If there is a piece at (7, 0) and it's a rook, set rook to that piece
				if (board.getPiece(7, 0) != null && board.getPiece(7, 0) instanceof Rook) {
					rook = (Rook) board.getPiece(7, 0);
				}
			}
			// If x is less than the king's x coordinate, search for the rook on the left
			// side of the board
			else {
				// If there is a piece at (0, 0) and it's a rook, set rook to that piece
				if (board.getPiece(0, 0) != null && board.getPiece(0, 0) instanceof Rook) {
					rook = (Rook) board.getPiece(0, 0);
				}
			}
		}
	}

	// Method to check if the king is in check
	public boolean isInCheck() {
		// Check if any of the opponent's moves would capture the king
		for (Move m : Game.allEnemysMove) {
			if (m.getToX() == xCord && m.getToY() == yCord) {
				return true;
			}
		}
		return false;
	}
}
