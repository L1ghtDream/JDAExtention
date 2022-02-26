package dev.lightdream.jdaextension.commands;

import dev.lightdream.jdaextension.JDAExtensionMain;
import dev.lightdream.jdaextension.dto.JdaEmbed;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.internal.utils.PermissionUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class DiscordCommand {

    public final @NotNull List<String> aliases = new ArrayList<>();
    public final @NotNull String description;
    public final @NotNull String usage;
    public final Permission permission;
    public final boolean deleteCommandMessage;
    protected final JDAExtensionMain main;

    @SuppressWarnings("unused")
    public DiscordCommand(JDAExtensionMain main, @NotNull List<String> aliases, @NotNull String description, Permission permission, @NotNull String usage, boolean deleteCommandMessage) {
        this.main = main;
        for (String alias : aliases) {
            this.aliases.add(alias.toLowerCase());
        }
        this.description = description;
        this.usage = usage;
        this.permission = permission;
        this.deleteCommandMessage = deleteCommandMessage;
    }

    @SuppressWarnings("unused")
    public DiscordCommand(JDAExtensionMain main, @NotNull String alias, @NotNull String description, Permission permission, @NotNull String usage, boolean deleteCommandMessage) {
        this.main = main;
        this.aliases.add(alias.toLowerCase());
        this.description = description;
        this.usage = usage;
        this.permission = permission;
        this.deleteCommandMessage = deleteCommandMessage;
    }

    @SuppressWarnings("unused")
    public boolean hasPermission(Member member) {
        if (permission == null) {
            return true;
        }
        if (member == null) {
            return false;
        }
        return PermissionUtil.checkPermission(member, permission);

    }

    public void execute(@Nullable Member member, User user, TextChannel channel, List<String> args, Message message) {
        if (deleteCommandMessage) {
            message.delete().queue();
        }
        if (!isMemberSafe()) {
            if (member == null) {
                sendMessage(channel, main.getJDAConfig().serverCommand);
                return;
            }
        }
        if (member == null) {
            execute(user, channel, args);
            return;
        }
        execute(member, channel, args);
    }

    public abstract void execute(Member member, TextChannel channel, List<String> args);

    public abstract void execute(User user, MessageChannel channel, List<String> args);

    @SuppressWarnings("unused")
    public void sendUsage(MessageChannel channel) {
        channel.sendMessageEmbeds(main.getJDAConfig().usage.parse("command", aliases.get(0))
                        .parse("usage", usage)
                        .build()
                        .build())
                .queue();
    }

    public void sendMessage(MessageChannel channel, JdaEmbed embed) {
        channel.sendMessageEmbeds(embed.build().build()).queue();
    }


    /**
     * False  if it requires the member to be not null
     * True   if it does not require the member to be not null
     */
    public abstract boolean isMemberSafe();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscordCommand that = (DiscordCommand) o;
        for (String alias : aliases) {
            if (that.aliases.contains(alias)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(aliases);
    }

}