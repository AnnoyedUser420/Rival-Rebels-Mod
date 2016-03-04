package assets.rivalrebels.client.renderentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.client.objfileloader.ModelFromObj;
import assets.rivalrebels.common.entity.EntityB83;
import assets.rivalrebels.common.entity.EntityHackB83;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderHackB83 extends Render
{
	public RenderHackB83()
	{
	}
	
	public void renderB83(EntityHackB83 b83, double x, double y, double z, float par8, float par9)
	{
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glTranslatef((float) x, (float) y, (float) z);
		GL11.glRotatef(b83.rotationYaw - 90.0f, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(b83.rotationPitch - 180.0f, 0.0F, 0.0F, 1.0F);
		Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.etb83);
		RenderB83.md.render();
		GL11.glPopMatrix();
	}
	
	/**
	 * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then handing it off to a worker function which does the actual work. In all
	 * probabilty, the class Render is generic (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1, double d2, float f, float f1). But JAD is pre
	 * 1.5 so doesn't do that.
	 */
	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	{
		renderB83((EntityHackB83) par1Entity, par2, par4, par6, par8, par9);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return null;
	}
}