package dev.lightdream.jdaextension.dto.context;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public class PrivateCommandContext extends CommandContext {

    public PrivateCommandContext(SlashCommandEvent event) {
        super(event);
    }

}
