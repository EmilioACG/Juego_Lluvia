package puppy.code;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class ChildBuilder {
    private Texture texturaDefault;
    private Texture texturaSlow;
    private Texture texturaHerido;
    private Texture texturaInvulnerable;
    private Texture texturaVulnerable;
    private Texture texturaSlowVulnerable;
    private Sound sonidoHerido;
    private Sound sonidoPuntos;
    private EstrategiaMovimiento estrategiaMovimiento;

    // Métodos del Builder
    public ChildBuilder setTexturaDefault(Texture texturaDefault) {
        this.texturaDefault = texturaDefault;
        return this;
    }

    public ChildBuilder setTexturaSlow(Texture texturaSlow) {
        this.texturaSlow = texturaSlow;
        return this;
    }

    public ChildBuilder setTexturaHerido(Texture texturaHerido) {
        this.texturaHerido = texturaHerido;
        return this;
    }

    public ChildBuilder setTexturaInvulnerable(Texture texturaInvulnerable) {
        this.texturaInvulnerable = texturaInvulnerable;
        return this;
    }

    public ChildBuilder setTexturaVulnerable(Texture texturaVulnerable) {
        this.texturaVulnerable = texturaVulnerable;
        return this;
    }

    public ChildBuilder setTexturaSlowVulnerable(Texture texturaSlowVulnerable) {
        this.texturaSlowVulnerable = texturaSlowVulnerable;
        return this;
    }

    public ChildBuilder setSonidoHerido(Sound sonidoHerido) {
        this.sonidoHerido = sonidoHerido;
        return this;
    }

    public ChildBuilder setSonidoPuntos(Sound sonidoPuntos) {
        this.sonidoPuntos = sonidoPuntos;
        return this;
    }

    public ChildBuilder setEstrategiaMovimiento(EstrategiaMovimiento estrategiaMovimiento) {
        this.estrategiaMovimiento = estrategiaMovimiento;
        return this;
    }

    // Método para construir el objeto Child
    public Child build() {
        if (texturaDefault == null) {
            throw new IllegalArgumentException("La textura default es obligatoria.");
        }
        return new Child(
            texturaDefault,
            texturaSlow,
            texturaHerido,
            texturaInvulnerable,
            texturaVulnerable,
            texturaSlowVulnerable,
            sonidoHerido,
            sonidoPuntos,
            estrategiaMovimiento
        );
    }
}
