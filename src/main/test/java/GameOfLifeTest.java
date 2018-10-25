import de.red6.gameoflife.daniel.GameOfLife;
import org.junit.jupiter.api.Test;

public class GameOfLifeTest {

    GameOfLife gol = new GameOfLife();

    @Test
    public void testFirstIteration() {
        gol.init(10);

        gol.setAlive(4,5);
        gol.setAlive(4,6);
        gol.setAlive(4,7);

        gol.printGrid();
        gol.step();
        gol.printGrid();
    }

}
