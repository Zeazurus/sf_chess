public class Horse extends ChessPiece {
    public Horse(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (isPositionOnBoard(chessBoard, line, column, toLine, toColumn)) {
            if (isPossibleMove(chessBoard, line, column, toLine, toColumn)) {
                if (!chessBoard.board[line][column].equals(this)) {
                    return false;
                }

                int[][] positions = new int[][]{
                        {line - 2, column - 1}, {line - 2, column + 1},
                        {line + 2, column - 1}, {line + 2, column + 1},
                        {line - 1, column - 2}, {line - 1, column + 2},
                        {line + 1, column - 2}, {line + 1, column + 2}};

                for (int[] position : positions) {
                    if (position[0] == toLine && position[1] == toColumn) return true;
                }
            }
        } else return false;
        return false;
    }

    @Override
    public String getSymbol() {
        return "H";
    }

    @Override
    public boolean isPositionOnBoard(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        return chessBoard.checkPos(line) && chessBoard.checkPos(column) && chessBoard.checkPos(toLine) && chessBoard.checkPos(toColumn);
    }

    public boolean isPossibleMove(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        return line != toLine && column != toColumn && (chessBoard.board[toLine][toColumn] == null ||
                !chessBoard.board[toLine][toColumn].color.equals(this.color)) &&
                chessBoard.board[line][column] != null;
    }
}