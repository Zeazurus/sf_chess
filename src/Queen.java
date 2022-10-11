public class Queen extends ChessPiece {
    public Queen(String color) {
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
        } else if (isPositionOnBoard(chessBoard, line, column, toLine, toColumn)) {
            if (column == toColumn) {
                return doColumnMove(chessBoard, line, column, toLine);
            } else if (line == toLine) {
                return doLineMove(chessBoard, line, column, toLine, toColumn);
            } else return false;
        } else return false;
    }

    @Override
    public String getSymbol() {
        return "Q";
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

    public boolean doColumnMove(ChessBoard chessBoard, int line, int column, int toLine) {
        for (int i = Math.min(line, toLine); i < Math.max(line, toLine); i++) {
            if (chessBoard.board[i][column] != null) {
                if (chessBoard.board[i][column].getColor().equals(this.color) && i == toLine)
                    return false;
                else if (!chessBoard.board[i][column].getColor().equals(this.color) && i == toLine)
                    return true;
                else if (i != toLine && i != line) return false;
            }
        }

        if (chessBoard.board[toLine][column] != null) {
            if (chessBoard.board[toLine][column].getColor().equals(this.color) && chessBoard.board[toLine][column] != this)
                return false;
            else
                return !chessBoard.board[toLine][column].getColor().equals(this.color) && chessBoard.board[toLine][column] != this;
        } else return true;
    }

    public boolean doLineMove(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        for (int i = Math.min(toColumn, column); i < Math.max(column, toColumn); i++) {
            if (chessBoard.board[line][i] != null) {
                if (chessBoard.board[line][i].getColor().equals(this.color) && i == toColumn)
                    return false;
                else if (!chessBoard.board[line][i].getColor().equals(this.color) && i == toColumn)
                    return true;
                else if (i != toLine && i != column) return false;
            }
        }

        if (chessBoard.board[toLine][toColumn] != null) {
            if (chessBoard.board[toLine][toColumn].getColor().equals(this.color) && chessBoard.board[toLine][toColumn] != this)
                return false;
            else
                return !chessBoard.board[toLine][toColumn].getColor().equals(this.color) && chessBoard.board[toLine][toColumn] != this;
        } else return true;
    }
}