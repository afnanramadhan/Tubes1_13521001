import javafx.scene.control.Button;


public class LocalSearchBot extends Bot {
    @Override
    public int[] move(Button[][] position) {
        // create random move
        while(true){
            int x = (int) (Math.random()*8);
            int y = (int) (Math.random()*8);
            if (position[x][y].getText() == "") {
                System.out.println("Local Search move: " + x + " " + y);
                return new int[]{x, y};
            }
        }
    }
}
