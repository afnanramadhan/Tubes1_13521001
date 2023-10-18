import javafx.scene.control.Button;

public class GeneticAlgorithmBot extends Bot {
    @Override
    public int[] move(Button[][] position, String playerLabel) {
        // create random move
        while(true){
            int x = (int) (Math.random()*8);
            int y = (int) (Math.random()*8);
            if (position[x][y].getText() == "") {
                System.out.println("GeneticAlgorithmBot move: " + x + " " + y);
                return new int[]{x, y};
            }
        }
    }
}
