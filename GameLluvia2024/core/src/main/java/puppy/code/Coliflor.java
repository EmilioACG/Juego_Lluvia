package puppy.code;

import com.badlogic.gdx.graphics.Texture;

public class Coliflor extends Verdura {

    //-----------------------------------------------------------------
    //   CONSTRUCTOR
    //-----------------------------------------------------------------
    public Coliflor(int posX, int posY, int width, int height) {
        super(posX,posY,width,height);
    }

    //-----------------------------------------------------------------
    //   METODOS
    //-----------------------------------------------------------------

    /**
     * Aplica la interacción cuando un niño colisiona con una Coliflor
     * Al colisionar, se restan 20 puntos de vida al objeto tipo Child
     * y la velocidad del objeto tipo Child disminuye a la mitad por 5 segundos.
     *
     * @param niño Objeto de tipo Child que colisionó con un objeto de tipo Coliflor
     */
    @Override
    public void interactuar(Child niño) {
        interacturarGenerico(niño,2);
        niño.setEstaRalentizado(true, 5f);
    }

    public void texturizar(Texture textura) {
        setTexture(textura);
    }
}
