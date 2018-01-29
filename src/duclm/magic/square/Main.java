package duclm.magic.square;

import java.io.*;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int n = 10 + new Random().nextInt(90);
        //n = 16;
        System.out.println("n = " + n);

        try {
            long start1 = System.currentTimeMillis();
            test1(n);
            System.out.format("Random time: %5dms\n", (System.currentTimeMillis() - start1));

            long start2 = System.currentTimeMillis();
            test2();
            System.out.format("Magic time:  %5dms\n", (System.currentTimeMillis() - start2));

            System.out.format("Total time:  %5dms", (System.currentTimeMillis() - start1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void test1(int n) throws FileNotFoundException {
        try (PrintStream printStream = new PrintStream(new File("./matrix.txt"))) {
            new RandomSquare(n).output(printStream);
        }
    }

    private static void test2() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("./matrix.txt"));
             PrintStream printStream = new PrintStream(new File("./smatrix.txt"))) {
            int n = Integer.parseInt(br.readLine());
            new MagicSquare(n).output(printStream);
        }
    }


}
