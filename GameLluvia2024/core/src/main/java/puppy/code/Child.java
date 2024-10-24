package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;


public class Child {
    private Rectangle child;
    private Texture childImagen;
    private Texture childImagenHerido;
    private Sound sonidoHerido;
    private int vidas = 100;
    private int puntos = 0;
    private int puntajeMaximo = 0;
    private int racha;
    private int rachaMaxima = 0;
    private int velx = 400;
    private boolean herido = false;
    private int tiempoHeridoMax=50;
    private int tiempoHerido;
    private boolean esInvunerable = false;
    private float tiempoInvunerableMax = 5f;
    private float tiempoInvunerable;
    private float altoTexture = 110;
    private float anchoTexture = 90;


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
    public int getRachaMaxima(){
        return rachaMaxima;
    }
    public int puntajeMaximo(){
        return puntajeMaximo;
    }
    public Rectangle getArea() {
        return child;
    }
    public void setVidas(int vidas){
        this.vidas = vidas;
    }

    public void setPuntos(int puntos){
        this.puntos = puntos;
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
        if(racha > rachaMaxima)
            rachaMaxima = racha;
        if(puntos > puntajeMaximo)
            puntajeMaximo = puntos;
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
            vidas -= 10;
            herido = true;
            tiempoHerido=tiempoHeridoMax;
            sonidoHerido.play();
        }
    }

    public void dibujar(SpriteBatch batch, boolean gameOver) {
        if (!herido)
            batch.draw(childImagen, child.x, child.y,anchoTexture,altoTexture);
        else {
            racha = 0;
            batch.draw(childImagenHerido, child.x, child.y+ MathUtils.random(-5,5),anchoTexture,altoTexture);
            tiempoHerido--;
            if (tiempoHerido<=0) herido = false;
        }
        if(gameOver)
            batch.draw(childImagenHerido, child.x, child.y,anchoTexture,altoTexture);
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
        if(getEsInvunerable()) {
            tiempoInvunerable -= tiempoJuego;
            if (tiempoInvunerable <= 0)
                esInvunerable = false;
        }
    }

    public void colisionaConComida(Array<Rectangle> rainDropsPos, Array<Comida> tiposLluviaCaida, int posComida, Sound dropSound) {
        if(tiposLluviaCaida.get(posComida) instanceof Verdura) { // gota dañina
            dañar();

            rainDropsPos.removeIndex(posComida);
            tiposLluviaCaida.removeIndex(posComida);
        } else { // gota a recolectar
            ((Dulce)tiposLluviaCaida.get(posComida)).dulceInteractuaConNiño(this);

            dropSound.play();

            rainDropsPos.removeIndex(posComida);
            tiposLluviaCaida.removeIndex(posComida);
        }
    }
}
