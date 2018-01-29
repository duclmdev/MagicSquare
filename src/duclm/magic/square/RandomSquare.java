package duclm.magic.square;

import java.io.PrintStream;
import java.util.Random;

public class RandomSquare extends Square{

    RandomSquare(int n) {
        super(n);
    }

    protected void create() {
        Random random = new Random();
        int x, y;
        for (int i = 1, square = n * n; i <= square; i++) {
            do {
                x = random.nextInt(n);
                y = random.nextInt(n);
            } while (a[x][y] != 0);
            a[x][y] = i;
        }
    }

    @Override
    public void output(PrintStream out) {
        out.println(n);
        super.output(out);
    }
}
