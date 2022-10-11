public class Bishop extends ChessPiece {
    public Bishop(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (isPossibleMove(chessBoard, line, column, toLine, toColumn)) {
            if (!chessBoard.board[line][column].equals(this)) {
                return false;
            }

            if ((column == Math.min(column, toColumn) && line == Math.max(line, toLine)) ||
                    (toColumn == Math.min(column, toColumn) && toLine == Math.max(line, toLine))) {
                return from70to07(chessBoard, line, column, toLine, toColumn);
            } else {
                return from07to70(chessBoard, line, column, toLine, toColumn);
            }
        } else return false;
    }

    @Override
    public String getSymbol() {
        return "B";
    }

    @Override
    public boolean isPositionOnBoard(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        return chessBoard.checkPos(line) && chessBoard.checkPos(column) && chessBoard.checkPos(toLine) && chessBoard.checkPos(toColumn);
    }

    public boolean isPossibleMove(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        return line != toLine && column != toColumn &&
                Math.max(line, toLine) - Math.min(line, toLine) == Math.max(column, toColumn) - Math.min(column, toColumn) &&
                isPositionOnBoard(chessBoard, line, column, toLine, toColumn) &&
                (chessBoard.board[toLine][toColumn] == null || !chessBoard.board[toLine][toColumn].color.equals(this.color)) &&
                chessBoard.board[line][column] != null;
    }

    public boolean from70to07(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        int initLine = Math.max(line, toLine);
        int initColumn = Math.min(column, toColumn);
        int endColumn = Math.max(column, toColumn);
        int[][] positions = new int[endColumn - initColumn][1];
        for (int i = 1; i < endColumn - initColumn; i++) {
            if (chessBoard.board[initLine - i][initColumn + i] == null) {
                positions[i - 1] = new int[]{initLine - i, initColumn + i};
            } else if (!chessBoard.board[initLine - i][initColumn + i].color.equals(this.color) && initLine - i == toLine) {
                positions[i - 1] = new int[]{initLine - i, initColumn + i};
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean from07to70(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        int initLine = Math.min(line, toLine);
        int initColumn = Math.min(column, toColumn);
        int endColumn = Math.max(column, toColumn);
        int[][] positions = new int[endColumn - initColumn][1];
        for (int i = 1; i < endColumn - initColumn; i++) {
            if (chessBoard.board[initLine + i][initColumn + i] == null) {
                positions[i - 1] = new int[]{initLine + i, initColumn + i};
            } else if (!chessBoard.board[initLine + i][initColumn + i].color.equals(this.color) && initLine + i == toLine) {
                positions[i - 1] = new int[]{initLine + i, initColumn + i};
            } else {
                return false;
            }
        }
        return true;
    }
}