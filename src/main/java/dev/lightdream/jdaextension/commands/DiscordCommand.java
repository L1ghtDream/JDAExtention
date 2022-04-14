package dev.lightdream.jdaextension.commands;

import dev.lightdream.jdaextension.JDAExtensionMain;
import dev.lightdream.jdaextension.dto.CommandArgument;
import dev.lightdream.jdaextension.dto.JDAEmbed;
import dev.lightdream.jdaextension.dto.context.CommandContext;
import dev.lightdream.jdaextension.dto.context.GuildCommandContext;
import dev.lightdream.jdaextension.dto.context.PrivateCommandContext;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.internal.utils.PermissionUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class DiscordCommand {

    public final @NotNull List<String> aliases = new ArrayList<>();
    public final @NotNull String description;
    public final @NotNull List<CommandArgument> arguments;
    public final Permission permission;
    public final boolean privateResponse;
    protected final JDAExtensionMain main;

    @SuppressWarnings("unused")
    public DiscordCommand(JDAExtensionMain main, @NotNull List<String> aliases, @NotNull String description,
                          Permission permission, boolean privateResponse, @NotNull List<CommandArgument> arguments) {
        this.main = main;
        for (String alias : aliases) {
            this.aliases.add(alias.toLowerCase());
        }
        this.description = description;
        this.arguments = arguments;
        this.permission = permission;
        this.privateResponse = privateResponse;
    }

    @SuppressWarnings("unused")
    public DiscordCommand(JDAExtensionMain main, @NotNull String alias, @NotNull String description, Permission permission,
                          boolean privateResponse, @NotNull List<CommandArgument> arguments) {
        this.main = main;
        this.aliases.add(alias.toLowerCase());
        this.description = description;
        this.arguments = arguments;
        this.permission = permission;
        this.privateResponse = privateResponse;
    }

    @SuppressWarnings("unused")
    public static void sendMessage(CommandContext context, JDAEmbed embed, boolean privateResponse) {
        context.sendMessage(embed, privateResponse);
    }

    @SuppressWarnings({"BooleanMethodIsAlwaysInverted"})
    public boolean hasPermission(Member member) {
        if (permission == null) {
            return true;
        }
        if (member == null) {
            return true;
        }
        return PermissionUtil.checkPermission(member, permission);
    }

    public void execute(SlashCommandEvent event) {


        event.deferReply(true).queue();
        if (!isMemberSafe()) {
            if (event.getMember() == null) {
                sendMessage(new PrivateCommandContext(event), main.getJDAConfig().serverCommand);
                return;
            }
        }
        if (event.getMember() == null) {
            if (!hasPermission(event.getMember())) {
                sendMessage(new PrivateCommandContext(event), main.getJDAConfig().notAllowed);
                return;
            }
            executePrivate(new PrivateCommandContext(event));
            return;
        }
        if (!hasPermission(event.getMember())) {
            sendMessage(new GuildCommandContext(event), main.getJDAConfig().notAllowed);
            return;
        }
        executeGuild(new GuildCommandContext(event));
    }

    public abstract void executeGuild(GuildCommandContext context);

    public abstract void executePrivate(PrivateCommandContext context);

    public void sendMessage(CommandContext context, JDAEmbed embed) {
        context.sendMessage(embed, privateResponse);
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