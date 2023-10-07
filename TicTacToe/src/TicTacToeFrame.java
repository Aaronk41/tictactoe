import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeFrame extends JFrame {
    private JButton[][] buttons;
    private char currentPlayer;
    private boolean gameWon;
    private int moveCount;

    public TicTacToeFrame() {
        buttons = new JButton[3][3];
        currentPlayer = 'X';
        gameWon = false;
        moveCount = 0;

        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(new GridLayout(3, 3));

        initializeButtons();
    }

    private void initializeButtons() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                final int finalRow = row; // Declare row as final
                final int finalCol = col; // Declare col as final

                JButton button = new JButton("");
                button.setFont(new Font("Arial", Font.PLAIN, 48));
                buttons[row][col] = button;

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!gameWon && buttons[finalRow][finalCol].getText().isEmpty()) {
                            buttons[finalRow][finalCol].setText(String.valueOf(currentPlayer));
                            moveCount++;

                            if (checkForWin(finalRow, finalCol)) {
                                JOptionPane.showMessageDialog(null, currentPlayer + " wins!");
                                gameWon = true;
                                restartGame();
                            } else if (moveCount == 9) {
                                JOptionPane.showMessageDialog(null, "It's a tie!");
                                restartGame();
                            } else {
                                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                            }
                        }
                    }
                });

                add(button);
            }
        }
    }

    private boolean checkForWin(int row, int col) {
        // Check the current row
        if (buttons[row][0].getText().equals(buttons[row][1].getText()) &&
                buttons[row][0].getText().equals(buttons[row][2].getText())) {
            return true;
        }

        // Check the current column
        if (buttons[0][col].getText().equals(buttons[1][col].getText()) &&
                buttons[0][col].getText().equals(buttons[2][col].getText())) {
            return true;
        }

        // Check the main diagonal (top-left to bottom-right)
        if (row == col &&
                buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[0][0].getText().equals(buttons[2][2].getText())) {
            return true;
        }

        // Check the other diagonal (top-right to bottom-left)
        if (row + col == 2 &&
                buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                buttons[0][2].getText().equals(buttons[2][0].getText())) {
            return true;
        }

        return false;
    }

    private void restartGame() {
        int choice = JOptionPane.showConfirmDialog(null, "Play another game?", "Restart Game", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            // Reset the game
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    buttons[row][col].setText("");
                }
            }
            currentPlayer = 'X';
            gameWon = false;
            moveCount = 0;
        } else {
            // Exit the application
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TicTacToeFrame frame = new TicTacToeFrame();
            frame.setVisible(true);
        });
    }
}





