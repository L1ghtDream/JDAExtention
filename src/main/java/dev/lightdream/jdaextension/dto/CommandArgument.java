package dev.lightdream.jdaextension.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.dv8tion.jda.api.interactions.commands.OptionType;

@AllArgsConstructor
@NoArgsConstructor
public class CommandArgument {

    public OptionType type;
    public String name;
    public String description;
    public boolean required;

}
