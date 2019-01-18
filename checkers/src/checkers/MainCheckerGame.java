package checkers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainCheckerGame extends JFrame implements MouseListener {

    private static Board gameBoard;
    private int playerTurn = 2;

    private MainCheckerGame() {
        gameBoard = new Board();

        initialize();
    }

    public static void main(String args[]) {
        new MainCheckerGame();
    }

    private void initialize() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600 + 22);
        setResizable(false);
        setVisible(true);
        addMouseListener(this);

        gameBoard.setUpBoard();
        paint(getGraphics());
    }

    private void move(Point clickedTile) {
        System.out.printf("X: %d Y: %d\n", clickedTile.x, clickedTile.y);
        if (gameBoard.getPlayerFromCord(clickedTile) == playerTurn &&
                gameBoard.hasAvailableMove(clickedTile, playerTurn)) {
            gameBoard.showAvailableMoves(this.getGraphics());
            System.out.println("Move Available");
        } else if (!(gameBoard.getPlayerFromCord(clickedTile) == playerTurn)) {
            System.out.println("Wrong Piece");
        } else if(!gameBoard.hasAvailableMove(clickedTile, playerTurn)) {
            System.out.println("No Move Available");
        } else {
            System.out.println("Something went wrong");
        }

    }

    @Override
    public void paint(Graphics g) {
        gameBoard.drawBoard(g);
        gameBoard.drawPlayerPieces(g);
    }

    private Point getTileFromCord(Point cord) {
       return new Point(cord.x / 75, cord.y / 75);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        paint(this.getGraphics());
        Point clickedTile = getTileFromCord(e.getPoint());
        move(clickedTile);
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
