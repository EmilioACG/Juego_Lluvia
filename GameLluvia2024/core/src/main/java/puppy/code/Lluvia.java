package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Lluvia {
	private Array<Rectangle> rainDropsPos;

	//private Array<Integer> rainDropsType;
    private Array<Comida> tiposLluviaCaida;

    private long lastDropTime;
    private Texture brocoli;
    private Texture berenjena;
    private Texture coliflor;
    private Texture frugele;
    private Texture superocho;
    private Texture picodulce;
    private Sound dropSound;
    private Music rainMusic;

	public Lluvia(Texture brocoli, Texture berenjena, Texture coliflor,
                  Texture frugele, Texture superocho, Texture picodulce, Sound ss, Music mm) {
		rainMusic = mm;
		dropSound = ss;
        this.brocoli = brocoli;
        this.berenjena = berenjena;
        this.coliflor = coliflor;
        this.frugele = frugele;
        this.superocho = superocho;
        this.picodulce = picodulce;
	}

	public void crear() {
		rainDropsPos = new Array<Rectangle>();

		//rainDropsType = new Array<Integer>();
        tiposLluviaCaida = new Array<Comida>();

		crearGotaDeLluvia();
	      // start the playback of the background music immediately
	      rainMusic.setLooping(true);
	      rainMusic.play();
	}

	private void crearGotaDeLluvia() {
        Rectangle raindrop = new Rectangle();
        raindrop.x = MathUtils.random(0, 800-64);
        raindrop.y = 480;
        raindrop.width = 64;
        raindrop.height = 64;
        rainDropsPos.add(raindrop);
        // ver el tipo de gota
        int dropComidaRandom = MathUtils.random(1,100);
        if (dropComidaRandom <= 3)
            tiposLluviaCaida.add(new Verdura("BERENJENA"));
        else if (dropComidaRandom <= 10)
            tiposLluviaCaida.add(new Verdura("COLIFLOR"));
        else if (dropComidaRandom <= 25)
            tiposLluviaCaida.add(new Verdura("BROCOLI"));
        else if (dropComidaRandom <= 30)
            tiposLluviaCaida.add(new Dulce("PICODULCE"));
        else if (dropComidaRandom <= 40)
            tiposLluviaCaida.add(new Dulce("SUPEROCHO"));
        else
            tiposLluviaCaida.add(new Dulce("FRUGELE"));

        lastDropTime = TimeUtils.nanoTime();
    }

   public void actualizarMovimiento(Child niño) {
	   // generar gotas de lluvia
	   if(TimeUtils.nanoTime() - lastDropTime > 100000000) crearGotaDeLluvia();


	   // revisar si las gotas cayeron al suelo o chocaron con el niño
	   for (int i=0; i < rainDropsPos.size; i++ ) {
		  Rectangle raindrop = rainDropsPos.get(i);
	      raindrop.y -= 300 * Gdx.graphics.getDeltaTime();
	      //cae al suelo y se elimina
	      if(raindrop.y + 64 < 0) {
              rainDropsPos.removeIndex(i);
              tiposLluviaCaida.removeIndex(i);
	      }

          //choca con el niño
	      if(raindrop.overlaps(niño.getArea())) { //la gota choca con el niño
            if(tiposLluviaCaida.get(i) instanceof Verdura) { // gota dañina
	    	  niño.dañar();

              rainDropsPos.removeIndex(i);
              tiposLluviaCaida.removeIndex(i);
            }else { // gota a recolectar
	    	  niño.sumarPuntos(10);
	          dropSound.play();

              rainDropsPos.removeIndex(i);
              tiposLluviaCaida.removeIndex(i);
            }
          }
	   }
   }

   public void actualizarDibujoLluvia(SpriteBatch batch) {

	  for (int i=0; i < rainDropsPos.size; i++ ) {
		  Rectangle raindrop = rainDropsPos.get(i);
		  if(tiposLluviaCaida.get(i) instanceof Verdura) { // comida dañina
              switch (tiposLluviaCaida.get(i).getNombre()) {
                  case "COLIFLOR":
                      batch.draw(coliflor, raindrop.x, raindrop.y);
                      break;
                  case "BERENJENA":
                      batch.draw(berenjena, raindrop.x, raindrop.y);
                      break;
                  case "BROCOLI":
                      batch.draw(brocoli, raindrop.x, raindrop.y);
                      break;
              }
          }
		  else {
              switch (tiposLluviaCaida.get(i).getNombre()) {
                  case "PICODULCE":
                      batch.draw(picodulce, raindrop.x, raindrop.y);
                      break;
                  case "SUPEROCHO":
                      batch.draw(superocho, raindrop.x, raindrop.y);
                      break;
                  case "FRUGELE":
                      batch.draw(frugele, raindrop.x, raindrop.y);
                      break;
              }
          }

      }
   }

   public void reiniciar(){
		rainDropsPos.clear(); 
		tiposLluviaCaida.clear();
   }

   public void destruir() {
	      dropSound.dispose();
	      rainMusic.dispose();
   }

}
