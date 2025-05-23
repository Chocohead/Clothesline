package com.jamieswhiteshirt.clothesline.client.render;

import com.jamieswhiteshirt.clothesline.client.ClotheslineClient;
import net.minecraft.client.render.*;
import net.minecraft.util.Identifier;

public class ClotheslineRenderLayers extends RenderLayer {
    public ClotheslineRenderLayers(String name, VertexFormat vertexFormat, VertexFormat.DrawMode drawMode, int expectedBufferSize, boolean hasCrumbling, boolean translucent, Runnable startAction, Runnable endAction) {
        super(name, vertexFormat, drawMode, expectedBufferSize, hasCrumbling, translucent, startAction, endAction);
    }

    private static final VertexFormat CLOTHESLINE_VERTEX_FORMAT = VertexFormat.builder()
        .add("Position", VertexFormatElement.POSITION)
        .add("Normal", VertexFormatElement.NORMAL)
        .add("UV0", VertexFormatElement.UV_0)
        .add("UV2", VertexFormatElement.UV_2)
        .build();

    private static final Identifier CLOTHESLINE_TEXTURE = Identifier.of("clothesline", "textures/misc/clothesline.png");
    // TODO: What is a reasonable default buffer size?
    private static final RenderLayer CLOTHESLINE = RenderLayer.of("clothesline", CLOTHESLINE_VERTEX_FORMAT, VertexFormat.DrawMode.QUADS, 256, RenderLayer.MultiPhaseParameters.builder()
        .texture(new RenderPhase.Texture(CLOTHESLINE_TEXTURE, false, false))
        .program(new ShaderProgram(ClotheslineClient::getClotheslineShader))
        .transparency(NO_TRANSPARENCY)
        .lightmap(ENABLE_LIGHTMAP)
        .build(true)
    );

    public static RenderLayer getClothesline() {
        return CLOTHESLINE;
    }
}
