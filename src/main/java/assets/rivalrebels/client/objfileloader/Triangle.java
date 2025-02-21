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
package assets.rivalrebels.client.objfileloader;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

import static org.lwjgl.opengl.GL11.*;

public class Triangle {
    public Vertice[] pa;
    private final BufferBuilder tes = Tessellator.getInstance().getBuffer();

    public Triangle(Vertice[] PA) {
        if (PA.length != 3)
            throw new IllegalArgumentException("Invalid Triangle! Specified Vec3 Array must have 3 Vec3s");
        pa = PA;
    }

    public void render() {
        tes.begin(GL_TRIANGLES, DefaultVertexFormats.POSITION_TEX_NORMAL);
        for (int i = 0; i < pa.length; i++) {
            pa[i].render();
        }
        Tessellator.getInstance().draw();
    }

    public void renderWireframe() {
        glLineWidth(2);
        tes.begin(GL_LINE_LOOP, DefaultVertexFormats.POSITION);
        for (int i = 0; i < pa.length; i++) {
            pa[i].renderWireframe();
        }
        Tessellator.getInstance().draw();
    }

    public void normalize() {
        for (int i = 0; i < pa.length; i++) {
            pa[i].normalize();
        }
    }

    public void scale(Vec3 v) {
        for (int i = 0; i < pa.length; i++) {
            pa[i].scale(v);
        }
    }

    public Triangle[] refine() {
        Triangle[] p = new Triangle[4];
        if (pa.length == 3) {
            Vertice mp1 = Vertice.average(pa[0], pa[1]);
            Vertice mp2 = Vertice.average(pa[1], pa[2]);
            Vertice mp3 = Vertice.average(pa[2], pa[0]);
            p[0] = new Triangle(new Vertice[]{pa[0].clone(), mp1.clone(), mp3.clone()});
            p[1] = new Triangle(new Vertice[]{pa[1].clone(), mp2.clone(), mp1.clone()});
            p[2] = new Triangle(new Vertice[]{pa[2].clone(), mp3.clone(), mp2.clone()});
            p[3] = new Triangle(new Vertice[]{mp1.clone(), mp2.clone(), mp3.clone()});
        }
        return p;
    }
}
