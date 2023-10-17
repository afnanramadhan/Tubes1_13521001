import javafx.scene.control.Button;

public class Bot {
    public int[] move(Button[][] board) {
        // create random move
        while(true){
            int x = (int) (Math.random()*8);
            int y = (int) (Math.random()*8);
            if (board[x][y].getText() == "") {
                return new int[]{x, y};
            }
        }
        
        // return new int[]{(int) (Math.random()*8), (int) (Math.random()*8)};
    }
}
