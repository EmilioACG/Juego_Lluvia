package puppy.code;

import com.badlogic.gdx.graphics.Texture;

public class Frugele extends Dulce {

    //-----------------------------------------------------------------
    //   CONSTRUCTOR
    //-----------------------------------------------------------------
    public Frugele(int posX, int posY, int width, int height) {
        super(posX,posY,width,height);
    }

    //-----------------------------------------------------------------
    //   METODOS
    //-----------------------------------------------------------------

    /**
     * Aplica la interacción cuando un niño colisiona con un Frugele.
     * Al colisionar, se suman 10 puntos al puntaje total del niño,
     *
     * @param niño Objeto de tipo Child que colisionó con el objeto Dulce
     */
    @Override
    public void interactuar(Child niño) {}

    public void texturizar(Texture textura) {
        setTexture(textura);
    }

    protected int calcularAtributoDado(){
        return getAtributoBasico();
    }
}
