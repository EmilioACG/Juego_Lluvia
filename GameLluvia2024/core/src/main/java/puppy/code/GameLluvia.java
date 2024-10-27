package puppy.code;



import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
	   private boolean gameOver;

	   private Child child;
	   private int rachaMaxima = 0;
	   private int puntuacionMaxima = 0;
	   private Lluvia lluvia;
	   private Texture fondoTexture;
	   private Texture childTexture;
	   private Texture childTextureHerido;
       private Texture childTextureInvunerable;


	@Override
	public void create () {
		  // Se selecciona la fuente que se ocupara
		  generadorFont = new FreeTypeFontGenerator(Gdx.files.internal("youmurdererbb_reg.ttf"));
		  parametroFont = new FreeTypeFontParameter();
		  parametroFont.size = 32;
		  font = generadorFont.generateFont(parametroFont);
		  gameOver = false;

		  // load the images for the droplet and the bucket, 64x64 pixels each
		  Sound hurtSound = Gdx.audio.newSound(Gdx.files.internal("hurt.ogg"));
		  // Se cran las textura de child y la clase child
		  childTexture = new Texture(Gdx.files.internal("imagenChild.png"));
		  childTextureHerido = new Texture(Gdx.files.internal("imagenChildLlorando.png"));
          childTextureInvunerable = new Texture(Gdx.files.internal("imagenChildInvunerable.png"));
		  child = new Child(childTexture,childTextureHerido,childTextureInvunerable,hurtSound);

	      // load the drop sound effect and the rain background "music"

          Texture brocoli = new Texture(Gdx.files.internal("brocoli.png"));
          Texture berenjena = new Texture(Gdx.files.internal("berenjena.png"));
          Texture coliflor = new Texture(Gdx.files.internal("coliflor.png"));
          Texture frugele = new Texture(Gdx.files.internal("frugele.png"));
          Texture superocho = new Texture(Gdx.files.internal("superocho.png"));
          Texture picodulce = new Texture(Gdx.files.internal("picodulce.png"));

          Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
          Music rainMusic = Gdx.audio.newMusic(Gdx.files.internal("musicaHalloween.mp3"));

          lluvia = new Lluvia(brocoli,berenjena,coliflor,frugele,superocho,picodulce, dropSound, rainMusic);

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


		if (!gameOver) {
            //dibujar textos
			font.draw(batch, "Puntos totales: " + child.getPuntos(), 5, 475);
			font.draw(batch, "Vidas : " + child.getVidas(), 690, 475);
			font.draw(batch, "Numero de racha : " + child.getRacha(),590,440 );
			if(child.getVidas() <= 0)
				gameOver = true;

			if (!child.estaHerido()) {
				// movimiento del tarro desde teclado
	        	child.actualizarMovimiento();
				// caida de la lluvia
	        	lluvia.actualizarMovimiento(child);
			}
		}

		else {
			int auxPuntaje = child.puntajeMaximo();
			int auxRacha = child.getRachaMaxima();
			if(auxPuntaje > puntuacionMaxima)
				puntuacionMaxima = auxPuntaje;
			if(auxRacha > rachaMaxima)
				rachaMaxima = auxRacha;
			lluvia.reiniciar(); // Elimina la lluvia
			font.getData().setScale(2); // Aumenta la escala del texto
			// Muestra los mensajes de GAME OVER, reiniciar, puntaje maximo y racha maxima
            font.draw(batch, "GAME OVER", Gdx.graphics.getWidth() / 2 - 30, Gdx.graphics.getHeight() / 2 + 40);
            font.draw(batch, "Presiona R para reiniciar", Gdx.graphics.getWidth() / 2 - 160, Gdx.graphics.getHeight() / 2 - 10);
			font.getData().setScale(1);  // Vuelve la escala del texto a la normalidad
			font.draw(batch, "La puntuacion maxima es : " + puntuacionMaxima, Gdx.graphics.getWidth() / 2 - 40, Gdx.graphics.getHeight() / 2 - 70);
			font.draw(batch, "La racha maxima es : " + rachaMaxima, Gdx.graphics.getWidth() / 2 - 40, Gdx.graphics.getHeight() / 2 - 110);

		}

        float temporizador = Gdx.graphics.getDeltaTime();
        child.actualizadorEstados(temporizador);

		child.dibujar(batch,gameOver);
		lluvia.actualizarDibujoLluvia(batch);

		batch.end();
		if (gameOver && Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            reiniciarJuego();
        }
	}
	public void reiniciarJuego(){
		child.setVidas(100);
    	child.setPuntos(0);
   	 	gameOver = false;
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

