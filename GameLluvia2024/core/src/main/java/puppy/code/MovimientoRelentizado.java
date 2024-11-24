package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;


public class MovimientoRelentizado implements EstrategiaMovimiento {
    private float velocidad;

    public MovimientoRelentizado(float velocidad) {
        this.velocidad = velocidad;
    }

    @Override
    public void mover(Rectangle child, float anchoTexture) {
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) child.x -= (velocidad / 2) * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) child.x += (velocidad / 2) * Gdx.graphics.getDeltaTime();
        // que no se salga de los bordes izq y der
        if(child.x < 0) child.x = 0;
        if(child.x > 800 - anchoTexture) child.x = 800 - anchoTexture;
    }
}

