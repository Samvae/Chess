package com.chess.pieces;

import com.chess.board.Board;
import com.chess.board.Move;
import com.chess.gameplay.Game;

public class Pawn extends Piece {
	private boolean firstMove; // flag to check if the pawn has moved before
	private boolean moved2Squares = false; // flag to check if the pawn has moved 2 squares forward in the previous turn

	public Pawn(int x, int y, boolean isWhite, Board board, int value) {
		super(x, y, isWhite, board, value);
		firstMove = true; // set the firstMove flag to true since the pawn has not moved yet
		this.pieceImage = PieceImages.PAWN;
	}

	// Initialize the pawn's side and set the image based on whether the pawn is
	// white or black
	public void intializeSide(int value) {
		super.intializeSide(value);
		if (isWhite()) {
			image = PieceImages.wp;
		} else {
			image = PieceImages.bp;
		}
	}

	public boolean makeMove(int toX, int toY, Board board) {

		// Create a new move object
		Move move = new Move(xCord, yCord, toX, toY, this);

		// Check if the piece is alive
		if (!alive()) {
			return false;
		}

		// Check if the move is valid
		if (moves.contains(move)) {

			// Check if the move is a capture by en passant
			if (toX == xCord + 1 && yCord - (isWhite ? 1 : -1) == toY) {
				if (board.getXY(toX, toY) == 0) {
					// Remove the captured pawn from the list of pieces and the board
					Game.AllPieces.remove(board.getPiece(xCord + 1, yCord));
					Game.fillPieces();
					board.setXY(xCord + 1, yCord, 0);
					board.setPieceIntoBoard(xCord + 1, yCord, null);
				}
			}

			// Check if the move is a capture by en passant
			if (toX == xCord - 1 && yCord - (isWhite ? 1 : -1) == toY) {
				if (board.getXY(toX, toY) == 0) {
					// Remove the captured pawn from the list of pieces and the board
					Game.AllPieces.remove(board.getPiece(xCord - 1, yCord));
					Game.fillPieces();
					board.setXY(xCord - 1, yCord, 0);
					board.setPieceIntoBoard(xCord - 1, yCord, null);
				}
			}

			// Check if the move is a double pawn push
			if (firstMove && Math.abs((yCord - toY)) == 2) {
				moved2Squares = true;
			}

			// Remove any en passant targets for the opponent
			removeEnpassant();

			// Update the board and the piece's position
			board.updatePieces(xCord, yCord, toX, toY, this);
			xCord = toX;
			yCord = toY;

			// Set the firstMove flag to false
			firstMove = false;

			// Return true to indicate that the move was successful
			return true;
		}

		// Return false to indicate that the move was invalid
		return false;
	}

	// remove the possibility of en passant for all pawns except for the current
	// pawn
	private void removeEnpassant() {
		for (Piece p : Game.AllPieces) {
			// check if the current piece is a pawn and not the current pawn
			if (p instanceof Pawn && p != this) {
				// cast the current piece to a pawn and set its "moved2Squares" attribute to
				// false
				((Pawn) p).setMoved2Squares(false);
			}
		}
	}

	// check if the pawn has reached the opposite end of the board
	public boolean madeToTheEnd() {
		if (isWhite && yCord == 0) {
			return true;
		}
		if (!isWhite && yCord == 7) {
			return true;
		}
		return false;
	}

	public boolean canMove(int x, int y, Board board) {
		// Initialize enpassant variable
		int enpassant = 0;

		// Set enpassant to -1 if the pawn is white, otherwise set it to 1
		if (isWhite) {
			enpassant = -1;
		} else {
			enpassant = 1;
		}

		// Check if there is a pawn that can be captured via en passant to the left or
		// right
		if (xCord > 0 && xCord < 7) {
			if (board.getXY(xCord + 1, yCord) == enpassant) {
				// Get the left pawn that can be captured via en passant
				Pawn leftPawn = (Pawn) board.getPiece(xCord + 1, yCord);
				// Check if the pawn can capture the left pawn via en passant
				if (x == leftPawn.getXcord() && y == leftPawn.getYcord() + enpassant && leftPawn.isMoved2Squares()) {
					return true;
				}
			}

			if (board.getXY(xCord - 1, yCord) == enpassant) {
				// Get the right pawn that can be captured via en passant
				Pawn rightPawn = (Pawn) board.getPiece(xCord - 1, yCord);
				// Check if the pawn can capture the right pawn via en passant
				if (x == rightPawn.getXcord() && y == rightPawn.getYcord() + enpassant && rightPawn.isMoved2Squares()) {
					return true;
				}
			}
		}

		// Check if there is a piece blocking the pawn's way
		if ((board.getPiece(x, y) != null && board.getPiece(x, y).isWhite() == isWhite())) {
			return false;
		}

		// Check if the pawn is moving diagonally without capturing a piece
		if (xCord != x && board.getPiece(x, y) == null) {
			return false;
		}

		if (isWhite) {
			// Check if the pawn is making its first move and can move 1 or 2 squares
			// forward
			if (firstMove) {
				if (x == xCord && (y == yCord - 1 || y == yCord - 2) && board.getPiece(x, y) == null
						&& board.getPiece(x, y + 1) == null) {
					return true;
				}
			}
			// Check if the pawn can move 1 square forward
			if (x == xCord && y == yCord - 1 && board.getPiece(x, y) == null) {
				return true;
			}

			// Check if the pawn can capture a piece
			return capture(x, y, board);

		}
		// Check if the piece is black
		if (!isWhite) {

			// Check if this is the first move of the pawn
			if (firstMove) {

				// Check if the pawn can move 2 squares forward and there are no pieces in the
				// way
				if (x == xCord && (y == yCord + 1 || y == yCord + 2) && board.getPiece(x, y) == null
						&& board.getPiece(x, y - 1) == null) {
					return true;
				}
			}

			// Check if the pawn can move one square forward and there are no pieces in the
			// way
			if (x == xCord && y == yCord + 1 && board.getPiece(x, y) == null) {
				return true;
			}

			// Otherwise, try to capture a piece at the specified position
			return capture(x, y, board);
		}

		// If the piece is not black, return false
		return false;
	}

	public boolean capture(int x, int y, Board board) {
		if (isWhite()) {
			// white pawn capture conditions
			// if the pawn is moving diagonally one square to the right or left and captures
			// an opponent's piece, return true
			if (y == yCord - 1 && x == xCord + 1) {
				return true;
			}
			if (y == yCord - 1 && x == xCord - 1) {
				return true;
			}
		} else {
			// black pawn capture conditions
			// if the pawn is moving diagonally one square to the right or left and captures
			// an opponent's piece, return true
			if (y == yCord + 1 && x == xCord + 1) {
				return true;
			}
			if (y == yCord + 1 && x == xCord - 1) {
				return true;
			}
		}
		return false;
	}

	// removes the captured en passant pawn from the board
	public void removeEnpassanCapturedpiece(int x, int y) {

	}

	public boolean isFirstMove() {
		return firstMove;
	}

	public void setFirstMove(boolean firstMove) {
		// sets the first move flag for the pawn
		this.firstMove = firstMove;
	}

	public boolean isMoved2Squares() {
		return moved2Squares;
	}

	public void setMoved2Squares(boolean moved2Squares) {
		// sets the moved 2 squares flag for the pawn
		this.moved2Squares = moved2Squares;
	}
}
