import javafx.scene.control.Button;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GeneticAlgorithmBot extends Bot {
    private int depth = 4;

    @Override
    public int[] move(Button[][] position, String playerLabel, int roundsLeft) {
        this.roundsLeft = roundsLeft;
        int[] selectedLoc = {-1, -1};
        Button[][] tempMap = new Button[position.length][position[0].length];
        for (int i = 0; i < position.length; i++) {
            for (int j = 0; j < position[0].length; j++) {
                Button copyMap = new Button();
                copyMap.setText(position[i][j].getText());
                tempMap[i][j] = copyMap;
            }
        }

        int[] a = geneticMinimax(tempMap, depth, playerLabel);
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
    public int [] geneticMinimax(Button[][] maploc, int depth, String playerLabel) {
        int maxEval = Integer.MIN_VALUE;
        int bestX = -1;
        int bestY = -1;
        List<int[]> parents = new ArrayList<>();
        List<int[]> calonparents = new ArrayList<>();

        List<Chromosome> population = generateInitialPopulation(maploc, playerLabel);


            for(int i = 0; i < 8; i++){
                for(int j = 0 ; j < 8; j++){
                    if(maploc[i][j].getText().equals("")){
                        int temp = jumlahPenambahan(i, j, maploc, playerLabel);
                        calonparents.add(new int[]{temp, i, j});
                        }
                    }
                }
                Collections.sort(calonparents, new Comparator<int[]>() {
                    @Override
                    public int compare(int[] a, int[] b) {
                        // Compare the first element of each array
                        return Integer.compare(a[0], b[0]);
                    }
                });
                for(int i = 0 ; i < 4; i++){
                    parents.add(calonparents.get(i));
                }
    }
    public int jumlahPenambahan(int posisiX, int posisiY, Button[][] button, String playerLabel){
        int count = 1;
        String oponentLabel = "";
        if(playerLabel.equals("O")){
            oponentLabel = "X";
        }else{
            oponentLabel = "O";
        }

        if(posisiX>=1){
            if (button[posisiX-1][posisiY].getText().equals(oponentLabel))
                count++;
        }
        if(posisiX<=6){
            if (button[posisiX+1][posisiY].getText().equals(oponentLabel))
                count++;
        }
        if(posisiY>=1){
            if (button[posisiX][posisiY-1].getText().equals(oponentLabel))
                count++;
        }
        if(posisiY<=6){
            if (button[posisiX][posisiY+1].getText().equals(oponentLabel))
                count++;
        }
        return count;
    }
    
}