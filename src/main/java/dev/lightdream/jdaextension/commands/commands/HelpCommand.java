package dev.lightdream.jdaextension.commands.commands;

import dev.lightdream.jdaextension.JDAExtensionMain;
import dev.lightdream.jdaextension.commands.DiscordCommand;
import dev.lightdream.jdaextension.dto.CommandContext;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class HelpCommand extends DiscordCommand {
    public HelpCommand(JDAExtensionMain main) {
        super(main, "help", main.getJDALang().getHelpCommandDescription(), null, true, new ArrayList<>());
    }

    @Override
    public void executeGuild(CommandContext context) {
        executePrivate(context);
    }

    @Override
    public void executePrivate(CommandContext context) {
        main.getDiscordCommandManager().sendHelp(context, privateResponse);
    }

    @Override
    public boolean isMemberSafe() {
        return true;
    }
}