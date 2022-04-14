package dev.lightdream.jdaextension.commands.commands;

import dev.lightdream.jdaextension.JDAExtensionMain;
import dev.lightdream.jdaextension.Utils;
import dev.lightdream.jdaextension.commands.DiscordCommand;
import dev.lightdream.jdaextension.dto.context.GuildCommandContext;
import dev.lightdream.jdaextension.dto.context.PrivateCommandContext;
import net.dv8tion.jda.api.Permission;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class StatsCommand extends DiscordCommand {
    public StatsCommand(JDAExtensionMain main) {
        super(main, "stats", "Stats about the bot (Admin only)", Permission.ADMINISTRATOR, true, new ArrayList<>());
    }

    @Override
    public void executeGuild(GuildCommandContext context) {
        sendMessage(context,
                main.getJDAConfig().stats.parse("ram", String.valueOf(Utils.getRam()))
                        .parse("cpu", String.valueOf(Utils.getCpuLoad()))
                        .parse("java", Utils.getJava()));

    }

    @Override
    public void executePrivate(PrivateCommandContext context) {
        // Impossible
    }

    @Override
    public boolean isMemberSafe() {
        return false;
    }


}