import rx.Observable;
import rx.Subscription;

public class ArrayInstructions {

    public static void main (String... args) {

        String[][] instruccionesArr = new String[][] {
                {"imprime", "Hola Mundo!", ";"},
                {"suma", "3", "4", ";"},
                {"imprime", "Adios!", ";"},
                {"resta", "3", "4", ";"}
        };

        Observable<String[]> instruccionesObs = Observable.from(instruccionesArr);

        instruccionesObs
                .subscribe(
                        inst -> {
                            String instrucc = inst[0];

                            switch (instrucc) {
                                case "imprime":
                                    System.out.println(inst[1]);
                                    break;
                                case "suma":
                                    int suma = Integer.parseInt(inst[1]) + Integer.parseInt(inst[2]);
                                    System.out.println(suma);
                                    break;
                                case "resta":
                                    int resta = Integer.parseInt(inst[1]) - Integer.parseInt(inst[2]);
                                    System.out.println(resta);
                                    break;
                                default:
                                    break;
                            }
                        },
                        err -> new Exception("Something went wrong: " + err.getLocalizedMessage(), err));
    }
}
