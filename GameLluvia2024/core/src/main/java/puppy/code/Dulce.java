package puppy.code;

public class Dulce extends Comida implements Interactuable {
    private int atributoBasico = 10;

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
                interaccionComun(niño);
                break;
            case "SUPEROCHO":
                interaccionPocoComun(niño);
                break;
            case "PICODULCE":
                interaccionRara(niño);
                break;
        }
    }

    /**
     * Aplica la interacción cuando un niño colisiona con un frugelé.
     * Al colisionar, se suman 10 puntos al puntaje total del niño,
     *
     * @param niño Objeto de tipo Child que colisionó con el Dulce de nombre "FRUGELE"
     */
    @Override
    public void interaccionComun(Child niño) {
        niño.sumarPuntos(getAtributoBasico());
    }

    /**
     * Aplica la interacción cuando un niño colisiona con un Super8.
     * Al colisionar, se suman 20 puntos al puntaje total del niño,
     * y 10 puntos vida actual.
     *
     * @param niño Objeto de tipo Child que colisionó con el Dulce de nombre "SUPEROCHO"
     */
    @Override
    public void interaccionPocoComun(Child niño) {
        niño.sumarPuntos(getAtributoBasico() * 2);
        niño.sumarVidas(getAtributoBasico());
    }

    /**
     * Aplica la interacción cuando un niño colisiona con un Pico Dulce.
     * Al colisionar, se suman 50 puntos al puntaje total del niño,
     * y este se vuelve invulnerable por 5 segundos a futuras interacciones con objetos de la clase Verdura.
     *
     * @param niño Objeto de tipo Child que colisionó con el Dulce de nombre "PICODULCE"
     */
    @Override
    public void interaccionRara(Child niño) {
        niño.sumarPuntos(getAtributoBasico() * 5);
        niño.setEsInvunerable(true, 5f);
    }

}
