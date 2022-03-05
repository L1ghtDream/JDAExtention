package dev.lightdream.jdaextension.dto.context;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public class GuildCommandContext extends CommandContext {
    public GuildCommandContext(SlashCommandEvent event) {
        super(event);
    }

    @SuppressWarnings("unused")
    public TextChannel getTextChannel() {
        return getEvent().getTextChannel();
    }

    @SuppressWarnings("unused")
    public Member getMember() {
        return getEvent().getMember();
    }

    @SuppressWarnings("unused")
    public Guild getGuild() {
        return getEvent().getGuild();
    }

    public PrivateCommandContext toPrivate(){
        return new PrivateCommandContext(getEvent());
    }


}
