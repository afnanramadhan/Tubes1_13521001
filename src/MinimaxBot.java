import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;

import javafx.scene.control.Button;

public class MinimaxBot extends Bot{

    @Override
    public int[] move(java.awt.Button[][] position, String playerLabel, int roundsLeft)  {
        // int max = Integer.MIN_VALUE;
        int[] selectedLoc = {-1, -1};
        Button tempMap = new Button[position.length][position[0].length];
        for(int i = 0 ; i < position.length; i++){
            for(int j = 0 ; j < position[0].length; j++){
                Button copyMap = new Button();
                copyMap.setText(position[i][j].getText());
                tempMap[i][j] = copyMap;
            }
        }
        int[] a = minimax(position, roundsLeft, Integer.MAX_VALUE, Integer.MIN_VALUE);
        selectedLoc[0] = a[1];
        selectedLoc[1] = a[2];
        return selectedLoc;
    }
    
    public int minimaxEval(Button[][] maploc) {
        int eval = 0;
        for(int i = 0 ; i < 8; i++){
            for(int j = 0 ; j < 8; j++){
                if(maploc[i][j].getText().equals("O")) {
                    eval++;
                }
                if(maploc[i][j].getText().equals("X")) {
                    eval--;
                }
            }
        }
        return eval;
    }

    public int[] minimax(Button[][] maploc, int roundsLeft, int alpha, int beta, String playerLabel){

        if(roundsLeft == 0) {
            int evaluation = minimaxEval(maploc);
            return new int[] {evaluation, -1, -1};
        }

        if(playerLabel = "O") {
            int maxEval = Integer.MIN_VALUE;
            int bestX = -1;
            int bestY = -1;

            for(int i = 0; i < 8; i++){
                for(int j = 0 ; j < 8; j++){
                    if(maploc[i][j].getText().equals("")){
                        Button[][] newPos = updateVirtualPosition(i, j, maploc, 1);
                        int[] selection =  minimax(newPos, roundsLeft - 1, alpha, beta, "X");
                        int eval = selection[0];

                        if (eval > maxEval){
                            bestX = i;
                            bestY = j;
                            maxEval = eval;
                        }

                        alpha = Math.max(alpha, eval);
                        if(beta < alpha) break;
                    }
                }
            }
            return new int[] {maxEval, bestX, bestY};
        }
        else {
            int minEval = Integer.MAX_VALUE;
            int bestX = -1;
            int bestY = -1;

            for(int i = 0 ; i < 8 ; i++){
                for(int j = 0 ; j < 8; j++){
                    if (maploc[i][j].getText().equals("")) {
                        Button[][] newPos = updateVirtualPosition(i, j, maploc,0);
                        int[] selection = minimax(newPos, roundsLeft - 1, alpha, beta, "O");
                        int eval = selection[0];

                        if(eval < minEval){
                            minEval = eval;
                            bestX = i;
                            bestY = j;
                        }

                        beta = Math.min(beta, eval);
                    }
                }
            }
            return new int[] {minEval, bestX, bestY};
        }
    }

    public Button[][] updateVirtualPosition(int x, int y, Button[][] position, boolean playerO) {
        Button[][] finalPosition = new Button[position.length][position[0].length];
        for(int i = 0 ; i <position.length;i++){
            for(int j = 0 ; j < position[0].length; j++){
                Button copiedButton = new Button();
                copiedButton.setText(position[i][j].getText());
                finalPosition[i][j] = copiedButton;
            }
        }

        if(playerO){
            finalPosition[x][y].setText("0");
            if(x > 0 && finalPosition[x-1][y].getText().equals("X")){
                finalPosition[x-1][y].setText("O");
            }
            if(x < 7 && finalPosition[x-1][y].getText().equals("X")){
                finalPosition[x+1][y].setText("O");
            }
            if(y > 0 && finalPosition[x][y-1].getText().equals("X")){
                finalPosition[x][y-1].setText("O");
            }
            if(y < 7 && finalPosition[x][y+1].getText().equals("X")){
                finalPosition[x][y+1].setText("O");
            }
        }
        else {
            finalPosition[x][y].setText("X");
            if(x > 0 && finalPosition[x-1][y].getText().equals("O")){
                finalPosition[x-1][y].setText("X");
            }
            if(x < 7 && finalPosition[x-1][y].getText().equals("O")){
                finalPosition[x+1][y].setText("X");
            }
            if(y > 0 && finalPosition[x][y-1].getText().equals("O")){
                finalPosition[x][y-1].setText("X");
            }
            if(y < 7 && finalPosition[x][y+1].getText().equals("O")){
                finalPosition[x][y+1].setText("X");
            }
        }
        return finalPosition;
    }
}