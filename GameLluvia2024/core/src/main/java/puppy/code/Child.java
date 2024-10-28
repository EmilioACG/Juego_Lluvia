package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;


public class Child implements Movimiento {
    private Rectangle niño;
    private Texture textureNiñoDefault;
    private Texture textureNiñoHerido;
    private Texture textureNiñoInvulnerable;
    private Sound sonidoHerido;
    private Sound sonidoPuntos;
    private int vidas = 100;
    private int puntaje = 0;
    private int puntajeMaximo = 0;
    private int racha;
    private int rachaMaxima = 0;
    private int velx = 400;
    private boolean estaHerido = false;
    private int tiempoHerido;
    private int tiempoHeridoMax = 50;
    private boolean estaInvunerable = false;
    private float tiempoBuffInvulnerable;
    private boolean estaRalentizado = false;
    private float tiempoDebuffRalentizado;
    private boolean estaVulnerable = false;
    private float tiempoDebuffVulnerable;
    private float altoTexture = 110f;
    private float anchoTexture = 90f;

    //-----------------------------------------------------------------
    //   CONSTRUCTOR
    //-----------------------------------------------------------------
    public Child(Texture tex,Texture texHerido,Texture childImagenInvunerable, Sound ss, Sound sonidoPuntos) {
        textureNiñoDefault = tex;
        textureNiñoHerido = texHerido;
        this.textureNiñoInvulnerable = childImagenInvunerable;
        sonidoHerido = ss;
        this.sonidoPuntos = sonidoPuntos;
    }

    //-----------------------------------------------------------------
    //   GETTER's
    //-----------------------------------------------------------------
    public Rectangle getNiño() {
        return niño;
    }
    public int getVidas() {
        return vidas;
    }
    public int getPuntaje() {
        return puntaje;
    }
    public int getPuntajeMaximo() {
        return puntajeMaximo;
    }
    public int getRacha(){
        return racha;
    }
    public int getRachaMaxima(){
        return rachaMaxima;
    }
    public int getVelx() {
        return velx;
    }
    public boolean getEstaHerido() {
        return estaHerido;
    }
    public int getTiempoHerido() {
        return tiempoHerido;
    }
    public int getTiempoHeridoMax() {
        return tiempoHeridoMax;
    }
    public boolean getEstaInvunerable() {
        return estaInvunerable;
    }
    public float getTiempoBuffInvulnerable() {
        return tiempoBuffInvulnerable;
    }
    public boolean getEstaRalentizado() {
        return estaRalentizado;
    }
    public float getTiempoDebuffRalentizado() {
        return tiempoDebuffRalentizado;
    }
    public boolean getEstaVulnerable() {
        return estaVulnerable;
    }
    public float getTiempoDebuffVulnerable() {
        return tiempoDebuffVulnerable;
    }
    public float getAltoTexture() {
        return altoTexture;
    }
    public float getAnchoTexture() {
        return anchoTexture;
    }

    //-----------------------------------------------------------------
    //   SETTER's
    //-----------------------------------------------------------------
    public void setNiño(Rectangle niño) {
        this.niño = niño;
    }
    public void setVidas(int vidas){
        this.vidas = vidas;
    }
    public void setPuntaje(int puntaje){
        this.puntaje = puntaje;
    }
    public void setPuntajeMaximo(int puntajeMaximo) {
        this.puntajeMaximo = puntajeMaximo;
    }
    public void setRacha(int racha) {
        this.racha = racha;
    }
    public void setRachaMaxima(int rachaMaxima) {
        this.rachaMaxima = rachaMaxima;
    }
    public void setVelx(int velx) {
        this.velx = velx;
    }
    public void setEstaHerido(boolean estaHerido) {
        this.estaHerido = estaHerido;
    }
    public void setTiempoHerido(int tiempoHerido) {
        this.tiempoHerido = tiempoHerido;
    }
    public void setTiempoHeridoMax(int tiempoHeridoMax) {
        this.tiempoHeridoMax = tiempoHeridoMax;
    }
    public void setEstaInvunerable(boolean esInvunerable, float tiempoInvunerable) {
        this.estaInvunerable = esInvunerable;
        this.tiempoBuffInvulnerable = tiempoInvunerable;

        this.estaRalentizado = false;
        this.estaVulnerable = false;
        this.tiempoDebuffRalentizado = 0;
        this.velx = 400;
        this.tiempoDebuffVulnerable = 0;
    }
    public void setTiempoBuffInvulnerable(float tiempoBuffInvulnerable) {
        this.tiempoBuffInvulnerable = tiempoBuffInvulnerable;
    }
    public void setEstaRalentizado(boolean estaRalentizado, float tiempoDebuff) {
        if(!estaInvunerable) {
            this.estaRalentizado = estaRalentizado;
            velx = 200;
            this.tiempoDebuffRalentizado = tiempoDebuff;
        }

    }
    public void setTiempoDebuffRalentizado(float tiempoDebuffRalentizado) {
        this.tiempoDebuffRalentizado = tiempoDebuffRalentizado;
    }
    public void setEstaVulnerable(boolean estaVulnerable, float tiempoDebuff) {
        if(!estaInvunerable) {
            this.estaVulnerable = estaVulnerable;
            this.tiempoDebuffVulnerable = tiempoDebuff;
        }
    }
    public void setTiempoDebuffVulnerable(float tiempoDebuffVulnerable) {
        this.tiempoDebuffVulnerable = tiempoDebuffVulnerable;
    }
    public void setAltoTexture(float altoTexture) {
        this.altoTexture = altoTexture;
    }
    public void setAnchoTexture(float anchoTexture) {
        this.anchoTexture = anchoTexture;
    }

