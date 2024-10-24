package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;


public class Child {
    private Rectangle child;
	private Texture childImagen;
	private Texture childImagenHerido;
	private Sound sonidoHerido;
	private int vidas = 100;
	private int puntos = 0;
	private int racha;
	private int velx = 400;
	private boolean herido = false;
	private int tiempoHeridoMax=50;
    private float tiempoInvunerableMax = 5f;
    private float tiempoInvunerable;
	private int tiempoHerido;
	private float altoTexture = 110;
	private float anchoTexture = 90;
    private boolean esInvunerable = false;

    public Child(Texture tex,Texture texHerido, Sound ss) {
        childImagen = tex;
        childImagenHerido = texHerido;
        sonidoHerido = ss;
    }

    public float getTiempoInvunerable() {
        return tiempoInvunerable;
    }

    public void setTiempoInvunerable(float tiempoInvunerable) {
        this.tiempoInvunerable = tiempoInvunerable;
    }

    public float getTiempoInvunerableMax() {
        return tiempoInvunerableMax;
    }

    public void setTiempoInvunerableMax(float tiempoInvunerableMax) {
        this.tiempoInvunerableMax = tiempoInvunerableMax;
    }

    public int getVidas() {
        return vidas;
    }

    public int getPuntos() {
        return puntos;
    }
    public int getRacha(){
        return racha;
    }
    public Rectangle getArea() {
        return child;
    }

    public boolean getEsInvunerable() {
        return esInvunerable;
    }

    public void setEsInvunerable(boolean esInvunerable) {
        this.esInvunerable = esInvunerable;
        if(esInvunerable) {
            tiempoInvunerable = tiempoInvunerableMax;
        }
    }

    public void sumarPuntos(int pp) {
        puntos += pp;
        racha ++;
    }
    public void sumarVidas(int pv){
        vidas += pv;
    }


    public void crear() {
        child = new Rectangle();
        child.x = 800 / 2 - 64 / 2;
        child.y = 0;
        child.width = altoTexture;
        child.height = anchoTexture;
    }


    public void dañar() {
        if(!getEsInvunerable()) {
            vidas--;
            herido = true;
            tiempoHerido=tiempoHeridoMax;
            sonidoHerido.play();
        }
    }

    public void dibujar(SpriteBatch batch) {
        if (!herido)
            batch.draw(childImagen, child.x, child.y,anchoTexture,altoTexture);
        else {
            racha = 0;
            batch.draw(childImagenHerido, child.x, child.y+ MathUtils.random(-5,5),anchoTexture,altoTexture);
            tiempoHerido--;
            if (tiempoHerido<=0) herido = false;
        }
    }


    public void actualizarMovimiento() {
        //movimiento desde teclado
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) child.x -= velx * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) child.x += velx * Gdx.graphics.getDeltaTime();
        // que no se salga de los bordes izq y der
        if(child.x < 0) child.x = 0;
        if(child.x > 800 - anchoTexture) child.x = 800 - anchoTexture;
    }


	public void destruir() {
        childImagen.dispose();
    }

    public boolean estaHerido() {
	   return herido;
   }

    public void actualizarInvulnerabilidad(float tiempoJuego) {
        if(esInvunerable) {
            tiempoInvunerable -= tiempoJuego;
            if (tiempoInvunerable <= 0)
                esInvunerable = false;
        }
    }
}
