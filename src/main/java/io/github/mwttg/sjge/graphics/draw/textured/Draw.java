package io.github.mwttg.sjge.graphics.draw.textured;

import io.github.mwttg.sjge.graphics.entity.Drawable;
import java.util.Map;
import org.lwjgl.opengl.GL40;

public class Draw {
  private final UploadUniforms uploadUniforms;
  private final int shaderProgramId;
  private final Map<String, Integer> uniformLocations;

  public Draw(final int shaderProgramId, final Map<String, Integer> uniformLocations) {
    this.shaderProgramId = shaderProgramId;
    this.uniformLocations = uniformLocations;
    this.uploadUniforms = new UploadUniforms();
  }

  void apply(final Drawable entity) {
    GL40.glBindVertexArray(entity.ids().vaoId());
    GL40.glUseProgram(shaderProgramId);
    enableVertexAttribArray();

    uploadUniforms.apply(uniformLocations, entity);
    GL40.glDrawArrays(GL40.GL_TRIANGLES, 0, entity.ids().size());

    disableVertexAttribArray();
  }

  private void enableVertexAttribArray() {
    GL40.glEnableVertexAttribArray(0); // vertices
    GL40.glEnableVertexAttribArray(1); // texture coordinates
  }

  private void disableVertexAttribArray() {
    GL40.glDisableVertexAttribArray(1); // texture coordinates
    GL40.glDisableVertexAttribArray(0); // vertices
  }
}
