public class Pawn extends ChessPiece {
    public Pawn(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (isPositionOnBoard(chessBoard, line, column, toLine, toColumn) && chessBoard.board[line][column] != null) {
            if (column == toColumn) {
                int direction;
                int initialPosition;

                if (color.equals("White")) {
                    direction = 1;
                    initialPosition = 1;
                } else {
                    direction = -1;
                    initialPosition = 6;
                }

                if (line + direction == toLine) {
                    return chessBoard.board[toLine][toColumn] == null;
                }

                if (line == initialPosition && line + 2 * direction == toLine) {
                    return chessBoard.board[toLine][toColumn] == null && chessBoard.board[line + direction][column] == null;
                }

            } else {
                return doEatPiece(chessBoard, line, column, toLine, toColumn);
            }
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "P";
    }

    @Override
    public boolean isPositionOnBoard(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        return chessBoard.checkPos(line) && chessBoard.checkPos(column) && chessBoard.checkPos(toLine) && chessBoard.checkPos(toColumn);
    }

    public boolean doEatPiece(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if ((column - toColumn == 1 || column - toColumn == -1) && (line - toLine == 1 || line - toLine == -1) &&
                chessBoard.board[toLine][toColumn] != null) {

            return !chessBoard.board[toLine][toColumn].getColor().equals(color);
        } else return false;
    }
}