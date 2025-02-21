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
package assets.rivalrebels.client.gui;

import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.client.renderentity.RenderRhodes;
import assets.rivalrebels.client.tileentityrender.TileEntityForceFieldNodeRenderer;
import assets.rivalrebels.common.entity.EntityRhodes;
import assets.rivalrebels.common.item.weapon.ItemBinoculars;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class RivalRebelsRenderOverlay {
    public int tic = 0;
    public boolean r = false;
    public EntityRhodes rhodes = null;
    public long counter = 0;
    float beep = 0.0f;

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void eventHandler(RenderGameOverlayEvent event) {
        if (rhodes != null) {
            renderRhodes(event, Minecraft.getMinecraft().player, rhodes);
        }
        renderItems(event);
    }

    private void renderItems(RenderGameOverlayEvent event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.HOTBAR) {
            EntityPlayer player = Minecraft.getMinecraft().player;
            ItemStack item = player.inventory.getCurrentItem();
            if (item == null) return;
            if (item.getItem() instanceof ItemBinoculars) renderBinoculars(item, event, player);
        }
    }

    private void renderRhodes(RenderGameOverlayEvent event, EntityPlayer player, EntityRhodes rhodes) {
        if (event.getType() == ElementType.HOTBAR) {
            counter--;
            if (counter <= 0) {
                counter = 0;
                RivalRebels.rrro.rhodes = null;
            }
			/*for (int i = 0; i < 65536; i++)
			{
				GL11.glBindTexture(GL11.GL_TEXTURE_2D, i);
				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
			}*/
            Tessellator t = Tessellator.instance;
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            GlStateManager.depthMask(false);
            GlStateManager.enableBlend();
            FontRenderer fr = Minecraft.getMinecraft().fontRenderer;
            int w = event.resolution.getScaledWidth();
            int h = event.resolution.getScaledHeight();

            GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            Minecraft.getMinecraft().getTextureManager().bindTexture(RivalRebels.guirhodesline);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
            GlStateManager.color(1.0F, 0.0F, 0.0F, 0.5F);
            t.startDrawingQuads();
            t.addVertexWithUV(0, h, -90, 0, 1);
            t.addVertexWithUV(w, h, -90, 1, 1);
            t.addVertexWithUV(w, 0, -90, 1, 0);
            t.addVertexWithUV(0, 0, -90, 0, 0);
            t.draw();

            GlStateManager.blendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
            Minecraft.getMinecraft().getTextureManager().bindTexture(RivalRebels.guirhodesout);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
            GlStateManager.color(0.0F, 0.0F, 0.0F, 0.333f);
            t.startDrawingQuads();
            t.addVertexWithUV(0, h, -90, 0, 1);
            t.addVertexWithUV(w, h, -90, 1, 1);
            t.addVertexWithUV(w, 0, -90, 1, 0);
            t.addVertexWithUV(0, 0, -90, 0, 0);
            t.draw();

            if (Keyboard.isKeyDown(Keyboard.KEY_H)) {
                GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                Minecraft.getMinecraft().getTextureManager().bindTexture(RivalRebels.guirhodeshelp);
                GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
                GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                t.startDrawingQuads();
                t.addVertexWithUV(w * 0.25f, h * 0.75f, -90, 0, 1);
                t.addVertexWithUV(w * 0.75f, h * 0.75f, -90, 1, 1);
                t.addVertexWithUV(w * 0.75f, h * 0.25f, -90, 1, 0);
                t.addVertexWithUV(w * 0.25f, h * 0.25f, -90, 0, 0);
                t.draw();
            }

            if (rhodes.itexfolder > -1 && rhodes.itexfolder < 4) {
                GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(RivalRebels.MODID, "textures/" + RenderRhodes.texfolders[rhodes.itexfolder] + rhodes.itexloc + ".png"));
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0f);
                t.startDrawingQuads();
                float s = 8;
                float wl = w * 0.5f;
                float hl = h * 0.05f;
                t.addVertexWithUV(wl - s, hl + s, -90, 0, 1);
                t.addVertexWithUV(wl + s, hl + s, -90, 1, 1);
                t.addVertexWithUV(wl + s, hl - s, -90, 1, 0);
                t.addVertexWithUV(wl - s, hl - s, -90, 0, 0);
                t.draw();
            }
            GlStateManager.disableBlend();

            String disp = "Rival Rebels";
            fr.drawString(disp, (int) (w * 0.05), (int) (h * 0.05), 0xffffff, false);
            disp = "Robot: " + rhodes.getName();
            fr.drawString(disp, (int) (w * 0.05), (int) (h * 0.1), 0xffffff, false);
            disp = RivalRebels.reactor.getLocalizedName() + ": " + rhodes.health;
            float val = (rhodes.health / (float) RivalRebels.rhodesHealth);
            fr.drawString(disp, (int) (w * 0.05), (int) (h * 0.15), (((int) ((1 - val) * 255) & 255) << 16) | (((int) (val * 255) & 255) << 8), false);
            float yaw = (player.rotationYaw + 360000) % 360;
            disp = (yaw >= 315 || yaw < 45) ? I18n.translateToLocal("RivalRebels.binoculars.south") : (yaw >= 45 && yaw < 135) ? I18n.translateToLocal("RivalRebels.binoculars.west") : (yaw >= 135 && yaw < 225) ? I18n.translateToLocal("RivalRebels.binoculars.north") : (yaw >= 225 && yaw < 315) ? I18n.translateToLocal("RivalRebels.binoculars.east") : "Whut";
            fr.drawString(disp, (int) (w * 0.05), (int) (h * 0.2), 0xffffff, false);

            disp = I18n.translateToLocal(RivalRebels.einsten.getUnlocalizedName() + ".name") + ": " + rhodes.energy;
            fr.drawString(disp, (int) (w * 0.8), (int) (h * 0.05), (rhodes.laserOn > 0) ? 0xff3333 : 0xffffff, false);
            disp = "Jet: " + rhodes.energy;
            fr.drawString(disp, (int) (w * 0.8), (int) (h * 0.1), RivalRebels.proxy.spacebar() ? 0x6666ff : 0xffffff, false);
            disp = RivalRebels.forcefieldnode.getLocalizedName() + ": " + rhodes.energy;
            fr.drawString(disp, (int) (w * 0.8), (int) (h * 0.15), rhodes.forcefield ? 0xBB88FF : 0xffffff, false);
            disp = I18n.translateToLocal(RivalRebels.seekm202.getUnlocalizedName() + ".name") + ": " + rhodes.rocketcount;
            fr.drawString(disp, (int) (w * 0.8), (int) (h * 0.2), 0xffffff, false);
            disp = (rhodes.plasma ? "Plasma: " : (I18n.translateToLocal(RivalRebels.fuel.getUnlocalizedName() + ".name") + ": ")) + rhodes.flamecount;
            fr.drawString(disp, (int) (w * 0.8), (int) (h * 0.25), 0xffffff, false);
            disp = RivalRebels.nuclearBomb.getLocalizedName() + ": " + rhodes.nukecount;
            fr.drawString(disp, (int) (w * 0.8), (int) (h * 0.3), 0xffffff, false);
            disp = "Guard";
            fr.drawString(disp, (int) (w * 0.8), (int) (h * 0.35), Keyboard.isKeyDown(Keyboard.KEY_G) ? 0xffff00 : 0xffffff, false);
            disp = rhodes.getName() + " " + RivalRebels.controller.getLocalizedName() + ": H";
            fr.drawString(disp, (int) (w * 0.05), (int) (h * 0.95), Keyboard.isKeyDown(Keyboard.KEY_H) ? 0xffff00 : 0xffffff, false);
            if (rhodes.forcefield) {
                GL11.glBindTexture(GL11.GL_TEXTURE_2D, TileEntityForceFieldNodeRenderer.id[(int) ((TileEntityForceFieldNodeRenderer.getTime() / 100) % TileEntityForceFieldNodeRenderer.frames)]);
                GlStateManager.enableBlend();
                GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
                GlStateManager.disableLighting();
                GlStateManager.color(1.0F, 1.0F, 1.0F, 0.7f);
                t.startDrawingQuads();
                t.addVertexWithUV(0, h, -90, 0, h * 0.003f);
                t.addVertexWithUV(w, h, -90, w * 0.003f, h * 0.003f);
                t.addVertexWithUV(w, 0, -90, w * 0.003f, 0);
                t.addVertexWithUV(0, 0, -90, 0, 0);
                t.draw();
                GlStateManager.disableBlend();
                GlStateManager.enableLighting();
            }
        }
    }

    private void renderBinoculars(ItemStack item, RenderGameOverlayEvent event, EntityPlayer player) {
        if (Mouse.isButtonDown(1)) {
            if (event.isCancelable()) event.setCanceled(true);
            if (event.getType() == ElementType.HOTBAR) {
                Tessellator t = Tessellator.instance;
                tic++;
                GL11.glDisable(GL11.GL_DEPTH_TEST);
                GlStateManager.depthMask(false);
                Minecraft.getMinecraft().getTextureManager().bindTexture(RivalRebels.guibinoculars);
                FontRenderer fr = Minecraft.getMinecraft().fontRenderer;
                int w = event.getResolution().getScaledWidth();
                int h = event.getResolution().getScaledHeight();

                GlStateManager.blendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
                GlStateManager.enableBlend();
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                t.startDrawingQuads();
                t.addVertexWithUV(0, h, -90, 0, 1);
                t.addVertexWithUV(w, h, -90, 1, 1);
                t.addVertexWithUV(w, 0, -90, 1, 0);
                t.addVertexWithUV(0, 0, -90, 0, 0);
                t.draw();

                Minecraft.getMinecraft().getTextureManager().bindTexture(RivalRebels.guibinocularsoverlay);
                GlStateManager.color(0.333F, 0.333F, 0.333F, 0.5F);
                t.startDrawingQuads();
                t.addVertexWithUV(0, h, -90, 0, 1);
                t.addVertexWithUV(w, h, -90, 1, 1);
                t.addVertexWithUV(w, 0, -90, 1, 0);
                t.addVertexWithUV(0, 0, -90, 0, 0);
                t.draw();

                // GlStateManager.blendFunc(GL11.GL_ONE, GL11.GL_ONE);
                // GlStateManager.disableBlend();
                // GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                // t.startDrawing(GL11.GL_LINES);
                // t.addVertex(1, 100, -90);
                // t.addVertex(100, 100, -90);
                // t.addVertex(100, 1, -90);
                // t.addVertex(1, 1, -90);
                // t.draw();
                // GlStateManager.enableBlend();
                Block id = player.world.getBlock(ItemBinoculars.tx, ItemBinoculars.ty, ItemBinoculars.tz);
                String disp = "X";
                if (id != null) disp = I18n.translateToLocal(id.getUnlocalizedName() + ".name");
                fr.drawString(disp, (int) ((w * 0.50) - (fr.getStringWidth(disp) / 2f)), (int) (h * 0.18), 0x00ff00, false);
                if (!ItemBinoculars.tooFar)
                    disp = "(" + ItemBinoculars.tx + ", " + ItemBinoculars.ty + ", " + ItemBinoculars.tz + ")";
                else disp = "";
                fr.drawString(disp, (int) ((w * 0.50) - (fr.getStringWidth(disp) / 2f)), (int) (h * 0.13), 0x00ff00, false);
                disp = "";
                if (tic % 30 == 0) r = !r;
                //if (nbt.getInteger("ty") != -1 && nbt.getInteger("cooldowntime") > 0) disp = ">" + nbt.getInteger("tx") + ", " + nbt.getInteger("ty") + ", " + nbt.getInteger("tz") + "<";
                //else if (r) disp = ">                    <";
                //fr.drawString(disp, (int) ((w * 0.50) - (fr.getStringWidth(disp) / 2f)), (int) (h * 0.85), 0xff0000, false);
                disp = "LTD RR";
                fr.drawString(disp, (int) ((w * 0.50) - (fr.getStringWidth(disp) / 2f)), (int) (h * 0.80), 0xffffff, false);
                disp = ((int) ItemBinoculars.distblock) + "m";
                fr.drawString(disp, (int) ((w * 0.637) - (fr.getStringWidth(disp) / 2f)), (int) (h * 0.205), 0xffffff, false);
                float yaw = (player.rotationYaw + 360000) % 360;
                disp = (yaw >= 315 || yaw < 45) ? I18n.translateToLocal("RivalRebels.binoculars.south") : (yaw >= 45 && yaw < 135) ? I18n.translateToLocal("RivalRebels.binoculars.west") : (yaw >= 135 && yaw < 225) ? I18n.translateToLocal("RivalRebels.binoculars.north") : (yaw >= 225 && yaw < 315) ? I18n.translateToLocal("RivalRebels.binoculars.east") : "Whut";
                fr.drawString(disp, (int) ((w * 0.370) - (fr.getStringWidth(disp) / 2f)), (int) (h * 0.205), 0xffffff, false);
                if (ItemBinoculars.tooFar)
                    fr.drawString(I18n.translateToLocal("RivalRebels.controller.range"), (int) ((w * 0.5) - (fr.getStringWidth(I18n.translateToLocal("RivalRebels.controller.range")) / 2f)), (int) (h * 0.85), 0xff0000, false);
                else if (ItemBinoculars.tooClose)
                    fr.drawString(I18n.translateToLocal("RivalRebels.nextbattle.no"), (int) ((w * 0.5) - (fr.getStringWidth(I18n.translateToLocal("RivalRebels.nextbattle.no")) / 2f)), (int) (h * 0.85), 0xff0000, false);
                    //else if (dist2 < 40)
                    //{
                    //	disp = I18n.translateToLocal("RivalRebels.nextbattle.no");
                    //	fr.drawString(disp, (int) ((w * 0.5) - (fr.getStringWidth(disp) / 2f)), (int) (h * 0.90), 0xff0000, false);
                    //	disp = (team == RivalRebelsTeam.OMEGA ? RivalRebels.omegaobj.getLocalizedName() : RivalRebels.omegaobj.getLocalizedName());
                    //	fr.drawString(disp, (int) ((w * 0.5) - (fr.getStringWidth(disp) / 2f)), (int) (h * 0.94), 0xff0000, false);
                    //}
                else if (ItemBinoculars.ready)
                    fr.drawString(I18n.translateToLocal("RivalRebels.binoculars.target"), (int) ((w * 0.5) - (fr.getStringWidth(I18n.translateToLocal("RivalRebels.binoculars.target")) / 2f)), (int) (h * 0.85), 0xff0000, false);

                fr.drawString(I18n.translateToLocal("RivalRebels.message.use") + " " + I18n.translateToLocal("RivalRebels.sneak") + " B-83 x2", (int) (w * 0.05), (int) (h * 0.95), 0xff0000, false);
                fr.drawString("Press C to select bomb type", (int) (w * 0.60), (int) (h * 0.95), 0xff0000, false);

                if ((ItemBinoculars.tasks > 0 || ItemBinoculars.carpet > 0) && ItemBinoculars.dist < 10f) {
                    Minecraft.getMinecraft().getTextureManager().bindTexture(ItemBinoculars.c ? RivalRebels.guicarpet : RivalRebels.ittaskb83);

                    GlStateManager.blendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
                    float col = 1.0f - ItemBinoculars.dist / 10f;
                    GlStateManager.color(col, col, col, 1.0f);
                    t.startDrawingQuads();
                    t.addVertexWithUV(w * 0.72, h * 0.85 + 16, -90, 0, 1);
                    t.addVertexWithUV(w * 0.72 + 16, h * 0.85 + 16, -90, 1, 1);
                    t.addVertexWithUV(w * 0.72 + 16, h * 0.85, -90, 1, 0);
                    t.addVertexWithUV(w * 0.72, h * 0.85, -90, 0, 0);
                    t.draw();

                    disp = "x" + ItemBinoculars.tasks;
                    fr.drawString(disp, (int) (w * 0.76), (int) (h * 0.85), ItemBinoculars.c ? 0xffff00 : 0xff0000, false);
                    disp = "x" + ItemBinoculars.carpet;
                    fr.drawString(disp, (int) (w * 0.76), (int) (h * 0.9), ItemBinoculars.c ? 0xff0000 : 0xffff00, false);
                    disp = I18n.translateToLocal("RivalRebels.tacticalnuke.name");
                    if (!r)
                        fr.drawString(disp, (int) ((w * 0.5) - (fr.getStringWidth(disp) / 2f)), (int) (h * 0.71), 0x00ff00, false);
                } else if ((ItemBinoculars.tasks > 0 || ItemBinoculars.carpet > 0) && ItemBinoculars.hasLaptop) {
                    GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
                    disp = RivalRebels.controller.getLocalizedName() + " " + I18n.translateToLocal("RivalRebels.controller.range");
                    fr.drawString(disp, (int) (w * 0.63), (int) (h * 0.87), 0xffff00, false);
                }
            }
        }
    }
}