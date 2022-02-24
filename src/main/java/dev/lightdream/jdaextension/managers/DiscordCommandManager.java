package dev.lightdream.jdaextension.managers;

import dev.lightdream.jdaextension.JDAExtensionMain;
import dev.lightdream.jdaextension.commands.DiscordCommand;
import dev.lightdream.jdaextension.commands.commands.HelpCommand;
import dev.lightdream.jdaextension.commands.commands.StatsCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DiscordCommandManager extends ListenerAdapter {

    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private final JDAExtensionMain main;
    public List<DiscordCommand> commands;

    public DiscordCommandManager(JDAExtensionMain main, List<DiscordCommand> commands) {
        this.main = main;
        this.commands = commands;
        this.commands.add(new HelpCommand(main));
        this.commands.add(new StatsCommand(main));
        main.getBot().addEventListener(this);
    }

    public void sendHelp(MessageChannel channel) {
        channel.sendMessageEmbeds(main.getJDAConfig().helpEmbed.build().build()).queue();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }

        String[] message = event.getMessage().getContentRaw().split(" ");
        Member member = event.getMember();

        if (message[0].startsWith("+")) {
            commands.forEach(command -> {
                if (command.aliases.contains(message[0].replace("+", "").toLowerCase())) {
                    if (command.permission == null) {
                        if (member != null) {
                            if (command.hasPermission(event.getMember())) {
                                command.execute(event.getMember(),
                                        event.getAuthor(),
                                        event.getTextChannel(),
                                        new ArrayList<>(Arrays.asList(message).subList(1, message.length)));
                                return;
                            }
                            return;
                        }
                        command.execute(null,
                                event.getAuthor(),
                                event.getTextChannel(),
                                new ArrayList<>(Arrays.asList(message).subList(1, message.length)));
                        return;
                    }
                    if (command.hasPermission(event.getMember())) {
                        command.execute(event.getMember(),
                                event.getAuthor(),
                                event.getTextChannel(),
                                new ArrayList<>(Arrays.asList(message).subList(1, message.length)));
                        return;
                    }
                    command.sendMessage(event.getChannel(), main.getJDAConfig().notAllowed);

                }
            });
        }
    }


}