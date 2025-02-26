/*******************************************************************************
 * Copyright (c) 2012, 2016 Rodol Phito.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Mozilla Public License Version 2.0
 * which accompanies this distribution, and is available at
 * https://www.mozilla.org/en-US/MPL/2.0/
 * <p>
 * Rival Rebels Mod. All code, art, and design by Rodol Phito.
 * <p>
 * http://RivalRebels.com/
 *******************************************************************************/
package assets.rivalrebels.client.model;

import net.minecraft.client.renderer.GLAllocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

public class ModelFast3D {
    private final int textureID = 0;
    private int vboiId = 0;
    private final int vboId = 0;
    private int indicesCount = 0;
    private int pId = 0;

    public ModelFast3D(/*File rr3dfile, File pngtexfile*/) {
        ByteBuffer byteBuffer = GLAllocation.createDirectByteBuffer(3 * 5 * 8);
        FloatBuffer floatBuffer = byteBuffer.asFloatBuffer();
        floatBuffer.put(0f);
        floatBuffer.put(0f);
        floatBuffer.put(0f);
        floatBuffer.put(0f);
        floatBuffer.put(0f);

        floatBuffer.put(0f);
        floatBuffer.put(1f);
        floatBuffer.put(0f);
        floatBuffer.put(0f);
        floatBuffer.put(0f);

        floatBuffer.put(0f);
        floatBuffer.put(0f);
        floatBuffer.put(1f);
        floatBuffer.put(0f);
        floatBuffer.put(0f);
        floatBuffer.flip();

        byte[] indices = {0, 1, 2};
        indicesCount = indices.length;
        ByteBuffer indicesBuffer = GLAllocation.createDirectByteBuffer(indicesCount);
        indicesBuffer.put(indices);
        indicesBuffer.flip();

        //vboId = ARBVertexBufferObject.glGenBuffersARB(floatBuffer);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboId);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, floatBuffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 8, 0);
        GL20.glVertexAttribPointer(2, 2, GL11.GL_FLOAT, false, 8, 6);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        GL30.glBindVertexArray(0);

        vboiId = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboiId);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);

        int vsId = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
        int fsId = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
        GL20.glShaderSource(vsId, "void main() { gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex; }");
        GL20.glShaderSource(fsId, "void main() { gl_FragColor = vec4(0.4,0.4,0.8,1.0); }");
        GL20.glCompileShader(vsId);
        GL20.glCompileShader(fsId);

        pId = GL20.glCreateProgram();
        GL20.glAttachShader(pId, vsId);
        GL20.glAttachShader(pId, fsId);

        GL20.glBindAttribLocation(pId, 0, "in_Position");
        GL20.glBindAttribLocation(pId, 1, "in_Normal");
        GL20.glBindAttribLocation(pId, 2, "in_UV");

        GL20.glLinkProgram(pId);
        GL20.glValidateProgram(pId);
    }

    public void render() {
        GL20.glUseProgram(pId);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboId);
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL20.glEnableVertexAttribArray(2);

        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboiId);

        GL11.glDrawElements(GL11.GL_TRIANGLES, indicesCount, GL11.GL_UNSIGNED_BYTE, 0);

        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL30.glBindVertexArray(0);
        GL20.glUseProgram(0);
    }
}
