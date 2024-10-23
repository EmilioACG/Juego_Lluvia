package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;


public class Child {
	   private Rectangle child;
	   private Texture childImage;
	   private Sound sonidoHerido;
	   private int vidas = 3;
	   private int puntos = 0;
	   private int velx = 400;
	   private boolean herido = false;
	   private int tiempoHeridoMax=50;
	   private int tiempoHerido;
	   private float altoTexture = 110;
	   private float anchoTexture = 90;

	   public Child(Texture tex, Sound ss) {
		   childImage = tex;
		   sonidoHerido = ss;
	   }

		public int getVidas() {
			return vidas;
		}

		public int getPuntos() {
			return puntos;
		}
		public Rectangle getArea() {
			return child;
		}
		public void sumarPuntos(int pp) {
			puntos+=pp;
		}


	   public void crear() {

		      child = new Rectangle();
		      child.x = 800 / 2 - 64 / 2;
		      child.y = 0;
		      child.width = altoTexture;
		      child.height = anchoTexture;
	   }
	   public void dañar() {
		  vidas--;
		  herido = true;
		  tiempoHerido=tiempoHeridoMax;
		  sonidoHerido.play();
	   }
	   public void dibujar(SpriteBatch batch) {
		 if (!herido)
		   batch.draw(childImage, child.x, child.y,anchoTexture,altoTexture);
		 else {

		   batch.draw(childImage, child.x, child.y+ MathUtils.random(-5,5),anchoTexture,altoTexture);
		   tiempoHerido--;
		   if (tiempoHerido<=0) herido = false;
		 }
	   }


	   public void actualizarMovimiento() {
		   // movimiento desde mouse/touch
		   /*if(Gdx.input.isTouched()) {
			      Vector3 touchPos = new Vector3();
			      touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			      camera.unproject(touchPos);
			      bucket.x = touchPos.x - 64 / 2;
			}*/
		   //movimiento desde teclado
		   if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) child.x -= velx * Gdx.graphics.getDeltaTime();
		   if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) child.x += velx * Gdx.graphics.getDeltaTime();
		   // que no se salga de los bordes izq y der
		   if(child.x < 0) child.x = 0;
		   if(child.x > 800 - anchoTexture) child.x = 800 - anchoTexture;
	   }


	public void destruir() {
			childImage.dispose();
	   }

   public boolean estaHerido() {
	   return herido;
   }

}
