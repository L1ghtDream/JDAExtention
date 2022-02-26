package dev.lightdream.jdaextension;

import dev.lightdream.jdaextension.dto.JDAConfig;
import dev.lightdream.jdaextension.dto.JdaEmbed;
import dev.lightdream.jdaextension.managers.DiscordCommandManager;
import net.dv8tion.jda.api.JDA;

public interface JDAExtensionMain {

    JDA getBot();

    JDAConfig getJDAConfig();

    DiscordCommandManager getDiscordCommandManager();

    String getPrefix();

    JdaEmbed getHelpEmbed();

}
