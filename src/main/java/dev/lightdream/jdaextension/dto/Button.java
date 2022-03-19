package dev.lightdream.jdaextension.dto;

import dev.lightdream.jdaextension.enums.JDAButtonType;
import net.dv8tion.jda.api.entities.Emoji;

@SuppressWarnings("unused")
public class Button {

    public JDAButtonType type;
    public String id;
    public String text;
    public Emoji emoji;

    public Button() {

    }

    public Button(JDAButtonType type, String id, String text, Emoji emoji) {
        this.type = type;
        this.id = id;
        this.text = text;
        this.emoji = emoji;
    }

    public Button(JDAButtonType type, String id, String text) {
        this.type = type;
        this.id = id;
        this.text = text;
    }

    public Button(JDAButtonType type, String id, Emoji emoji) {
        this.type = type;
        this.id = id;
        this.emoji = emoji;
    }

    public net.dv8tion.jda.api.interactions.components.Button getButton() {
        switch (type) {
            case PRIMARY:
                if (text == null) {
                    return net.dv8tion.jda.api.interactions.components.Button.primary(id, emoji);
                }
                return net.dv8tion.jda.api.interactions.components.Button.primary(id, text);
            case SECONDARY:
                if (text == null) {
                    return net.dv8tion.jda.api.interactions.components.Button.secondary(id, emoji);
                }
                return net.dv8tion.jda.api.interactions.components.Button.secondary(id, text);
            case SUCCESS:
                if (text == null) {
                    return net.dv8tion.jda.api.interactions.components.Button.success(id, emoji);
                }
                return net.dv8tion.jda.api.interactions.components.Button.success(id, text);
            case DANGER:
                if (text == null) {
                    return net.dv8tion.jda.api.interactions.components.Button.danger(id, emoji);
                }
                return net.dv8tion.jda.api.interactions.components.Button.danger(id, text);
            case LINK:
                if (text == null) {
                    return net.dv8tion.jda.api.interactions.components.Button.link(id, emoji);
                }
                return net.dv8tion.jda.api.interactions.components.Button.link(id, text);
        }
        return null;
    }

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public Button clone() {
        return new Button(type, id, text, emoji);
    }

    public Button parse(String target, String replacement) {
        Button button = clone();
        button.id = button.id.replace("%" + target + "%", replacement);
        button.text = button.text.replace("%" + target + "%", replacement);
        return button;
    }


}
