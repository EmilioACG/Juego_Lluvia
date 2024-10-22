package puppy.code;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public abstract class Comida {
    private Array<Rectangle> rainDropsPos;
    private Array<Integer> rainDropsType;
    private long lastDropTime;
    private Sound dropSound;
    private Music rainMusic;
}
