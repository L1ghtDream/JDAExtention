package dev.lightdream.jdaextension.dto;

import dev.lightdream.jdaextension.enums.JDAButtonType;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

@SuppressWarnings("unused")
public class JDAButton {

    public JDAButtonType type;
    public String id;
    public String text;
    public Emoji emoji;

    public JDAButton() {

    }

    public JDAButton(JDAButtonType type, String id, String text, Emoji emoji) {
        this.type = type;
        this.id = id;
        this.text = text;
        this.emoji = emoji;
    }

    public JDAButton(JDAButtonType type, String id, String text) {
        this.type = type;
        this.id = id;
        this.text = text;
    }

    public JDAButton(JDAButtonType type, String id, Emoji emoji) {
        this.type = type;
        this.id = id;
        this.emoji = emoji;
    }

    public Button getButton() {
        switch (type) {
            case PRIMARY:
                if (text == null) {
                    return Button.primary(id, emoji);
                }
                return Button.primary(id, text);
            case SECONDARY:
                if (text == null) {
                    return Button.secondary(id, emoji);
                }
                return Button.secondary(id, text);
            case SUCCESS:
                if (text == null) {
                    return Button.success(id, emoji);
                }
                return Button.success(id, text);
            case DANGER:
                if (text == null) {
                    return Button.danger(id, emoji);
                }
                return Button.danger(id, text);
            case LINK:
                if (text == null) {
                    return Button.link(id, emoji);
                }
                return Button.link(id, text);
        }
        return null;
    }

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public JDAButton clone() {
        return new JDAButton(type, id, text, emoji);
    }

    public JDAButton parse(String target, String replacement) {
        JDAButton JDAButton = clone();
        JDAButton.id = JDAButton.id.replace("%" + target + "%", replacement);
        JDAButton.text = JDAButton.text.replace("%" + target + "%", replacement);
        return JDAButton;
    }

    @Override
    public String toString() {
        return "JDAButton{" +
                "type=" + type +
                ", id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", emoji=" + emoji +
                '}';
    }
}
