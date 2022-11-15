package rc.sudokugenius;

public class Meter {

    public static void main(String[] args) {
        int a = 1;

        for (int i = 0; i < 3; i++) {
            long before = System.currentTimeMillis();

            for (int j = 0; j < 1000000; j++) {
                if (a == 1) {
                    a = 1;
                }
            }

            System.out.println(System.currentTimeMillis() - before);
        }

        for (int i = 0; i < 3; i++) {
            long before = System.currentTimeMillis();

            for (int j = 0; j < 1000000; j++) {
                switch (a) {
                    case 1:
                        a = 1;
                        break;
                }
            }

            System.out.println(System.currentTimeMillis() - before);
        }
    }
}