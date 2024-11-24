package puppy.code;

import com.badlogic.gdx.graphics.Texture;

public class PicoDulce extends Dulce {

    //-----------------------------------------------------------------
    //   CONSTRUCTOR
    //-----------------------------------------------------------------
    public PicoDulce(int posX, int posY, int width, int height) {
        super(posX,posY,width,height);
    }

    //-----------------------------------------------------------------
    //   METODOS
    //-----------------------------------------------------------------

    /**
     * Aplica la interacción cuando un niño colisiona con un PicoDulce.
     * Al colisionar, se suman 50 puntos al puntaje total del niño,
     * y este se vuelve invulnerable por 5 segundos a futuras interacciones con objetos de la clase Verdura.
     *
     * @param niño Objeto de tipo Child que colisionó con el objeto PicoDulce
     */
    @Override
    public void interactuar(Child niño) {
        niño.setEstaInvunerable(true, 5f);
    }

    public void texturizar(Texture textura) {
        setTexture(textura);
    }

    protected int calcularAtributoDado(){
        return getAtributoBasico() * 5;
    }

}
