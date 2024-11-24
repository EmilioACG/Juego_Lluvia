package puppy.code;

import com.badlogic.gdx.graphics.Texture;

public abstract class Verdura extends Comida{

    //-----------------------------------------------------------------
    //   CONSTRUCTOR
    //-----------------------------------------------------------------
    public Verdura(int posX, int posY, int width, int height) {
        super(posX,posY,width,height);
    }

    //-----------------------------------------------------------------
    //   METODOS
    //-----------------------------------------------------------------
    protected void interactuarGenerico(Child niño, int stat) {
        niño.dañar(stat);
    }

    public abstract void interactuar(Child niño);

    public abstract void texturizar(Texture textura);

    protected abstract int calcularAtributoDado();
}
