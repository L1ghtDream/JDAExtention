package dev.lightdream.jdaextension.commands.commands;

import dev.lightdream.jdaextension.JDAExtensionMain;
import dev.lightdream.jdaextension.commands.DiscordCommand;
import dev.lightdream.jdaextension.dto.context.GuildCommandContext;
import dev.lightdream.jdaextension.dto.context.PrivateCommandContext;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class HelpCommand extends DiscordCommand {
    public HelpCommand(JDAExtensionMain main) {
        super(main, "help", main.getJDALang().getHelpCommandDescription(), null, true, new ArrayList<>());
    }

    @Override
    public void executeGuild(GuildCommandContext context) {
        executePrivate(context.toPrivate());
    }

    @Override
    public void executePrivate(PrivateCommandContext context) {
        main.getDiscordCommandManager().sendHelp(context, privateResponse);
    }

    @Override
    public boolean isMemberSafe() {
        return true;
    }
}