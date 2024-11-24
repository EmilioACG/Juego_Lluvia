package puppy.code;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;


public class Child {
    private Rectangle niño;
    private Texture texturaDefault;
    private Texture texturaSlow;
    private Texture texturaHerido;
    private Texture texturaInvulnerable;
    private Texture texturaVulnerable;
    private Texture texturaSlowVulnerable;
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
    private EstrategiaMovimiento estrategiaMovimiento;
    private MovimientoRelentizado estrategiaMovimientoReducido;

    //-----------------------------------------------------------------
    //   CONSTRUCTOR
    //-----------------------------------------------------------------
    public Child(Texture texturaDefault, Texture texturaSlow, Texture texturaHerido, Texture texturaInvulnerable,
                 Texture texturaVulnerable, Texture texturaSlowVulnerable, Sound sonidoHerido, Sound sonidoPuntos,EstrategiaMovimiento estrategiaInicial) {
        this.texturaDefault = texturaDefault;
        this.texturaSlow = texturaSlow;
        this.texturaHerido = texturaHerido;
        this.texturaInvulnerable = texturaInvulnerable;
        this.texturaVulnerable = texturaVulnerable;
        this.texturaSlowVulnerable = texturaSlowVulnerable;
        this.sonidoHerido = sonidoHerido;
        this.sonidoPuntos = sonidoPuntos;
        this.estrategiaMovimiento = estrategiaInicial != null ? estrategiaInicial : new MovimientoNormal(velx);
        this.estrategiaMovimientoReducido = new MovimientoRelentizado(velx);
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
        if ( !(estaHerido || estaInvunerable || estaRalentizado || estaVulnerable) ) {
            batch.draw(texturaDefault, niño.x, niño.y, anchoTexture, altoTexture);
        } else if (estaVulnerable && estaRalentizado) {
            batch.draw(texturaSlowVulnerable, niño.x, niño.y,anchoTexture,altoTexture);
        } else if (estaHerido) {
            batch.draw(texturaHerido, niño.x, niño.y+ MathUtils.random(-5,5),anchoTexture,altoTexture);
            tiempoHerido--;
            if (tiempoHerido<=0) estaHerido = false;
        } else if (estaInvunerable) {
            batch.draw(texturaInvulnerable, niño.x, niño.y, anchoTexture, altoTexture);
        } else if (estaRalentizado) {
            batch.draw(texturaSlow, niño.x, niño.y, anchoTexture, altoTexture);
        } else if (estaVulnerable) {
            batch.draw(texturaVulnerable, niño.x, niño.y, anchoTexture, altoTexture);
        }


        if(gameOver)
            batch.draw(texturaHerido, niño.x, niño.y,anchoTexture,altoTexture);
    }

    public void actualizarMovimiento() {
        if(estaRalentizado)
            estrategiaMovimientoReducido.mover(niño, anchoTexture);
        else
            estrategiaMovimiento.mover(niño,anchoTexture);
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

    public void destruir() {
        sonidoPuntos.dispose();
        texturaDefault.dispose();
        sonidoHerido.dispose();
    }

    public void reiniciar() {
        niño.x = (float) (800 / 2 - 64 / 2); // Posición inicial
        niño.y = 0; 
    
        vidas = 100;
        puntaje = 0;
        puntajeMaximo = 0;
        racha = 0;
        rachaMaxima = 0;
        velx = 400;
    
        estaHerido = false;
        tiempoHerido = 0;
        estaInvunerable = false;
        tiempoBuffInvulnerable = 0;
        estaRalentizado = false;
        tiempoDebuffRalentizado = 0;
        estaVulnerable = false;
        tiempoDebuffVulnerable = 0;
    }

}
