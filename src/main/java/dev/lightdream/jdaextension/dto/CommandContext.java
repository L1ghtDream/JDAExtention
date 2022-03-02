package dev.lightdream.jdaextension.dto;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

public class CommandContext {

    private final SlashCommandEvent event;

    public CommandContext(SlashCommandEvent event) {
        this.event = event;
    }

    public SlashCommandEvent getEvent() {
        return event;
    }

    @SuppressWarnings("unused")
    public User getUser() {
        return event.getUser();
    }

    @SuppressWarnings("unused")
    public Member getMember() {
        return event.getMember();
    }

    @SuppressWarnings("unused")
    public TextChannel getTextChannel() {
        return event.getTextChannel();
    }

    public MessageChannel getMessageChannel() {
        return event.getMessageChannel();
    }

    @SuppressWarnings("unused")
    public Guild getGuild() {
        return event.getGuild();
    }

    @SuppressWarnings("unused")
    public OptionMapping getArgument(String id) {
        return event.getOption(id);
    }

}