    //-----------------------------------------------------------------
    //   METODOS
    //-----------------------------------------------------------------
    public void crear() {
        niño = new Rectangle();
        niño.x = (float) (800 / 2 - 64 / 2);
        niño.y = 0;
        niño.width = altoTexture;
        niño.height = anchoTexture;
    }

    public void dibujar(SpriteBatch batch, boolean gameOver) {
        if (!estaHerido)
            batch.draw(textureNiñoDefault, niño.x, niño.y,anchoTexture,altoTexture);
        else if (estaInvunerable) {
            batch.draw(textureNiñoInvulnerable, niño.x, niño.y,anchoTexture,altoTexture);
        } else {
            batch.draw(textureNiñoHerido, niño.x, niño.y+ MathUtils.random(-5,5),anchoTexture,altoTexture);
            tiempoHerido--;
            if (tiempoHerido<=0) estaHerido = false;
        }
        if(gameOver)
            batch.draw(textureNiñoHerido, niño.x, niño.y,anchoTexture,altoTexture);
    }

    @Override
    public void actualizarMovimiento() {
        //movimiento desde teclado
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) niño.x -= velx * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) niño.x += velx * Gdx.graphics.getDeltaTime();
        // que no se salga de los bordes izq y der
        if(niño.x < 0) niño.x = 0;
        if(niño.x > 800 - anchoTexture) niño.x = 800 - anchoTexture;
    }

    public void actualizadorEstados(float tiempoJuego) {
        if (estaInvunerable) {
            tiempoBuffInvulnerable -= tiempoJuego;
            if (tiempoBuffInvulnerable <= 0)
                estaInvunerable = false;
        } else if (estaRalentizado) {
            tiempoDebuffRalentizado -= tiempoJuego;
            if (tiempoDebuffRalentizado <= 0) {
                estaRalentizado = false;
                velx = 400;
            }
        } else if (estaVulnerable) {
            tiempoDebuffVulnerable -= tiempoJuego;
            if (tiempoDebuffVulnerable <= 0)
                estaVulnerable = false;
        }
    }


    public void sumarPuntos(int pp) {
        sonidoPuntos.play();
        racha ++;
        puntaje += pp * racha;
        if(racha > rachaMaxima)
            rachaMaxima = racha;
        if(puntaje > puntajeMaximo)
            puntajeMaximo = puntaje;
    }

    public void sumarVidas(int pv){
        vidas += pv;
    }

    public void dañar(int ptsDaño) {
        if(!estaInvunerable) {
            if(estaVulnerable) vidas -= ptsDaño * 2;
            else vidas -= ptsDaño;
            estaHerido = true;
            tiempoHerido=tiempoHeridoMax;
            sonidoHerido.play();
            racha = 0;
        }
    }

    @Override
    public void actualizarMovimiento(Child niño){}

    public void destruir() {
        sonidoPuntos.dispose();
        textureNiñoDefault.dispose();
        sonidoHerido.dispose();
    }

}
