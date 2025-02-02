import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import task1calculator.CalculatorUtils;
import task2compare.ArrayUtils;
import task3pair.Pair;

public class App {

    public static void main(String[] args) {

        Task1.run();
        Task2.run();
        Task3.run();
    }

}
class Task1 {

    static void run() {
        ConsoleUtils.printlnEmphasized("\nРабота обобщённых статических методов Калькулятора:\n");

        Number result;
        result = CalculatorUtils.sum(Integer.valueOf(15), Float.valueOf(29.159f));
        System.out.printf("Integer 15 + Float 29.159f = %s as Float\n", result.floatValue());

        result = CalculatorUtils.divide(Double.valueOf(123.456789), Long.valueOf(24));
        System.out.printf("Double 123.456789 / Long 24 = %s as Double\n", result.doubleValue());
        System.out.printf("Double 123.456789 / Long 24 = %s as Long\n", result.longValue());

        result = CalculatorUtils.multiply(Byte.valueOf((byte) 11), Short.valueOf((short) 32000));
        System.out.printf("Byte 11 * Short 32000 = %s as Integer\n", result.intValue());

        result = CalculatorUtils.subtract(Short.valueOf((short) 789), Double.valueOf(1000.4837));
        System.out.printf("Short 789 - Double 1000.4837 = %s as Double\n", result.doubleValue());
        System.out.printf("Short 789 - Double 1000.4837 = %s as Integer\n", result.intValue());
    }
}

class Task2 {

    private static final int VALUE_BOUND = 10000;
    private static final int N_NULLS = 3;

    static record ItemType(Integer value) {
        @Override
        public String toString() {
            return String.format("~%s~", value != null ? value.toString() : "Null");
        }
    }

    static void run() {
        ConsoleUtils.printlnEmphasized("\nРабота обобщённого метода compareArrays():\n");

        var rnd = ThreadLocalRandom.current();

        ItemType[] arrayA = new ItemType[20];
        // good values
        IntStream.range(0, arrayA.length).forEach(i -> {
            arrayA[i] = new ItemType(rnd.nextInt(VALUE_BOUND));
        });
        // contains null
        IntStream.range(0, N_NULLS).forEach(i -> {
            arrayA[rnd.nextInt(0, arrayA.length)] = new ItemType(null);
        });
        // nulls
        IntStream.range(0, N_NULLS).forEach(i -> {
            arrayA[rnd.nextInt(0, arrayA.length)] = null;
        });

        ItemType[] arrayB = new ItemType[20];
        IntStream.range(0, arrayA.length).forEach(i -> {
            arrayB[i] = arrayA[i] != null ? new ItemType(arrayA[i].value()) : null;
        });

        System.out.println("Исходные массивы:");
        System.out.println("arrayA: " + Arrays.toString(arrayA));
        System.out.println("arrayB: " + Arrays.toString(arrayB));
        System.out.println(
                ArrayUtils.<ItemType>compareArrays(arrayA, arrayB)
                        ? "Массивы одинаковые."
                        : "Массивы различаются!");

        arrayB[arrayB.length / 2] = new ItemType(VALUE_BOUND);
                        System.out.println("\nИсходные массивы:");
        System.out.println("arrayA: " + Arrays.toString(arrayA));
        System.out.println("arrayB: " + Arrays.toString(arrayB));
        System.out.println(
                ArrayUtils.<ItemType>compareArrays(arrayA, arrayB)
                        ? "Массивы одинаковые."
                        : "Массивы различаются!");
    }
}

class Task3 {

    static void run() {
        ConsoleUtils.printlnEmphasized("\nОбобщённый класс Pair:\n");

        var pair = new Pair<>(12345, "Текст");
        System.out.println(pair);

        var pair2 = new Pair<Float, Character[]>(456.235f, new Character[] { 'a', 'b', 'C' }, null, Arrays::toString);
        System.out.println(pair2);

        var pair3 = new Pair<Integer, Double>(null, null);
        System.out.println(pair3);
    }
}

class ConsoleUtils {
    static void printlnEmphasized(String text) {
        System.out.println("\u001b[1;4;97m" + text + "\u001b[0m");
    }
}