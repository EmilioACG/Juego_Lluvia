package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Lluvia implements Movimiento {
    private Array<Comida> tiposLluviaCaida;
    private long lastDropTime;
    private Texture brocoli, berenjena, coliflor, frugele, superocho, picodulce;
    private Music rainMusic;

    //-----------------------------------------------------------------
    //   CONSTRUCTOR
    //-----------------------------------------------------------------
	public Lluvia(Texture brocoli, Texture berenjena, Texture coliflor,
                  Texture frugele, Texture superocho, Texture picodulce, Music rainMusic) {
        this.brocoli = brocoli;
        this.berenjena = berenjena;
        this.coliflor = coliflor;
        this.frugele = frugele;
        this.superocho = superocho;
        this.picodulce = picodulce;
        this.rainMusic = rainMusic;
	}

    //-----------------------------------------------------------------
    //   GETTER's
    //-----------------------------------------------------------------
    public long getLastDropTime() {
        return lastDropTime;
    }

    //-----------------------------------------------------------------
    //   SETTER's
    //-----------------------------------------------------------------
    public void setLastDropTime(long lastDropTime) {
        this.lastDropTime = lastDropTime;
    }

    //-----------------------------------------------------------------
    //   METODOS
    //-----------------------------------------------------------------
    public void crear() {
        tiposLluviaCaida = new Array<>();
        //crear la primera gota de la lluvia de comida
		crearGotaDeLluvia();
        // iniciar la reproducción de la música de fondo inmediatamente
        rainMusic.setLooping(true);
        rainMusic.play();
	}

	private void crearGotaDeLluvia() {
        int x = MathUtils.random(0, 800-64);
        int y = 480, ancho = 64, alto = 64;

        int dropComidaRandom = MathUtils.random(1,100);

        if (dropComidaRandom == 1) {
            tiposLluviaCaida.add(new Berenjena(x, y, ancho, alto));
            ((Berenjena) tiposLluviaCaida.peek()).texturizar(berenjena);
        } else if (dropComidaRandom <= 5) {
            tiposLluviaCaida.add(new Coliflor(x, y, ancho, alto));
            ((Coliflor) tiposLluviaCaida.peek()).texturizar(coliflor);
        } else if (dropComidaRandom <= 20) {
            tiposLluviaCaida.add(new Brocoli(x, y, ancho, alto));
            ((Brocoli) tiposLluviaCaida.peek()).texturizar(brocoli);
        } else if (dropComidaRandom == 21) {
            tiposLluviaCaida.add(new PicoDulce(x, y, ancho, alto));
            ((PicoDulce) tiposLluviaCaida.peek()).texturizar(picodulce);
        } else if (dropComidaRandom <= 30) {
            tiposLluviaCaida.add(new SuperOcho(x, y, ancho, alto));
            ((SuperOcho) tiposLluviaCaida.peek()).texturizar(superocho);
        } else {
            tiposLluviaCaida.add(new Frugele(x, y, ancho, alto));
            ((Frugele) tiposLluviaCaida.peek()).texturizar(frugele);
        }

        lastDropTime = TimeUtils.nanoTime();
    }

    public void actualizarDibujoLluvia(SpriteBatch batch) {
        for (Comida comida : tiposLluviaCaida) {
            batch.draw(comida.getTexture(), comida.getPosicionX(), comida.getPosicionY());
        }
    }

    @Override
    public void actualizarMovimiento(Child niño) {
        // generar gotas de lluvia
	    if(TimeUtils.nanoTime() - lastDropTime > 100000000) crearGotaDeLluvia();

	    // revisar si las gotas cayeron al suelo o chocaron con el niño
        for (int i = 0; i < tiposLluviaCaida.size; i++) {
            Rectangle raindrop = tiposLluviaCaida.get(i).getComida();
            raindrop.y -= 300 * Gdx.graphics.getDeltaTime();

            //cae al suelo y se elimina
            if(raindrop.y + 64 < 0) {
                tiposLluviaCaida.removeIndex(i);
            }

            //comida colisiona con niño
            if(raindrop.overlaps(niño.getNiño())) {
                tiposLluviaCaida.get(i).interactuar(niño);
                tiposLluviaCaida.removeIndex(i);
            }
        }

    }

    public void reiniciar(){
        tiposLluviaCaida.clear();
    }

    public void destruir() {
	      rainMusic.dispose();
    }

    @Override
    public void actualizarMovimiento(){}
}
