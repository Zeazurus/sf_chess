public class King extends ChessPiece {
    public King(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (isPositionOnBoard(chessBoard, line, column, toLine, toColumn)) {
            if (Math.abs(line - toLine) > 1 || Math.abs(column - toColumn) > 1) return false;

            if (isUnderAttack(chessBoard, toLine, toColumn)) return false;

            if (chessBoard.board[toLine][toColumn] != null) {
                return !chessBoard.board[toLine][toColumn].getColor().equals(color);
            }

            return true;
        } else return false;
    }

    @Override
    public String getSymbol() {
        return "K";
    }

    public boolean isUnderAttack(ChessBoard board, int line, int column) {
        if (board.checkPos(line) && board.checkPos(column)) {
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 7; j++) {
                    if (board.board[i][j] != null) {
                        if (!board.board[i][j].getColor().equals(color) && board.board[i][j].canMoveToPosition(board, i, j, line, column)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        } else return false;
    }

    @Override
    public boolean isPositionOnBoard(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        return chessBoard.checkPos(line) && chessBoard.checkPos(column) && chessBoard.checkPos(toLine) && chessBoard.checkPos(toColumn);
    }
}