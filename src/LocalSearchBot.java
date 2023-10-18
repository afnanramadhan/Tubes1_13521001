import java.util.Random;

import javafx.scene.control.Button;


public class LocalSearchBot extends Bot {
    @Override
    public int[] move(Button[][] position, String playerLabel, int roundsLeft) {
        int max = Integer.MIN_VALUE;
        int[] posisi = new int[2];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8 ; j++) {
                if (position[i][j].getText().equals("")){
                    int temp = jumlahPenambahan(i,j,position,playerLabel);
                    if (temp > max){
                        max = temp;
                        System.out.println("max: "+ max + " i: "+ i + " j: "+ j);
                        posisi[0] = i;
                        posisi[1] = j;
                    }else if(temp==max){
                        Random random = new Random();
                        int randomNumber = random.nextInt(2);
                        if(randomNumber==1){
                            max = temp;
                            System.out.println("max: "+ max + " i: "+ i + " j: "+ j);
                            posisi[0] = i;
                            posisi[1] = j;
                        }
                    }
                }
            }
        }        
        return posisi;
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
