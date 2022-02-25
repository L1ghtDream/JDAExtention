package dev.lightdream.jdaextension.commands.commands;

import dev.lightdream.jdaextension.JDAExtensionMain;
import dev.lightdream.jdaextension.commands.DiscordCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

import java.util.List;

@SuppressWarnings("unused")
public class HelpCommand extends DiscordCommand {
    public HelpCommand(JDAExtensionMain main) {
        super(main, "help", "Send the help command", null, "", true);
    }

    @Override
    public void execute(Member user, TextChannel channel, List<String> args) {
        execute(user.getUser(), channel, args);
    }

    @Override
    public void execute(User user, MessageChannel channel, List<String> args) {
        main.getDiscordCommandManager()
                .sendHelp(channel);
    }

    @Override
    public boolean isMemberSafe() {
        return true;
    }
}