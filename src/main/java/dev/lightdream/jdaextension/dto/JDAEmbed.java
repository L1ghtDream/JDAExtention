package dev.lightdream.jdaextension.dto;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.interactions.components.Button;
import net.dv8tion.jda.api.requests.restaction.MessageAction;

import java.util.ArrayList;
import java.util.List;

public class JDAEmbed {

    public int red;
    public int green;
    public int blue;
    public String title;
    public String thumbnail;
    public String description;
    public List<JDAField> fields;
    public List<JDAButton> jdaButtons;
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private List<Button> buttons = new ArrayList<>();

    @SuppressWarnings("unused")
    public JDAEmbed() {

    }

    public JDAEmbed(int red, int green, int blue, String title, String thumbnail, String description, List<JDAField> fields, List<JDAButton> jdaButtons) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.title = title;
        this.thumbnail = thumbnail;
        this.description = description;
        this.fields = fields;
        this.jdaButtons = jdaButtons;
    }

    @SuppressWarnings("unused")
    public static JDAEmbed red(String title, String description) {
        return new JDAEmbed(
                255,
                0,
                0,
                title,
                "",
                description,
                new ArrayList<>(),
                new ArrayList<>()
        );

    }

    @SuppressWarnings("unused")
    public static JDAEmbed green(String title, String description) {
        return new JDAEmbed(
                0,
                255,
                0,
                title,
                "",
                description,
                new ArrayList<>(),
                new ArrayList<>()
        );
    }

    @SuppressWarnings("unused")
    public static JDAEmbed blue(String title, String description) {
        return new JDAEmbed(
                0,
                0,
                255,
                title,
                "",
                description,
                new ArrayList<>(),
                new ArrayList<>()
        );
    }

    @SuppressWarnings("unused")
    public static JDAEmbed black(String title, String description) {
        return new JDAEmbed(
                0,
                0,
                0,
                title,
                "",
                description,
                new ArrayList<>(),
                new ArrayList<>()
        );
    }

    @SuppressWarnings("unused")
    public JDAEmbed parse(String target, String replacement) {
        JDAEmbed parsed = clone();
        parsed.description = parsed.description.replace("%" + target + "%", replacement);
        parsed.thumbnail = parsed.thumbnail.replace("%" + target + "%", replacement);
        parsed.title = parsed.title.replace("%" + target + "%", replacement);

        List<JDAField> fields = new ArrayList<>();
        parsed.fields.forEach(field -> fields.add(field.parse(target, replacement)));
        parsed.fields = fields;

        List<JDAButton> JDAButtons = new ArrayList<>();
        if (parsed.jdaButtons == null) {
            parsed.jdaButtons = new ArrayList<>();
        }
        parsed.jdaButtons.forEach(JDAButton -> JDAButtons.add(JDAButton.parse(target, replacement)));
        parsed.jdaButtons = JDAButtons;
        return parsed;
    }

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public JDAEmbed clone() {
        List<JDAField> fields = new ArrayList<>();
        this.fields.forEach(field -> fields.add(field.clone()));
        return new JDAEmbed(red, green, blue, title, thumbnail, description, fields, jdaButtons);
    }

    @Override
    public String toString() {
        return "JdaEmbed{" + "red=" + red + ", green=" + green + ", blue=" + blue + ", title='" + title + '\'' + ", thumbnail='" + thumbnail + '\'' + ", description='" + description + '\'' + ", fields=" + fields + ", buttons=" + jdaButtons + '}';
    }

    public EmbedBuilder build() {
        EmbedBuilder embed = new EmbedBuilder();

        if (!thumbnail.equals("")) {
            embed.setThumbnail(thumbnail);
        }
        fields.forEach(field -> embed.addField(field.title, field.content, field.inline));
        if (title != null) {
            embed.setTitle(title, null);
        }
        embed.setColor(new java.awt.Color(red, green, blue));
        if (description != null) {
            embed.setDescription(description);
        }
        embed.setFooter("Author: LightDream#4379");

        return embed;
    }

    @SuppressWarnings("unused")
    public MessageAction buildMessageAction(MessageChannel channel) {
        List<net.dv8tion.jda.api.interactions.components.Button> buttons = new ArrayList<>();

        this.jdaButtons.forEach(JDAButton -> buttons.add(JDAButton.getButton()));

        return channel.sendMessageEmbeds(build().build()).setActionRow(buttons);
    }

    /**
     * Do not forget to use setActionRow(buttons);
     *
     * @return Embed with all the buttons parsed
     */
    @SuppressWarnings("unused")
    public JDAEmbed parseMessageAction() {
        buttons = new ArrayList<>();

        this.jdaButtons.forEach(JDAButton -> buttons.add(JDAButton.getButton()));

        return this;
    }

    @SuppressWarnings("unused")
    public List<Button> getButtons() {
        return buttons;
    }
}
