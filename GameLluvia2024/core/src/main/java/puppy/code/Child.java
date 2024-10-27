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
    private Texture childImagenInvunerable;
    private Sound sonidoHerido;
    private int vidas = 100;
    private int puntos = 0;
    private int puntajeMaximo = 0;
    private int racha;
    private int rachaMaxima = 0;
    private int velx = 400;
    private boolean herido = false;
    private int tiempoHerido;
    private int tiempoHeridoMax = 50;
    private float altoTexture = 110;
    private float anchoTexture = 90;

    //Buff's y Debuff's
    private boolean buffInvulnerable = false;
    private float tiempoBuffInvulnerable;
    private boolean debuffSlow = false;
    private float tiempoDebuffSlow;
    private boolean debuffVulnerable = false;
    private float tiempoDebuffVulnerable;



    public Child(Texture tex,Texture texHerido,Texture childImagenInvunerable, Sound ss) {
        childImagen = tex;
        childImagenHerido = texHerido;
        this.childImagenInvunerable = childImagenInvunerable;
        sonidoHerido = ss;
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

    public boolean getBuffInvulnerable() {
        return buffInvulnerable;
    }

    public void setEsInvunerable(boolean esInvunerable, float tiempoInvunerable) {
        this.buffInvulnerable = esInvunerable;
        if(esInvunerable) {
            this.tiempoBuffInvulnerable = tiempoInvunerable;
        }

        this.debuffSlow = false;
        this.debuffVulnerable = false;
        this.tiempoDebuffSlow = 0;
        this.tiempoDebuffVulnerable = 0;
    }

    public void setEstaRalentizado(boolean estaRalentizado, float tiempoDebuff) {
        this.debuffSlow = estaRalentizado;
        velx = 200;
        this.tiempoDebuffSlow = tiempoDebuff;
    }

    public void setEsVulnerable(boolean estaVulnerable, float tiempoDebuff) {
        this.debuffVulnerable = estaVulnerable;
        this.tiempoDebuffVulnerable = tiempoDebuff;
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

    public void dañar(int ptsDaño) {
        if(!getBuffInvulnerable()) {
            if(debuffSlow) vidas -= ptsDaño * 2;
            else vidas -= ptsDaño;
            herido = true;
            tiempoHerido=tiempoHeridoMax;
            sonidoHerido.play();
            racha = 0;
        }
    }

    public void dibujar(SpriteBatch batch, boolean gameOver) {
        if (!herido)
            batch.draw(childImagen, child.x, child.y,anchoTexture,altoTexture);
        else if (buffInvulnerable) {
            batch.draw(childImagenInvunerable, child.x, child.y,anchoTexture,altoTexture);
        } else {
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

    public void actualizadorEstados(float tiempoJuego) {
        if (buffInvulnerable) {
            tiempoBuffInvulnerable -= tiempoJuego;
            if (tiempoBuffInvulnerable <= 0)
                buffInvulnerable = false;
        } else if (debuffSlow) {
            tiempoDebuffSlow -= tiempoJuego;
            if (tiempoDebuffSlow <= 0) {
                debuffSlow = false;
                velx = 400;
            }
        } else if (debuffVulnerable) {
            tiempoDebuffVulnerable -= tiempoJuego;
            if (tiempoDebuffVulnerable <= 0)
                debuffVulnerable = false;
        }
    }

    public void colisionaConComida(Array<Rectangle> rainDropsPos, Array<Comida> tiposLluviaCaida, int posComida, Sound dropSound) {
        if(tiposLluviaCaida.get(posComida) instanceof Verdura) { // gota dañina
            ((Verdura)tiposLluviaCaida.get(posComida)).verduraInteractuaConNiño(this);

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
