package dev.lightdream.jdaextension.commands.commands;

import dev.lightdream.jdaextension.JDAExtensionMain;
import dev.lightdream.jdaextension.Utils;
import dev.lightdream.jdaextension.commands.DiscordCommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

import java.util.List;

public class StatsCommand extends DiscordCommand {
    public StatsCommand(JDAExtensionMain main) {
super(main, "stats", "Shows technical details about the bot and its environment", Permission.ADMINISTRATOR, "", true);
    }

    @Override
    public void execute(Member member, TextChannel channel, List<String> args) {
        execute(member.getUser(), channel, args);
    }

    @Override
    public void execute(User user, MessageChannel channel, List<String> args) {
        sendMessage(channel,
                main.getJDAConfig().stats.parse("ram", String.valueOf(Utils.getRam()))
                        .parse("cpu", String.valueOf(Utils.getCpuLoad()))
                        .parse("java", Utils.getJava())
        );
    }

    @Override
    public boolean isMemberSafe() {
        return true;
    }


}