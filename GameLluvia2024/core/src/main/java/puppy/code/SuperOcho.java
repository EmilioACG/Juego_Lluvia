package puppy.code;

import com.badlogic.gdx.graphics.Texture;

public class SuperOcho extends Dulce {

    //-----------------------------------------------------------------
    //   CONSTRUCTOR
    //-----------------------------------------------------------------
    public SuperOcho(int posX, int posY, int width, int height) {
        super(posX,posY,width,height);
    }

    //-----------------------------------------------------------------
    //   METODOS
    //-----------------------------------------------------------------

    /**
     * Aplica la interacción cuando un niño colisiona con un SuperOcho.
     * Al colisionar, se suman 20 puntos al puntaje total del niño,
     * y 10 puntos vida actual.
     *
     * @param niño Objeto de tipo Child que colisionó con el objeto SuperOcho
     */
    @Override
    public void interactuar(Child niño) {
        niño.sumarVidas(getAtributoBasico());
    }

    public void texturizar(Texture textura) {
        setTexture(textura);
    }

    protected int calcularAtributoDado(){
        return getAtributoBasico() * 2;
    }
}
