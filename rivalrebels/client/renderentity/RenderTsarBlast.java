package assets.rivalrebels.client.renderentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.client.model.ModelBlastSphere;
import assets.rivalrebels.client.model.ModelTsarBlast;
import assets.rivalrebels.common.entity.EntityTsarBlast;

public class RenderTsarBlast extends Render
{
	private ModelTsarBlast		model;
	private ModelBlastSphere	modelsphere;
	
	public RenderTsarBlast()
	{
		model = new ModelTsarBlast();
		modelsphere = new ModelBlastSphere();
	}
	
	@Override
	public void doRender(Entity var1, double x, double y, double z, float var8, float var9)
	{
		EntityTsarBlast tsar = (EntityTsarBlast) var1;
		tsar.time++;
		double radius = (((tsar.motionX * 10) - 1) * ((tsar.motionX * 10) - 1) * 2) + RivalRebels.tsarBombaStrength;
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		if (tsar.time < 60)
		{
			double elev = tsar.time / 5f;
			GL11.glTranslated(x, y + elev, z);
			modelsphere.renderModel(tsar.time, 1, 1, 1, 1);
		}
		else if (tsar.time < 300 && radius - RivalRebels.tsarBombaStrength > 9)
		{
			double elev = (tsar.time - 60f) / 4f;
			GL11.glTranslated(x, y + elev, z);
			GL11.glPushMatrix();
			GL11.glRotatef((float) (elev * 2), 0, 1, 0);
			GL11.glRotatef((float) (elev * 3), 1, 0, 0);
			modelsphere.renderModel((float) elev, 1, 0.25f, 0, 1f);
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			GL11.glRotatef((float) (elev * -2), 0, 1, 0);
			GL11.glRotatef((float) (elev * 4), 0, 0, 1);
			modelsphere.renderModel((float) (elev - 0.2f), 1, 0.5f, 0, 1f);
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			GL11.glRotatef((float) (elev * -3), 1, 0, 0);
			GL11.glRotatef((float) (elev * 2), 0, 0, 1);
			modelsphere.renderModel((float) (elev - 0.4f), 1, 0, 0, 1f);
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			GL11.glRotatef((float) (elev * -1), 0, 1, 0);
			GL11.glRotatef((float) (elev * 3), 0, 0, 1);
			modelsphere.renderModel((float) (elev - 0.6f), 1, 1, 0, 1);
			GL11.glPopMatrix();
		}
		else
		{
			Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.ettsarflame);
			GL11.glTranslated(x, y + 10 + ((tsar.motionX - 0.1d) * 14.14213562), z);
			GL11.glScalef((float) (radius * 0.116f), (float) (radius * 0.065f), (float) (radius * 0.116f));
			model.render();
		}
		GL11.glPopMatrix();
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return null;
	}
}
