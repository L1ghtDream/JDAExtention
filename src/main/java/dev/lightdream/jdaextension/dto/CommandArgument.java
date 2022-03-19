package dev.lightdream.jdaextension.dto;

import net.dv8tion.jda.api.interactions.commands.OptionType;

public class CommandArgument {

    public OptionType type;
    public String name;
    public String description;
    public boolean required;

    public CommandArgument() {

    }

    public CommandArgument(OptionType type, String name, String description, boolean required) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.required = required;
    }

}
