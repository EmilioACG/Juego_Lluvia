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
    public void interacturarGenerico(Child niño, int fuerza) {
        niño.dañar(getAtributoBasico() * fuerza);
    }

    public abstract void interactuar(Child niño);

    public abstract void texturizar(Texture textura);
}
