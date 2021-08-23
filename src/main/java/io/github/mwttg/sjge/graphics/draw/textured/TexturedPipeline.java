package io.github.mwttg.sjge.graphics.draw.textured;

import io.github.mwttg.sjge.graphics.entity.Drawable;
import io.github.mwttg.sjge.utilities.ShaderUtilities;
import java.util.ArrayList;
import java.util.List;

public class TexturedPipeline {

  private final Draw draw;
  private final List<Drawable> entities;

  public TexturedPipeline() {
    entities = new ArrayList<>();
    final var shaderProgramId = ShaderUtilities.createDefaultShader();
    final var uploadUniforms = new UploadUniforms();
    final var uniformLocations = uploadUniforms.initializeLocations(shaderProgramId);

    this.draw = new Draw(shaderProgramId, uniformLocations);
  }

  public void addEntity(final Drawable entity) {
    entities.add(entity);
  }

  public boolean removeEntity(final Drawable entity) {
    return entities.remove(entity);
  }

  public void draw() {
    entities.forEach(draw::apply);
  }
}
