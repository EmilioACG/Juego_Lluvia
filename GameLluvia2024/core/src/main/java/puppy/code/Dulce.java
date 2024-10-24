package puppy.code;

public class Dulce extends Comida implements Interactuable {
    //ATRIBUTOS
    private final int atributoBasico = 10;

    /**
     * Obtiene el atributo basico de dulce
     *
     * @return El
     */
    public int getAtributoBasico(){
        return this.atributoBasico;
    }

    //CONSTRUCTOR
    public Dulce(String nombre) {
        super(nombre);
    }


    public void dulceInteractuaConNiño(Child niño) {
        switch (getNombre()) {
            case "FRUGELE":
                interaccionFrugele(niño);
                break;
            case "SUPEROCHO":
                interaccionSuperOcho(niño);
                break;
            case "PICODULCE":
                interaccionPicoDulce(niño);
                break;
        }
    }

    /**
     * Aplica la interacción cuando un niño colisiona con un frugelé.
     * Al colisionar, se suman 10 puntos al puntaje total del niño,
     *
     * @param niño Objeto de tipo Child que colisionó con el Dulce de nombre "FRUGELE"
     */
    private void interaccionFrugele(Child niño) {
        niño.sumarPuntos(getAtributoBasico());
    }

    /**
     * Aplica la interacción cuando un niño colisiona con un Super8.
     * Al colisionar, se suman 20 puntos al puntaje total del niño,
     * y 10 puntos vida actual.
     *
     * @param niño Oobjeto de tipo Child que colisionó con el Dulce de nombre "SUPEROCHO"
     */
    private void interaccionSuperOcho(Child niño) {
        niño.sumarPuntos(getAtributoBasico() * 2);
        //niño.sumarVidas(getAtributoBasico());
    }

    /**
     * Aplica la interacción cuando un niño colisiona con un Pico Dulce.
     * Al colisionar, se suman 50 puntos al puntaje total del niño,
     * y este se vuelve invulnerable por 5 segundos a futuras interacciones con objetos de la clase Verdura.
     *
     * @param niño Oobjeto de tipo Child que colisionó con el Dulce de nombre "PICODULCE"
     */
    private void interaccionPicoDulce(Child niño) {
        niño.sumarPuntos(getAtributoBasico() * 5);

    }

}
