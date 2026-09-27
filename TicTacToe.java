import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    int boardWidth = 600;
    int boardHeight = 690; // 50px for the text panel on top + room foe reset button

    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JButton resetButton = new JButton("Restart");

    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    boolean gameOver = false;
    int turns = 0;

    TicTacToe() {
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(new Color(135, 206, 235));
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(new Color(135, 206, 235));
        frame.add(boardPanel);

        for (int r = 0; r < 3; r++) {
            for (int C = 0; C < 3; C++) {
                JButton tile = new JButton();
                board[r][C] = tile;
                boardPanel.add(tile);

                tile.setBackground(new Color(135, 206, 235));
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);
                // tile.setText(currentPlayer);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver)
                            return;
                        JButton tile = (JButton) e.getSource();
                        if (tile.getText() == "") {
                            tile.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if (!gameOver) {
                                currentPlayer = currentPlayer == playerX ? playerO : playerX;
                                textLabel.setText(currentPlayer + "'s turn.");
                            }

                        }

                    }
                });
                resetButton.setFont(new Font("Arial", Font.BOLD, 20));
                resetButton.setFocusable(false);
                resetButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        resetGame();
                    }
                });
                frame.add(resetButton, BorderLayout.SOUTH);
            }
        }
    }

    void checkWinner() {
        // horizontal
        for (int r = 0; r < 3; r++) {
            if (board[r][0].getText() == "")
                continue;

            if (board[r][0].getText() == board[r][1].getText() &&
                    board[r][1].getText() == board[r][2].getText()) {
                for (int i = 0; i < 3; i++) {
                    setWinner(board[r][i]);
                }
                gameOver = true;
                return;
            }
        }

        // vertical
        for (int C = 0; C < 3; C++) {
            if (board[0][C].getText() == "")
                continue;

            if (board[0][C].getText() == board[1][C].getText() &&
                    board[1][C].getText() == board[2][C].getText()) {
                for (int i = 0; i < 3; i++) {
                    setWinner(board[i][C]);
                }
                gameOver = true;
                return;

            }

            // anti-diaganally
            if (board[0][2].getText() == board[1][1].getText() &&
                    board[1][1].getText() == board[2][0].getText() &&
                    board[0][2].getText() != "") {
                setWinner(board[0][2]);
                setWinner(board[1][1]);
                setWinner(board[2][0]);
                gameOver = true;
                return;
            }
        }

        // diagnally
        if (board[0][0].getText() == board[1][1].getText() &&
                board[1][1].getText() == board[2][2].getText() &&
                board[0][0].getText() != "") {
            for (int i = 0; i < 3; i++) {
                setWinner(board[i][i]);
            }
            gameOver = true;
            return;
        }

        if (turns == 9) {
            for (int r = 0; r < 3; r++) {
                for (int C = 0; C < 3; C++) {
                    setTie(board[r][C]);
                }
            }
            gameOver = true;
        }
    }

    void resetGame() {
        for (int r = 0; r < 3; r++) {
            for (int C = 0; C < 3; C++) { 
                board[r][C].setText("");
                board[r][C].setForeground(Color.white);
                board[r][C].setBackground(new Color(132, 206, 235));
            }
        
    }
    currentPlayer = playerX;
    turns = 0;
    gameOver = false;
    textLabel.setText("Tic-Tac-Toe");
    }

    void setWinner(JButton tile) {
        tile.setForeground(Color.green);
        tile.setBackground(Color.BLUE);
        textLabel.setText(currentPlayer + " is the winner!");
    }

    void setTie(JButton tile) {
        tile.setForeground(Color.CYAN);
        tile.setBackground(Color.BLUE);
        textLabel.setText("Tie!");
    }
}
