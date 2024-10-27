package puppy.code;

public class Verdura extends Comida implements Interactuable{
    private int atributoBasico = 10;

    /**
     * Obtiene el atributo basico de verdura
     *
     * @return El atributoBasico
     */
    public int getAtributoBasico(){
        return this.atributoBasico;
    }

    //CONSTRUCTOR
    public Verdura(String nombre) {
        super(nombre);
    }


    public void verduraInteractuaConNiño(Child niño) {
        switch (getNombre()) {
            case "BROCOLI":
                interaccionComun(niño);
                break;
            case "COLIFLOR":
                interaccionPocoComun(niño);
                break;
            case "BERENJENA":
                interaccionRara(niño);
                break;
        }
    }

    /**
     * Aplica la interacción cuando un Objeto tipo Child colisiona con un "BROCOLI".
     * Al colisionar, se restan 10 puntos de vida al objeto tipo Child
     *
     * @param niño Objeto de tipo Child que colisionó con un "BROCOLI"
     */
    @Override
    public void interaccionComun(Child niño) {
        niño.dañar(getAtributoBasico());
    }

    /**
     * Aplica la interacción cuando un Objeto tipo Child colisiona con una "COLIFLOR".
     * Al colisionar, se restan 20 puntos de vida al objeto tipo Child
     * y la velocidad del objeto tipo Child disminuye a la mitad por 10 segundos.
     *
     * @param niño Objeto de tipo Child que colisionó con una "COLIFLOR"
     */
    @Override
    public void interaccionPocoComun(Child niño) {
        niño.dañar(getAtributoBasico() * 2);
        niño.setEstaRalentizado(true, 10f);

    }

    /**
     * Aplica la interacción cuando un Objeto tipo Child colisiona con una "BERENJENA".
     * Al colisionar, se restan 50 puntos de vida al objeto tipo Child
     * y volverá al objeto tipo Child vulnerable por 5 segundos.
     *
     * @param niño Objeto de tipo Child que colisionó con una "BERENJENA"
     */
    @Override
    public void interaccionRara(Child niño) {
        niño.sumarPuntos(getAtributoBasico() * 5);
        niño.setEsVulnerable(true, 5f);

    }

}
