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
// Copyrighted Rodolian Material
package assets.rivalrebels.client.model;

import assets.rivalrebels.client.renderhelper.RenderHelper;
import assets.rivalrebels.client.renderhelper.TextureVertice;
import assets.rivalrebels.client.renderhelper.Vertice;
import org.lwjgl.opengl.GL11;

public class ModelAstroBlasterBack {
    private final float i = 0.05f;
    private final float[] barrelx = {0f, 0.2f, 0.25f, 0.25f, 0f};
    private final float[] barrely = {0.1f, 0.1f, 0.05f, -0.25f, -0.25f};
    private final float[] tsart = {i * 20, i * 16, i * 13, i * 5, 0};

    private final int segments = 8;
    private final float deg = (float) Math.PI * 2f / segments;
    private final float sin = (float) Math.sin(deg);
    private final float cos = (float) Math.cos(deg);
    private final float add = 360 / segments;

    public void render() {
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_CULL_FACE);
        for (float i = 0; i < segments; i++) {
            GL11.glPushMatrix();
            GL11.glRotatef(add * i, 0, 1, 0);
            for (int f = 1; f < barrelx.length; f++) {
                TextureVertice t1 = new TextureVertice((1f / segments) * i, tsart[f]);
                TextureVertice t2 = new TextureVertice((1f / segments) * i, tsart[f - 1]);
                TextureVertice t3 = new TextureVertice((1f / segments) * (i + 1), tsart[f - 1]);
                TextureVertice t4 = new TextureVertice((1f / segments) * (i + 1), tsart[f]);
                RenderHelper.addFace(new Vertice(0f, barrely[f], barrelx[f]),
                        new Vertice(0f, barrely[f - 1], barrelx[f - 1]),
                        new Vertice(barrelx[f - 1] * sin, barrely[f - 1], barrelx[f - 1] * cos),
                        new Vertice(barrelx[f] * sin, barrely[f], barrelx[f] * cos), t1, t2, t3, t4);
            }
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
    }
}
