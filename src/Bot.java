import javafx.scene.control.Button;

public abstract class Bot {

    public abstract int[] move(Button[][] board, String playerLabel);
    // public abstract int evaluate();


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
