package puppy.code;



import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;



public class GameLluvia extends ApplicationAdapter {
       private OrthographicCamera camera;
	   private SpriteBatch batch;
	   private BitmapFont font;

	   private Child tarro;
	   private Lluvia lluvia;
	@Override
	public void create () {
		 font = new BitmapFont(); // use libGDX's default Arial font

		  // load the images for the droplet and the bucket, 64x64 pixels each
		  Sound hurtSound = Gdx.audio.newSound(Gdx.files.internal("hurt.ogg"));
		  tarro = new Child(new Texture(Gdx.files.internal("bucket.png")),hurtSound);

	      // load the drop sound effect and the rain background "music"
          Texture gota = new Texture(Gdx.files.internal("drop.png"));
          Texture gotaMala = new Texture(Gdx.files.internal("dropBad.png"));

          Texture frutilla = new Texture(Gdx.files.internal("frutilla.png"));
          Texture naranja = new Texture(Gdx.files.internal("naranja.png"));
          Texture platano = new Texture(Gdx.files.internal("platano.png"));
          Texture frugele = new Texture(Gdx.files.internal("frugele.png"));
          Texture superocho = new Texture(Gdx.files.internal("superocho.png"));
          Texture picodulce = new Texture(Gdx.files.internal("picodulce.png"));

          Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));

	      Music rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
          lluvia = new Lluvia(gota, gotaMala,frutilla,naranja,platano,frugele,superocho,picodulce, dropSound, rainMusic);

	      // camera
	      camera = new OrthographicCamera();
	      camera.setToOrtho(false, 800, 480);
	      batch = new SpriteBatch();
	      // creacion del tarro
	      tarro.crear();

	      // creacion de la lluvia
	      lluvia.crear();
	}



	@Override
	public void render () {
		//limpia la pantalla con color azul obscuro.
		ScreenUtils.clear(0, 0, 0.2f, 1);
		//actualizar matrices de la cámara
		camera.update();
		//actualizar
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		//dibujar textos
		font.draw(batch, "Gotas totales: " + tarro.getPuntos(), 5, 475);
		font.draw(batch, "Vidas : " + tarro.getVidas(), 720, 475);

		if (!tarro.estaHerido()) {
			// movimiento del tarro desde teclado
	        tarro.actualizarMovimiento();
			// caida de la lluvia
	        lluvia.actualizarMovimiento(tarro);
		}

		tarro.dibujar(batch);
		lluvia.actualizarDibujoLluvia(batch);

		batch.end();

	}

	@Override
	public void dispose () {
	      tarro.destruir();
          lluvia.destruir();
	      batch.dispose();
	      font.dispose();
	}
}

