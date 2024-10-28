package puppy.code;

import com.badlogic.gdx.graphics.Texture;

public abstract class Dulce extends Comida {

    //-----------------------------------------------------------------
    //   CONSTRUCTOR
    //-----------------------------------------------------------------
    public Dulce(int posX, int posY, int width, int height) {
        super(posX,posY,width,height);
    }

    //-----------------------------------------------------------------
    //   METODOS
    //-----------------------------------------------------------------
    public void interactuarGenerico(Child niño, int fuerza) {
        niño.sumarPuntos(getAtributoBasico() * fuerza);
    }

    public abstract void interactuar(Child niño);

    public abstract void texturizar(Texture textura);

}
