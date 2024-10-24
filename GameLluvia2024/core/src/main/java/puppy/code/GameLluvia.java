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
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;



public class GameLluvia extends ApplicationAdapter {
       private OrthographicCamera camera;
	   private SpriteBatch batch;
	   private FreeTypeFontGenerator generadorFont;
	   private  FreeTypeFontParameter parametroFont;
	   private BitmapFont font;

	   private Child child;
	   private Lluvia lluvia;
	   private Texture fondoTexture;
	   private Texture childTexture;
	   private Texture childTextureHerido;


	@Override
	public void create () {
		  generadorFont = new FreeTypeFontGenerator(Gdx.files.internal("youmurdererbb_reg.ttf"));
		  parametroFont = new FreeTypeFontParameter();
		  parametroFont.size = 32;
		  font = generadorFont.generateFont(parametroFont);

		  // load the images for the droplet and the bucket, 64x64 pixels each
		  Sound hurtSound = Gdx.audio.newSound(Gdx.files.internal("hurt.ogg"));
		  // Se cran las textura de child y la clase child
		  childTexture = new Texture(Gdx.files.internal("imagenChild.png"));
		  childTextureHerido = new Texture(Gdx.files.internal("imagenChildLlorando.png"));
		  child = new Child(childTexture,childTextureHerido,hurtSound);

	      // load the drop sound effect and the rain background "music"
          Texture gota = new Texture(Gdx.files.internal("drop.png"));
          Texture gotaMala = new Texture(Gdx.files.internal("dropBad.png"));

          Texture brocoli = new Texture(Gdx.files.internal("brocoli.png"));
          Texture berenjena = new Texture(Gdx.files.internal("berenjena.png"));
          Texture coliflor = new Texture(Gdx.files.internal("coliflor.png"));
          Texture frugele = new Texture(Gdx.files.internal("frugele.png"));
          Texture superocho = new Texture(Gdx.files.internal("superocho.png"));
          Texture picodulce = new Texture(Gdx.files.internal("picodulce.png"));

          Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
          Music rainMusic = Gdx.audio.newMusic(Gdx.files.internal("musicaHalloween.mp3"));

          lluvia = new Lluvia(gota, gotaMala,brocoli,berenjena,coliflor,frugele,superocho,picodulce, dropSound, rainMusic);

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
		font.draw(batch, "Puntos totales: " + child.getPuntos(), 5, 475);
		font.draw(batch, "Vidas : " + child.getVidas(), 690, 475);
		font.draw(batch, "Numero de racha : " + child.getRacha(),590,440 );


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

