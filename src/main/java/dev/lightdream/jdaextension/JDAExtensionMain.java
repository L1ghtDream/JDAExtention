package dev.lightdream.jdaextension;

import dev.lightdream.jdaextension.dto.JDAConfig;
import dev.lightdream.jdaextension.dto.JDALang;
import dev.lightdream.jdaextension.dto.JdaEmbed;
import dev.lightdream.jdaextension.managers.DiscordCommandManager;
import dev.lightdream.lambda.LambdaExecutor;
import dev.lightdream.logger.Logger;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.List;

public interface JDAExtensionMain {

    @SuppressWarnings("unused")
    static JDA generateBot(String token) {
        return generateBot(token, new ArrayList<>());
    }

    static JDA generateBot(String token, List<GatewayIntent> gatewayIntents) {
        return LambdaExecutor.LambdaCatch.ReturnLambdaCatch.executeCatch(() -> {
            try {
                return JDABuilder.createDefault(token)
                        .enableIntents(gatewayIntents)
                        .build();
            } catch (LoginException e) {
                Logger.error("The bot token seems to be missing or incorrect, please check if it!");
                return null;
            }
        });
    }

    JDA getBot();

    JDAConfig getJDAConfig();

    DiscordCommandManager getDiscordCommandManager();

    JdaEmbed getHelpEmbed();

    JDALang getJDALang();

}
