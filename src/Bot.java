import javafx.scene.control.Button;

public abstract class Bot {

    public abstract int[] move(Button[][] board);
    // public abstract int evaluate();

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


    // public int[] move(Button[][] board) {
    //     // create random move
    //     while(true){
    //         int x = (int) (Math.random()*8);
    //         int y = (int) (Math.random()*8);
    //         if (board[x][y].getText() == "") {
    //             return new int[]{x, y};
    //         }
    //     }
        
    //     // return new int[]{(int) (Math.random()*8), (int) (Math.random()*8)};
    // }
}
