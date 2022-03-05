package dev.lightdream.jdaextension.managers;

import dev.lightdream.jdaextension.JDAExtensionMain;
import dev.lightdream.jdaextension.commands.DiscordCommand;
import dev.lightdream.jdaextension.dto.context.CommandContext;
import dev.lightdream.jdaextension.dto.context.PrivateCommandContext;
import dev.lightdream.logger.Logger;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class DiscordCommandManager extends ListenerAdapter {

    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private final JDAExtensionMain main;
    public List<DiscordCommand> commands;

    public DiscordCommandManager(JDAExtensionMain main, List<DiscordCommand> commands) {
        this.main = main;
        this.commands = commands;

        commands.forEach(command ->
                command.aliases.forEach(alias -> {
                    if (command.description.isEmpty()) {
                        Logger.error("Skipping registering command " + alias + " as it does not have a description");
                        return;
                    }
                    CommandData commandData = new CommandData(alias, command.description);

                    AtomicBoolean skip = new AtomicBoolean(false);

                    command.arguments.forEach(argument -> {
                        if (argument.description.isEmpty()) {
                            skip.set(true);
                        }
                        if (skip.get()) {
                            return;
                        }
                        commandData.addOption(argument.type, argument.name, argument.description, argument.required);

                    });

                    if (skip.get()) {
                        Logger.error("Skipping registering command " + alias + " as one of its arguments does not have a description");
                        return;
                    }

                    System.out.println("Registering command " + alias);
                    main.getBot().upsertCommand(commandData).queue();
                }));


        main.getBot().addEventListener(this);
    }

    public void sendHelp(CommandContext context, boolean privateResponse) {
        if (privateResponse) {
            context.getEvent().replyEmbeds(main.getHelpEmbed().build().build()).queue();
            return;
        }
        context.getMessageChannel().sendMessageEmbeds(main.getHelpEmbed().build().build()).queue();
    }

    @Override
    public void onSlashCommand(@NotNull SlashCommandEvent event) {
        String[] message = event.getCommandString().split(" ");

        Member member = event.getMember();

        if (message[0].startsWith("/")) {
            commands.forEach(command -> {
                if (command.aliases.contains(message[0].replace("/", "")
                        .toLowerCase())) {
                    if (command.hasPermission(member)) {
                        command.execute(event);
                        return;
                    }
                    command.sendMessage(new PrivateCommandContext(event), main.getJDAConfig().notAllowed);
                }
            });

        }
    }
}

