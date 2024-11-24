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
    protected void interactuarGenerico(Child niño, int stat) {
        niño.sumarPuntos(stat);
    }

    public abstract void texturizar(Texture textura);

    public abstract void interactuar(Child niño);

    protected abstract int calcularAtributoDado();
}
