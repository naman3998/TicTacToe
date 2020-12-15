import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// Tic Tac Toe using Spring Framework in Java.
public class TicTacToe extends JFrame implements ActionListener {
    public static int Board_Size = 3;
    public static enum GameStatus {
        Incomplete , XWins , ZWins , Tie

    }
    private JButton [][] buttons = new JButton[Board_Size][Board_Size];
    boolean crossTurn = true;

    public TicTacToe()
    {
        super.setTitle("TicTacToe");
        super.setSize(800 , 800);
        super.setVisible(true);
        GridLayout grid = new GridLayout(Board_Size , Board_Size);
        super.setLayout(grid);
        Font font = new Font("Comic Sans " , 1 , 150);
        for (int row = 0; row < Board_Size; row++) {
            for (int col = 0; col < Board_Size ; col++) {

                JButton button = new JButton("");
                buttons[row][col]= button;
                button.setFont(font);
                button.addActionListener(this);
                super.add(button);
            }

        }
        super.setResizable(true);
        super.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        JButton clickedButton = (JButton)e.getSource();
        makeMove(clickedButton);

        GameStatus gs = this.getGameStatus();
        if(gs==GameStatus.Incomplete){
            return;
        }
        declareWinner(gs);

        int choice = JOptionPane.showConfirmDialog(this, "Do you want to restart the game");
        if(choice == JOptionPane.YES_OPTION){
            for (int row = 0; row < Board_Size; row++) {
                for (int col = 0; col < Board_Size; col++) {
                    buttons[row][col].setText("");
                }
            }
            crossTurn = true;
        }
        else {
            super.dispose();
        }
    }

    private  void makeMove(JButton clickedButton){
        String btntext = clickedButton.getText();
        if (btntext.length() > 0){
    JOptionPane.showMessageDialog(this, "Invalid Move");
        }
        else
        {
           if(crossTurn){
               clickedButton.setText("X");
           }
           else
           {
               clickedButton.setText("O");
           }
           crossTurn =! crossTurn;
        }
    }

    private GameStatus getGameStatus() {
        String text1 = "", text2 = "";
        // txt1 and txt 2 for storing the values of X and O for comparing them
        int row = 0, col = 0;

        row = 0;
        while (row < Board_Size) {
            col = 0;
            while (col < Board_Size - 1) {
                text1 = buttons[row][col].getText();
                text2 = buttons[row][col + 1].getText();

                if (!text1.equals(text2) || text1.length() == 0) {
                    break;
                }
                col++;
            }
            if (col == Board_Size - 1) {
                if (text1.equals("X")) {
                    return GameStatus.XWins;
                } else {
                    return GameStatus.ZWins;
                }
            }
            row++;
        }

        col = 0;
        while (col < Board_Size) {
            row = 0;
            while (row < Board_Size - 1) {
                text1 = buttons[row][col].getText();
                text2 = buttons[row + 1][col].getText();

                if (!text1.equals(text2) || text1.length() == 0) {
                    break;
                }
                row++;
            }
            if (row == Board_Size - 1) {
                if (text1.equals("X")) {
                    return GameStatus.XWins;
                } else {
                    return GameStatus.ZWins;
                }
            }
            col++;
        }

        row = 0;
        col = 0;
        while (row < Board_Size - 1) {
            text1 = buttons[row][col].getText();
            text2 = buttons[row + 1][col + 1].getText();

            if (!text1.equals(text2) || text1.length() == 0) {
                break;
            }

            row++;
            col++;
        }

        if (row == Board_Size - 1) {
            if (text1.equals("X")) {
                return GameStatus.XWins;
            } else {
                return GameStatus.ZWins;
            }
        }

        row = Board_Size - 1;
        col = 0;
        while (row > 0) {
            text1 = buttons[row][col].getText();
            text2 = buttons[row - 1][col + 1].getText();

            if (!text1.equals(text2) || text1.length() == 0) {
                break;
            }
            row--;
            col++;
        }
        if (row == 0) {
            if (text1.equals("X")) {
                return GameStatus.XWins;
            } else {
                return GameStatus.ZWins;
            }
        }

        String txt = "";
        for (row = 0; row < Board_Size ; row++) {
            for ( col = 0; col < Board_Size ; col++) {
                txt = buttons[row][col].getText();
                if(txt.length()==0){
                    return GameStatus.Incomplete;
                }

            }
        }
        return GameStatus.Tie;

        }

        private void declareWinner(GameStatus gs){
        if(gs==GameStatus.XWins){
            JOptionPane.showMessageDialog(this, "X Wins");
        }
        else if(gs==GameStatus.ZWins){
            JOptionPane.showMessageDialog(this, "Z Wins");
        }
        else {
            JOptionPane.showMessageDialog(this, "It is a Tie");
        }
        }


}
