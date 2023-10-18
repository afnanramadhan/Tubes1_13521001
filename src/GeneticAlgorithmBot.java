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
        int[] selectedLoc1 = {-1, -1};
        int[] selectedLoc2 = {-1, -1};
        int[] selectedLoc3 = {-1, -1};
        int[] selectedLoc4 = {-1, -1};
        Button[][] tempMap = new Button[position.length][position[0].length];
        for (int i = 0; i < position.length; i++) {
            for (int j = 0; j < position[0].length; j++) {
                Button copyMap = new Button();
                copyMap.setText(position[i][j].getText());
                tempMap[i][j] = copyMap;
            }
        }

        List<int[]> a = geneticMinimax(tempMap, playerLabel);
        List<int[]> b = crossover(a);
        List<int[]> c = mutation(b);

        selectedLoc1 = c.get(0);
        selectedLoc2 = c.get(1);
        selectedLoc3 = c.get(2);
        selectedLoc4 = c.get(3);

        List<int[]> selectedLoc = new ArrayList<>();

        for (int i = 0; i<4; i++){
            int x = jumlahPenambahan(c.get(i)[0], c.get(i)[1], tempMap, playerLabel);
            selectedLoc.add(new int[]{x, c.get(i)[0], c.get(i)[1]});

        }

        Collections.sort(selectedLoc, new Comparator<int[]>() {
                    @Override
                    public int compare(int[] a, int[] b) {
                        // Compare the first element of each array
                        return Integer.compare(a[0], b[0]);
                    }
                });
        System.out.println(selectedLoc.get(0)[1] + " " + selectedLoc.get(0)[2]);
        System.out.println(selectedLoc.get(1)[1] + " " + selectedLoc.get(1)[2]);
        System.out.println(selectedLoc.get(2)[1] + " " + selectedLoc.get(2)[2]);
        System.out.println(selectedLoc.get(3)[1] + " " + selectedLoc.get(3)[2]);
        if (position[selectedLoc.get(0)[1]][selectedLoc.get(0)[2]].getText().equals("")){
            System.out.println(selectedLoc.get(0)[1] + " " + selectedLoc.get(0)[2]);
            return new int[] {selectedLoc.get(0)[1],selectedLoc.get(0)[2]};}
        else if (position[selectedLoc.get(1)[1]][selectedLoc.get(1)[2]].getText().equals("")){
            System.out.println(selectedLoc.get(1)[1] + " " + selectedLoc.get(1)[2]);
            return new int[] {selectedLoc.get(1)[1],selectedLoc.get(1)[2]};}
        else if (position[selectedLoc.get(2)[1]][selectedLoc.get(2)[2]].getText().equals("")){
            System.out.println(selectedLoc.get(2)[1] + " " + selectedLoc.get(2)[2]);
            return new int[] {selectedLoc.get(2)[1],selectedLoc.get(2)[2]};}
        else if (position[selectedLoc.get(3)[1]][selectedLoc.get(3)[2]].getText().equals("")){
            System.out.println(selectedLoc.get(3)[1] + " " + selectedLoc.get(3)[2]);
            return new int[] {selectedLoc.get(3)[1],selectedLoc.get(3)[2]};}
        else{
            while(true){
                int x = (int) (Math.random()*8);
                int y = (int) (Math.random()*8);
                if (position[x][y].getText() == "") {
                    return new int[]{x, y};
                }
            }
        }
    }
    
    public List<int[]> geneticMinimax(Button[][] maploc, String playerLabel) {
        int maxEval = Integer.MIN_VALUE;
        List<int[]> parents = new ArrayList<>();
        List<int[]> calonparents = new ArrayList<>();

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
                    
                        return Integer.compare(a[0], b[0]);
                    }
                });
                for(int i = 0 ; i < 4; i++){
                    parents.add(calonparents.get(i));
                }
                return parents;
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

    public List<int[]> crossover(List<int[]> parents){
        int[] parent1 = parents.get(0);
        int [] parent2 = parents.get(1);
        int[] parent3 = parents.get(2);
        int[] parent4 = parents.get(3);
        // int sum = parents.get(0)[0] + parents.get(1)[0] + parents.get(2)[0] + parents.get(3)[0]; 
        // int value1 = parent1[0]/sum;
        // int value2 = value1 + (parent2[0]/sum);
        // int value3 = value2 + (parent3[0]/sum);
        // int value4 = value3 + (parent4[0]/sum);

        int[] child1 = {parents.get(0)[1], parents.get(1)[2]};
        int[] child2 = {parents.get(1)[1], parents.get(0)[2]};
        int[] child3 = {parents.get(2)[1], parents.get(3)[2]};
        int[] child4 = {parents.get(3)[1], parents.get(2)[2]};

        List<int[]> children = new ArrayList<>();
        children.add(child1);
        children.add(child2);
        children.add(child3);
        children.add(child4);
        return children;

    }

    public List<int[]> mutation(List<int[]> children){
        for(int i=0; i<4; i++){
            int random = (int) (Math.random() * 2);
            if(random==0){
                int randomX = (int) (Math.random() * 8);  
                children.get(i)[0] = randomX;
                children.get(i)[1] = children.get(i)[1];
            }else{
                int randomY = (int) (Math.random() * 8);  
                children.get(i)[0] = children.get(i)[0];
                children.get(i)[1] = randomY;
            }
        }

        int[] mutasi1 = {children.get(1)[0], children.get(0)[1]};
        int[] mutasi2 = {children.get(0)[0], children.get(1)[1]};
        int[] mutasi3 = {children.get(3)[0], children.get(2)[1]};
        int[] mutasi4 = {children.get(2)[0], children.get(3)[1]};



        List<int[]> mutasi = new ArrayList<>();
        mutasi.add(mutasi1);
        mutasi.add(mutasi2);
        mutasi.add(mutasi3);
        mutasi.add(mutasi4);
        return mutasi;
    }
    

}