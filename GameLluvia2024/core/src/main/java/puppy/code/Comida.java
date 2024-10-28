package puppy.code;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public abstract class Comida implements Interactuable {
    private Texture textura;
    private Rectangle comida;
    private int atributoBasico = 10;

    //-----------------------------------------------------------------
    //   CONSTRUCTOR
    //-----------------------------------------------------------------
    public Comida(int posicionX, int posicionY, int ancho, int largo) {
        this.comida = new Rectangle(posicionX, posicionY, ancho, largo);
    }

    //-----------------------------------------------------------------
    //   GETTER's
    //-----------------------------------------------------------------
    public Texture getTexture() {
        return textura;
    }
    public Rectangle getComida() {
        return comida;
    }
    public float getPosicionX() {
        return comida.x;
    }
    public float getPosicionY() {
        return comida.y;
    }
    public int getAtributoBasico(){
        return this.atributoBasico;
    }

    //-----------------------------------------------------------------
    //   SETTER's
    //-----------------------------------------------------------------
    public void setTexture(Texture textura) {
        this.textura = textura;
    }
    public void setAtributoBasico(int atributoBasico) {
        this.atributoBasico = atributoBasico;
    }

    //-----------------------------------------------------------------
    //   METODOS
    //-----------------------------------------------------------------
    public abstract void interactuar(Child niño);


}
