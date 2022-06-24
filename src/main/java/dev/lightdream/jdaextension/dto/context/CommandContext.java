package dev.lightdream.jdaextension.dto.context;

import dev.lightdream.jdaextension.dto.JDAEmbed;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

public abstract class CommandContext {

    private final SlashCommandInteractionEvent  event;
    private boolean editedOriginal = false;

    public CommandContext(SlashCommandInteractionEvent event) {
        this.event = event;
    }

    public SlashCommandInteractionEvent  getEvent() {
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

    public void sendMessage(JDAEmbed embed, boolean privateResponse) {
        if (privateResponse) {
            if (!editedOriginal) {
                this.getEvent().getHook().editOriginalEmbeds(embed.build().build()).queue();
                this.editedOriginal = true;
                return;
            }
            this.getEvent().getHook().sendMessageEmbeds(embed.build().build()).setEphemeral(true).queue();
            return;
        }
        this.getEvent().getHook().deleteOriginal().queue();
        this.getMessageChannel().sendMessageEmbeds(embed.build().build()).queue();
    }

}
