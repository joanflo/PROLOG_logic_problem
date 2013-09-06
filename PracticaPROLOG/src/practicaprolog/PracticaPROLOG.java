
package practicaprolog;

/**
 * Classe des d'on s'inicia el programa
 * @author joan i bernat
 */
public class PracticaPROLOG {

    /**
     * Constructor principal. Instanciam les dades del problema i també el motor
     * d'inferència per a la resolució.
     */
    public PracticaPROLOG() {
        LlistesInformacio dadesProblema = new LlistesInformacio();
        MotorInferencia motor = new MotorInferencia(dadesProblema);
        motor.iniciaMotor();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new PracticaPROLOG();
    }
}