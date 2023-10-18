import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;
import java.time.LocalTime;
import javafx.scene.control.Button;

public class MinimaxBot extends Bot{
    private int roundsLeft;
    Button[][] tempMap = new Button[8][8];

    @Override
    public int[] move(Button[][] position, String playerLabel, int roundsLeft)  {
        this.roundsLeft = roundsLeft;
        int[] selectedLoc = {-1, -1};
        for(int i = 0 ; i < position.length; i++){
            for(int j = 0 ; j < position[0].length; j++){
                Button copyMap = new Button();
                copyMap.setText(position[i][j].getText());
                this.tempMap[i][j] = copyMap;
            }
        }
        int depth = 4>this.roundsLeft?this.roundsLeft:4;
        LocalTime startTime = LocalTime.now();
        int[] a = minimax(this.tempMap, depth, Integer.MIN_VALUE, Integer.MAX_VALUE, playerLabel, true, startTime);
        selectedLoc[0] = a[1];
        selectedLoc[1] = a[2];
        return selectedLoc;
    }
    
    public int minimaxEval(Button[][] maploc, String playerLabel) {
        int eval = 0;
        for(int i = 0 ; i < 8; i++){
            for(int j = 0 ; j < 8; j++){
                if(playerLabel=="O"){
                    if(maploc[i][j].getText().equals("O")) {
                        eval++;
                    }
                    if(maploc[i][j].getText().equals("X")) {
                        eval--;
                    }
                }else{
                    if(maploc[i][j].getText().equals("X")) {
                        eval++;
                    }
                    if(maploc[i][j].getText().equals("O")) {
                        eval--;
                    }

                }
            }
        }
        return eval;
    }

    public int[] minimax(Button[][] maploc, int depth, int alpha, int beta, String playerLabel, boolean isMaximazing, LocalTime startTime){

        LocalTime now = LocalTime.now();
        int sisaWaktu = now.toSecondOfDay()-startTime.toSecondOfDay();
        System.out.println(sisaWaktu);
        
        if(depth == 0 || sisaWaktu >= 5) {
            if(depth > 0){
                Bot localBot = new LocalSearchBot();
                int[] unfinishedEval = localBot.move(this.tempMap, playerLabel, roundsLeft);
                return new int[] {69, unfinishedEval[0], unfinishedEval[1], 0};
            }
            int evaluation = minimaxEval(maploc,playerLabel);
            return new int[] {evaluation, -1, -1, 1};
        }

        if(isMaximazing) {
            int maxEval = Integer.MIN_VALUE;
            int bestX = -1;
            int bestY = -1;

            for(int i = 0; i < 8; i++){
                for(int j = 0 ; j < 8; j++){
                    if(maploc[i][j].getText().equals("")){
                        Button[][] newPos = updateVirtualPosition(i, j, maploc, playerLabel);
                        int[] selection =  minimax(newPos, depth - 1, alpha, beta, playerLabel,!isMaximazing, startTime);
                        int eval = selection[0];
                        if(selection[3] == 0){
                            return new int[] {eval,selection[1], selection[2], 0};
                        }
                        else{
                            if (eval > maxEval){
                                bestX = i;
                                bestY = j;
                                maxEval = eval;
                            }
    
                            alpha = Math.max(alpha, eval);
                            if(beta <= alpha) {
                                break;
                            }
                        }
                    }
                }
            }
            return new int[] {maxEval, bestX, bestY, 1};
        }
        else {
            int minEval = Integer.MAX_VALUE;
            int bestX = -1;
            int bestY = -1;

            for(int i = 0 ; i < 8 ; i++){
                for(int j = 0 ; j < 8; j++){
                    if (maploc[i][j].getText().equals("")) {
                        Button[][] newPos = updateVirtualPosition(i, j, maploc,playerLabel);
                        int[] selection = minimax(newPos, depth - 1, alpha, beta, playerLabel, !isMaximazing, startTime);
                        int eval = selection[0];
                        if(selection[3] == 0){
                            return new int[] {eval,selection[1], selection[2], 0};
                        }
                        else{
                            if(eval < minEval){
                                minEval = eval;
                                bestX = i;
                                bestY = j;
                            }
    
                            beta = Math.min(beta, eval);
                            if(beta <= alpha) {
                                break;
                            }
                        }

                    }
                }
            }
            return new int[] {minEval, bestX, bestY, 1};
        }
    }

    public Button[][] updateVirtualPosition(int x, int y, Button[][] position, String playerLabel) {
        Button[][] finalPosition = new Button[position.length][position[0].length];
        for(int i = 0 ; i <position.length;i++){
            for(int j = 0 ; j < position[0].length; j++){
                Button copiedButton = new Button();
                copiedButton.setText(position[i][j].getText());
                finalPosition[i][j] = copiedButton;
            }
        }

        if(playerLabel=="O"){
            finalPosition[x][y].setText("O");
            if(x > 0 && finalPosition[x-1][y].getText().equals("X")){
                finalPosition[x-1][y].setText("O");
            }
            if(x < 7 && finalPosition[x+1][y].getText().equals("X")){
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
            if(x < 7 && finalPosition[x+1][y].getText().equals("O")){
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