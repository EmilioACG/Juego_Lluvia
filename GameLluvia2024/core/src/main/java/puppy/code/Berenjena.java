package puppy.code;

import com.badlogic.gdx.graphics.Texture;

public class Berenjena extends Verdura implements Interactuable {

    //-----------------------------------------------------------------
    //   CONSTRUCTOR
    //-----------------------------------------------------------------
    public Berenjena(int posX, int posY, int width, int height) {
        super(posX,posY,width,height);
    }

    //-----------------------------------------------------------------
    //   METODOS
    //-----------------------------------------------------------------
    
    /**
     * Aplica la interacción cuando un niño colisiona con una Berenjena
     * Al colisionar, se restan 50 puntos de vida al objeto tipo Child
     * y volverá al objeto tipo Child vulnerable por 5 segundos.
     *
     * @param niño Objeto de tipo Child que colisionó con un objeto de tipo Berenjena
     */
    @Override
    public void interactuar(Child niño) {
        interacturarGenerico(niño,5);
        niño.setEstaVulnerable(true, 5f);
    }

    public void texturizar(Texture textura) {
        setTexture(textura);
    }
}
