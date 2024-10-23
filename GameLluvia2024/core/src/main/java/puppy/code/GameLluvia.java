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

	   private Child child;
	   private Lluvia lluvia;
	   private Texture fondoTexture;
	@Override
	public void create () {
		 font = new BitmapFont(); // use libGDX's default Arial font

		  // load the images for the droplet and the bucket, 64x64 pixels each
		  Sound hurtSound = Gdx.audio.newSound(Gdx.files.internal("hurt.ogg"));
		  child = new Child(new Texture(Gdx.files.internal("imagenChild.png")),hurtSound);

	      // load the drop sound effect and the rain background "music"
          Texture gota = new Texture(Gdx.files.internal("drop.png"));
          Texture gotaMala = new Texture(Gdx.files.internal("dropBad.png"));

          Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));

	      Music rainMusic = Gdx.audio.newMusic(Gdx.files.internal("musicaHalloween.mp3"));
          lluvia = new Lluvia(gota, gotaMala, dropSound, rainMusic);
		  fondoTexture = new Texture(Gdx.files.internal("imagenDeFondo.jpg"));

	      // camera
	      camera = new OrthographicCamera();
	      camera.setToOrtho(false, 800, 480);
	      batch = new SpriteBatch();
	      // creacion del tarro
	      child.crear();

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
		//dibujar fondo
		batch.draw(fondoTexture, 0, 0, Gdx.graphics.getWidth() + 180, Gdx.graphics.getHeight());
		//dibujar textos
		font.draw(batch, "Gotas totales: " + child.getPuntos(), 5, 475);
		font.draw(batch, "Vidas : " + child.getVidas(), 720, 475);
		

		if (!child.estaHerido()) {
			// movimiento del tarro desde teclado
	        child.actualizarMovimiento();
			// caida de la lluvia
	        lluvia.actualizarMovimiento(child);
		}

		child.dibujar(batch);
		lluvia.actualizarDibujoLluvia(batch);

		batch.end();

	}

	@Override
	public void dispose () {
		  child.destruir();
          lluvia.destruir();
	      batch.dispose();
	      font.dispose();
		  fondoTexture.dispose();
	}
}

