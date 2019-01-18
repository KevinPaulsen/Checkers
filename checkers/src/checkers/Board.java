package checkers;

import java.awt.*;
import java.util.ArrayList;

public class Board {

    private int[][] board = new int[8][8];
    private ArrayList<Point> availableMoves = new ArrayList<>();

    Board() {}


    public void setUpBoard() {
        for (int idx = 1; idx < 24; idx += 2) {
            board[idx / 8][idx % 8] = 1;
            if (idx % 8 == 7) {
                idx--;
            } else if (idx % 8 == 6) {
                idx++;
            }
        }
        for (int idx = 40; idx < 64; idx += 2) {
            board[idx / 8][idx % 8] = 2;
            if (idx % 8 == 7) {
                idx--;
            } else if (idx % 8 == 6) {
                idx++;
            }
        }

    }


    public void drawPlayerPieces(Graphics g) {
        board[5][1] = 1;
        board[5][3] = 2;
        g.setColor(Color.white);
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                if (board[row][column] == 1) {
                    g.fillOval(column * 75 + 10, row * 75 + 10 + 22, 55, 55);
                } else if (board[row][column] == 2){
                    g.drawOval(column * 75 + 10, row * 75 + 10 + 22, 55, 55);
                }
            }
        }
    }

    public void drawBoard(Graphics g) {
        g.setColor(Color.black);
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                switchColor(g);
                g.fillRect(column * 75, row * 75 + 22, 75, 75);
            }
            switchColor(g);
        }
    }

    public void showAvailableMoves(Graphics g) {
        g.setColor(Color.cyan);
        for (Point tile : availableMoves) {
            g.fillOval(tile.x * 75 + 10, tile.y * 75 + 10 + 22, 55, 55);
        }
    }

    boolean hasAvailableMove(Point cord, int playerTurn) {
        int direction = 1;
        int opponent = 1;
        boolean hasAvailableMove = false;
        availableMoves.clear();
        if (playerTurn == 2) {
            direction = -1;
        }
        if (opponent == playerTurn) {
            opponent = 2;
        }
        boolean hasFreeSpaceRight = (cord.x + 1 <= 7) && (board[cord.y + direction][cord.x + 1] == 0);
        boolean hasFreeSpaceLeft = (cord.x - 1 >= 0) && (board[cord.y + direction][cord.x - 1] == 0);
        boolean canJumpLeft = (cord.x - 1 >= 0) && (board[cord.y + direction][cord.x - 1] == opponent) &&
                (board[cord.y + (direction * 2)][cord.x - 2] == 0);
        boolean canJumpRight = (cord.x + 1 <= 7) && (board[cord.y + direction][cord.x + 1] == opponent) &&
                (board[cord.y + (direction * 2)][cord.x + 2] == 0);

        if (hasFreeSpaceRight) {
            availableMoves.add(new Point(cord.x + 1, cord.y + direction));
        }
        if (hasFreeSpaceLeft) {
            availableMoves.add(new Point(cord.x - 1, cord.y + direction));
        }
        if (canJumpLeft) {
            availableMoves.add(new Point(cord.x - 2, cord.y + (2 * direction)));
        }
        if (canJumpRight) {
            availableMoves.add(new Point(cord.x + 2, cord.y + (2 * direction)));
        }

        if (!availableMoves.isEmpty()) {
            hasAvailableMove = true;
        }

        return hasAvailableMove;
    }

    public int getPlayerFromCord(Point cord) {
        return board[cord.y][cord.x];
    }

    private Graphics switchColor(Graphics g) {
        if (g.getColor().equals(Color.black)) {
            g.setColor(Color.white);
        } else {
            g.setColor(Color.black);
        }
        return g;
    }
}
