package duclm.magic.square;

import java.io.PrintStream;

public abstract class Square {
    int n;
    int[][] a;

    Square(int n) {
        this.n = n;
        this.a = new int[n][n];
        create();
    }

    protected abstract void create();

    public void output(PrintStream out) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                out.format("%3d ", a[i][j]);
            }
            out.println();
        }
    }
}
