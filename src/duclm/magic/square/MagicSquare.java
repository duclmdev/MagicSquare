package duclm.magic.square;

import java.io.PrintStream;

public final class MagicSquare extends Square {
    private static final int ODD_CASE = 1;
    private static final int EVEN_4_CASE = 2;
    private static final int EVEN_4_2_CASE = 3;

    MagicSquare(int n) {
        super(n);
    }

    private int checkCase() {
        if (n % 2 != 0) return ODD_CASE;
        if (n % 4 != 0) return EVEN_4_2_CASE;
        return EVEN_4_CASE;
    }

    protected void create() {
        switch (checkCase()) {
            case ODD_CASE:
                createOdd();
                break;
            case EVEN_4_CASE:
                createEven4();
                break;
            case EVEN_4_2_CASE:
                createEven42();
                break;
        }
    }

    private void createOdd() {
        int x = 0;
        int y = n / 2;
        a[x][y] = 1;

        for (int i = 2, square = n * n; i <= square; i++) {
            x = (x - 1) < 0 ? (n - 1) : (x - 1);
            y = (y + 1) < n ? (y + 1) : 0;

            if (a[x][y] != 0) {
                x = (x + 2) < n ? (x + 2) : 0;
                y = (y - 1) < 0 ? (n - 1) : (y - 1);
            }

            a[x][y] = i;
        }
    }

    private void createEven4() {
        int count = 0;
        int square = n * n;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (((i % 4 == 0 || i % 4 == 3) && (j % 4 == 0 || j % 4 == 3)) || ((i % 4 == 1 || i % 4 == 2) && (j % 4 == 1 || j % 4 == 2))) {
                    a[i][j] = ++count;
                } else {
                    a[i][j] = square - count++;
                }
            }
        }
    }

    private void createEven42() {
        int m = (n - 2) / 4;
        int side = n / 2;
        int x = 0;
        int y = side / 2;

        int count = goL(2 * x, 2 * y, 0);

        for (int i = 2, square = side * side; i <= square; i++) {
            x = (x - 1) < 0 ? (side - 1) : (x - 1);
            y = (y + 1) < side ? (y + 1) : 0;

            if (a[2 * x][2 * y] != 0) {
                x = (x + 2) < side ? (x + 2) : 0;
                y = (y - 1) < 0 ? (side - 1) : (y - 1);
            }

            count = go(m, x, y, count);
        }
    }

    private int go(int m, int x, int y, int i) {
        if (x == m + 1 || (x == m && y == m)) {
            return goU(2 * x, 2 * y, i);
        }

        if (x <= m || (x == m + 1 && y == m)) {
            return goL(2 * x, 2 * y, i);
        }

        return goX(2 * x, 2 * y, i);

    }

    private int goL(int x, int y, int i) {
        a[x][y + 1] = ++i;
        a[x + 1][y] = ++i;
        a[x + 1][y + 1] = ++i;
        a[x][y] = ++i;
        return i;
    }

    private int goU(int x, int y, int i) {
        a[x][y] = ++i;
        a[x + 1][y] = ++i;
        a[x + 1][y + 1] = ++i;
        a[x][y + 1] = ++i;
        return i;
    }

    private int goX(int x, int y, int i) {
        a[x][y] = ++i;
        a[x + 1][y + 1] = ++i;
        a[x + 1][y] = ++i;
        a[x][y + 1] = ++i;
        return i;
    }

    @Override
    public void output(PrintStream out) {
        out.println(n);
        out.println(constant());
        super.output(out);
    }

    private int constant() {
//         int constant = 0;
//         for (int i = 0; i < n; i++) {
//             constant += a[i][i];
//         }
//         return constant;
        return (n * n * n + n) / 2;
    }


}
