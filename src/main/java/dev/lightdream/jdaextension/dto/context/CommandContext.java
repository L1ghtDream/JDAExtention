package dev.lightdream.jdaextension.dto.context;

import dev.lightdream.jdaextension.dto.JdaEmbed;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

public abstract class CommandContext {

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

    public MessageChannel getMessageChannel() {
        return event.getMessageChannel();
    }

    @SuppressWarnings("unused")
    public OptionMapping getArgument(String id) {
        return event.getOption(id);
    }

    public void sendMessage(JdaEmbed embed, boolean privateResponse) {
        if (privateResponse) {
            this.getEvent().replyEmbeds(embed.build().build()).setEphemeral(true).queue();
            return;
        }
        this.getMessageChannel().sendMessageEmbeds(embed.build().build()).queue();
        this.getEvent().deferReply().queue();
    }

}
