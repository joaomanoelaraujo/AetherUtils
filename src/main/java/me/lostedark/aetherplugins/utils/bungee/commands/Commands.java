package me.lostedark.aetherplugins.utils.bungee.commands;

import me.lostedark.aetherplugins.utils.bungee.Bungee;
import me.lostedark.aetherplugins.utils.bungee.commands.collections.GoCommand;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public abstract class Commands extends Command {
   public static void setupCommands() {
      new GoCommand();
   }

   public Commands(String name) {
      super(name);
      Bungee.getInstance().getProxy().getPluginManager().registerCommand(Bungee.getInstance(), this);
   }

   public abstract void executeCommand(CommandSender var1, String[] var2);

   public void execute(CommandSender sender, String[] args) {
      this.executeCommand(sender, args);
   }
}
