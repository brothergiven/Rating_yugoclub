package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

class Frame_MiniGame extends JFrame {
    private static final int DEFAULT_WIDTH = 600;
    private static final int DEFAULT_HEIGHT = 600;

    public Frame_MiniGame() {
        setTitle("GoGame");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setLocationRelativeTo(null);
        ImageComponent component = new ImageComponent();
        getContentPane().add(component);
        setVisible(true);
    }
} 

class Board {
    public static final int SIZE = 20;
    public static final int PIXELS_PER_POINT = 25;

    private int[][] board = new int[SIZE][SIZE];
    private int currentStone = 1;

    class StonePosition {
        int x;
        int y;
        int stone;
    
        public StonePosition(int x, int y, int stone) {
            this.x = x;
            this.y = y;
            this.stone = stone;
        }
    }

    private Stack<StonePosition> stoneHistory = new Stack<>();
    private Stack<StonePosition> redoStack = new Stack<>();

    public void putStone(int x, int y) {
        int i = Math.round((float)x / PIXELS_PER_POINT);
        int j = Math.round((float)y / PIXELS_PER_POINT);
        if (Math.abs(x - (i * PIXELS_PER_POINT + PIXELS_PER_POINT / 2)) <= PIXELS_PER_POINT / 2 &&
                Math.abs(y - (j * PIXELS_PER_POINT + PIXELS_PER_POINT / 2)) <= PIXELS_PER_POINT / 2 &&
                i >= 0 && i < SIZE && j >= 0 && j < SIZE && board[i][j] == 0) {
            board[i][j] = currentStone;

            stoneHistory.push(new StonePosition(i, j, currentStone));

            currentStone = 3 - currentStone;
            redoStack.clear();
        }
    }

    public int getStone(int x, int y) {
        int i = x / PIXELS_PER_POINT;
        int j = y / PIXELS_PER_POINT;

        if (i >= 0 && i < SIZE && j >= 0 && j < SIZE) {
            return board[i][j];
        } else {
            return 0;
        }
    }

    public void undoLastMove() {
        if (!stoneHistory.isEmpty()) {
            StonePosition lastMove = stoneHistory.pop();
            board[lastMove.x][lastMove.y] = 0;
            currentStone = 3 - currentStone;

            // undo한 동작을 redoStack에 추가합니다.
            redoStack.push(lastMove);
        }
    }

    public void redoLastUndo() {
        if (!redoStack.isEmpty()) {
            StonePosition lastUndo = redoStack.pop();
            board[lastUndo.x][lastUndo.y] = lastUndo.stone;
            currentStone = 3 - currentStone;

            // redo한 동작을 stoneHistory에 추가합니다.
            stoneHistory.push(lastUndo);
        }
    }

    public void resetToStart() {
        while (!stoneHistory.isEmpty()) {
            undoLastMove();
        }
    }

    public void resetToEnd() {
        while (!redoStack.isEmpty()) {
            redoLastUndo();
        }
    }
}




class ImageComponent extends JComponent {
    private Image boardImage;
    private Board board;

    public ImageComponent() {
        setLayout(new BorderLayout());

        board = new Board();
        JPanel buttonPanel = new JPanel();

        JButton undoButton = new JButton("뒤로");
        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.undoLastMove();
                repaint();
            }
        });
        buttonPanel.add(undoButton);

        JButton redoButton = new JButton("앞으로");
        redoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.redoLastUndo();
                repaint();
            }
        });
        buttonPanel.add(redoButton);

        JButton resetToStartButton = new JButton("맨 앞으로");
        resetToStartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.resetToStart();
                repaint();
            }
        });
        buttonPanel.add(resetToStartButton);

        JButton resetToEndButton = new JButton("맨 뒤로");
        resetToEndButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.resetToEnd();
                repaint();
            }
        });
        buttonPanel.add(resetToEndButton);

        add(buttonPanel, BorderLayout.SOUTH);
  
        try {
            ImageIcon boardIcon = new ImageIcon(System.getProperty("user.home") + "/Desktop/바둑판.png");
            boardImage = boardIcon.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH);

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    board.putStone(e.getX(), e.getY());
                    int boardX = e.getX() / Board.PIXELS_PER_POINT;
                    int boardY = e.getY() / Board.PIXELS_PER_POINT;
                    System.out.println("Mouse clicked at Board coordinates: (" + boardX + ", " + boardY + ")");
                    repaint();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics g) {
        if (boardImage == null) return;

        int boardWidth = boardImage.getWidth(this);
        int boardHeight = boardImage.getHeight(this);
        g.drawImage(boardImage, 0, 0, boardWidth, boardHeight, this);

        for (int i = 1; i < Board.SIZE; i++) {
            for (int j = 1; j < Board.SIZE; j++) {
                int stone = board.getStone(i * Board.PIXELS_PER_POINT, j * Board.PIXELS_PER_POINT);
                if (stone == 1) {
                    g.setColor(Color.BLACK);
                    g.fillOval(i * Board.PIXELS_PER_POINT, j * Board.PIXELS_PER_POINT, 20, 20);
                } else if (stone == 2) {
                    g.setColor(Color.WHITE); 
                    g.fillOval(i * Board.PIXELS_PER_POINT, j * Board.PIXELS_PER_POINT, 20, 20);
                }
            }
        }
    }
}

