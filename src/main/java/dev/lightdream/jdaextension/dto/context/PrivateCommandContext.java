package dev.lightdream.jdaextension.dto.context;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class PrivateCommandContext extends CommandContext {

    public PrivateCommandContext(SlashCommandInteractionEvent event) {
        super(event);
    }

}
