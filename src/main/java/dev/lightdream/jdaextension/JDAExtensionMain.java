package dev.lightdream.jdaextension;

import dev.lightdream.jdaextension.dto.JDAConfig;
import dev.lightdream.jdaextension.dto.JDAEmbed;
import dev.lightdream.jdaextension.managers.DiscordCommandManager;
import dev.lightdream.lambda.LambdaExecutor;
import dev.lightdream.logger.Logger;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.List;

public interface JDAExtensionMain {

    @SuppressWarnings("unused")
    static JDA generateBot(JDAExtensionMain main, String token) {
        return generateBot(main, token, new ArrayList<>());
    }

    static JDA generateBot(JDAExtensionMain main) {
        return generateBot(main, main.getJDAConfig().token);
    }

    static JDA generateBot(JDAExtensionMain main, List<GatewayIntent> gatewayIntents) {
        return generateBot(main, main.getJDAConfig().token, gatewayIntents);
    }

    static JDA generateBot(JDAExtensionMain main, String token, List<GatewayIntent> gatewayIntents) {
        return LambdaExecutor.LambdaCatch.ReturnLambdaCatch.executeCatch(() -> {
            try {
                return JDABuilder.createDefault(token)
                        .enableIntents(gatewayIntents)
                        .setActivity(Activity.playing("v" + main.getVersion() + " LightDream#4379"))
                        .build();
            } catch (LoginException e) {
                Logger.error("The bot token seems to be missing or incorrect, please check if it!");
                return null;
            }
        });
    }

    String getVersion();

    JDA getBot();

    JDAConfig getJDAConfig();

    DiscordCommandManager getDiscordCommandManager();

    JDAEmbed getHelpEmbed();

}
