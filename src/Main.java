import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<Integer> numbers = new ArrayList<>();

    private synchronized static void inc(int number) {
        numbers.add(number);
    }

    private synchronized static void show() {
        System.out.println(numbers);
    }

    public static void main(String[] args) {
        System.out.println("Ola thread");

         Runnable inc = () -> {
            for (int i = 0; i < 1000; i++) {
                //se adiconar vai dar erro de acesso concorrente , temos que utilizar o acesso sincronizado
                //numbers.add(i);
                inc(i);
            }
        };

        Runnable dec = ()-> {
            for (int i = 0; i > -1000 ; i--) {
                inc(i);
            }
        };

        Runnable show = ()-> {
            for (int i = 0; i < 2500; i++) {
                show();
            }
        };

        new Thread(inc).start();
        new Thread(dec).start();
        new Thread(show).start();


    }
}
