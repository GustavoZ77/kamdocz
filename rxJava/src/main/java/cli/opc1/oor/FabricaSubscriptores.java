package cli.opc1.oor;

import rx.Subscriber;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;

public class FabricaSubscriptores {
    /////////////////////////////// SUMA
    public static Subscriber<String> subscriptorSuma(Consumer<String> consumidorResultados) {
        return new Subscriber<String>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable throwable) {
                consumidorResultados.accept("Operacion suma esta mal formada");
            }

            @Override
            public void onNext(String command) {
                if (command.startsWith("suma ")) {
                    java.lang.String[] operands = command.replace("suma ", "").split("\\s");
                    Integer result = new Integer(operands[0]) + new Integer(operands[1]);
                    consumidorResultados.accept(result.toString());
                }
            }
        };
    }

    //////////////// RESTA
    public static Subscriber<String> subscriptorResta(Consumer<String> consumidorResultados) {
        return new Subscriber<String>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable throwable) {
                consumidorResultados.accept("Operacion resta esta mal formada");
            }

            @Override
            public void onNext(String command) {
                if (command.startsWith("resta ")) {
                    java.lang.String[] operands = command.replace("resta ", "").split("\\s");
                    Integer result = new Integer(operands[0]) - new Integer(operands[1]);
                    consumidorResultados.accept(result.toString());
                }
            }
        };
    }

    ///////////////////////// IMPRIME
    public static Subscriber<String> subscriptorImprime(Consumer<String> consumidorResultados) {
        return new Subscriber<String>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable throwable) {
                consumidorResultados.accept("Operacion imprime esta mal formada");
            }

            @Override
            public void onNext(String command) {
                if (command.startsWith("imprime ")) {
                    consumidorResultados.accept(command.replace("imprime ", ""));
                }
            }
        };
    }

    //// HORA
    public static Subscriber<String> subscriptorHora(Consumer<String> consumidorResultados) {
        return new Subscriber<String>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable throwable) {
                consumidorResultados.accept("Operacion imprime esta mal formada");
            }

            @Override
            public void onNext(String command) {
                if (command.startsWith("hora ")) {
                    String format = "HH:mm:ss";
                    if (command.contains("12"))
                        format = "h:mm:ss a";
                    consumidorResultados.accept(LocalTime.now().format(DateTimeFormatter.ofPattern(format)));
                }
            }
        };
    }

    //// FECHA
    public static Subscriber<String> subscriptorFecha(Consumer<String> consumidorResultados) {
        return new Subscriber<String>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable throwable) {
                consumidorResultados.accept("Operacion fecha esta mal formada");
            }

            @Override
            public void onNext(String command) {
                if (command.startsWith("fecha")) {
                    LocalDateTime local = LocalDateTime.now();
                    consumidorResultados.accept(local.getDayOfMonth() + "/" + local.getMonthValue() + "/" + local.getYear());
                }
            }
        };
    }
}
