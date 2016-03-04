package assets.rivalrebels.client.renderentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.client.model.ModelDisk;
import assets.rivalrebels.common.entity.EntityRoddiskLeader;

public class RenderRoddiskLeader extends Render
{
	int					er	= 0;
	private ModelDisk	model;
	
	public RenderRoddiskLeader()
	{
		model = new ModelDisk();
	}
	
	@Override
	public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9)
	{
		er += 13.46;
		EntityRoddiskLeader erd = (EntityRoddiskLeader) var1;
		Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.etdisk3);
		GL11.glPushMatrix();
		GL11.glScalef(0.4f, 0.4f, 0.4f);
		GL11.glTranslatef((float) var2, (float) var4, (float) var6);
		GL11.glPushMatrix();
		GL11.glRotatef(erd.rotationPitch, 0.0F, 0.0F, 1.0F);
		GL11.glRotatef(erd.rotationYaw - 90.0f + er, 0.0F, 1.0F, 0.0F);
		
		model.render();
		
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return null;
	}
}
