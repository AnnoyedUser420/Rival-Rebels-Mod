package assets.rivalrebels.common.command;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import assets.rivalrebels.common.core.RivalRebelsDamageSource;

public class CommandKillme extends CommandBase
{
	@Override
	public String getCommandName()
	{
		return "killme";
	}
	
	@Override
	public String getCommandUsage(ICommandSender par1ICommandSender)
	{
		return "/" + getCommandName();
	}
	
	@Override
	public List getCommandAliases()
	{
		return null;
	}
	
	@Override
	public boolean canCommandSenderUseCommand(ICommandSender par1)
	{
		return true;
	}
	
	@Override
	public void processCommand(ICommandSender sender, String[] array)
	{
		EntityPlayer person = getCommandSenderAsPlayer(sender);
		person.attackEntityFrom(RivalRebelsDamageSource.cyanide, 10000);
		sender.addChatMessage(new ChatComponentText("Do you think the cyanide tasted good?"));
	}
	
	/**
	 * Adds the strings available in this command to the given list of tab completion options.
	 */
	@Override
	public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr)
	{
		return par2ArrayOfStr.length >= 1 ? getListOfStringsMatchingLastWord(par2ArrayOfStr, MinecraftServer.getServer().getAllUsernames()) : null;
	}
}