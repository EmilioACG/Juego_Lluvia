package puppy.code;

import com.badlogic.gdx.graphics.Texture;

public class Brocoli extends Verdura {

    //-----------------------------------------------------------------
    //   CONSTRUCTOR
    //-----------------------------------------------------------------
    public Brocoli(int posX, int posY, int width, int height) {
        super(posX,posY,width,height);
    }

    //-----------------------------------------------------------------
    //   METODOS
    //-----------------------------------------------------------------

    /**
     * Aplica la interacción cuando un niño colisiona con un Brocoli
     * Al colisionar, se restan 10 puntos de vida al objeto tipo Child
     *
     * @param niño Objeto de tipo Child que colisionó con un un objeto de tipo Brocoli
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
