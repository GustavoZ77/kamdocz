package cli.opc1.frp;

import rx.functions.Action1;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class MotorEjecucion {

    public static BiFunction<Consumer<String>, String, Action1> onError = (echo1, op) -> (t) -> {
        echo1.accept("Operacion " + op + " mal Formada");
    };

    public static Function<Consumer<String>, Action1> sumar = echo1 -> (command) -> {
        if (command.toString().startsWith("suma ")) {
            String[] operands = command.toString().replace("suma ", "").split("\\s");
            Integer result = new Integer(operands[0]) + new Integer(operands[1]);
            echo1.accept(result.toString());
        }
    };

    public static Function<Consumer<String>, Action1> resta = echo1 -> (command) -> {
        if (command.toString().startsWith("resta ")) {
            String[] operands = command.toString().replace("resta ", "").split("\\s");
            Integer result = new Integer(operands[0]) - new Integer(operands[1]);
            echo1.accept(result.toString());
        }
    };

    public static Function<Consumer<String>, Action1> imprime = echo1 -> (command) -> {
        if (command.toString().startsWith("imprime ")) {
            echo1.accept(command.toString().replace("imprime ", ""));
        }
    };

    public static Function<Consumer<String>, Action1> hora = echo1 -> (command) -> {
        if (command.toString().startsWith("hora ")) {
            String format = "HH:mm:ss";
            if (command.toString().contains("12"))
                format = "h:mm:ss a";
            echo1.accept(LocalTime.now().format(DateTimeFormatter.ofPattern(format)));
        }
    };

    public static Function<Consumer<String>, Action1> fecha = echo1 -> (command) -> {
        if (command.toString().startsWith("fecha")) {
            LocalDateTime local = LocalDateTime.now();
            echo1.accept(local.getDayOfMonth() + "/" + local.getMonthValue() + "/" + local.getYear());
        }
    };

}
