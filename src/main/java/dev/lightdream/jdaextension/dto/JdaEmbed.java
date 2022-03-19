package dev.lightdream.jdaextension.dto;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.interactions.components.Button;
import net.dv8tion.jda.api.requests.restaction.MessageAction;

import java.util.ArrayList;
import java.util.List;

public class JdaEmbed {

    public int red;
    public int green;
    public int blue;
    public String title;
    public String thumbnail;
    public String description;
    public List<JdaField> fields;
    public List<JDAButton> jdaButtons;
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private List<Button> buttons = new ArrayList<>();

    @SuppressWarnings("unused")
    public JdaEmbed() {

    }

    public JdaEmbed(int red, int green, int blue, String title, String thumbnail, String description, List<JdaField> fields, List<JDAButton> jdaButtons) {
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
    public static JdaEmbed red(String title, String description) {
        return new JdaEmbed(
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
    public static JdaEmbed green(String title, String description) {
        return new JdaEmbed(
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
    public static JdaEmbed blue(String title, String description) {
        return new JdaEmbed(
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
    public static JdaEmbed black(String title, String description) {
        return new JdaEmbed(
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
    public JdaEmbed parse(String target, String replacement) {
        JdaEmbed parsed = clone();
        parsed.description = parsed.description.replace("%" + target + "%", replacement);
        parsed.thumbnail = parsed.thumbnail.replace("%" + target + "%", replacement);
        parsed.title = parsed.title.replace("%" + target + "%", replacement);

        List<JdaField> fields = new ArrayList<>();
        parsed.fields.forEach(field -> fields.add(field.parse(target, replacement)));
        parsed.fields = fields;

        List<JDAButton> JDAButtons = new ArrayList<>();
        parsed.jdaButtons.forEach(JDAButton -> JDAButtons.add(JDAButton.parse(target, replacement)));
        parsed.jdaButtons = JDAButtons;
        return parsed;
    }

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public JdaEmbed clone() {
        List<JdaField> fields = new ArrayList<>();
        this.fields.forEach(field -> fields.add(field.clone()));
        return new JdaEmbed(red, green, blue, title, thumbnail, description, fields, jdaButtons);
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
    public JdaEmbed parseMessageAction() {
        buttons = new ArrayList<>();

        this.jdaButtons.forEach(JDAButton -> buttons.add(JDAButton.getButton()));

        return this;
    }

    @SuppressWarnings("unused")
    public List<Button> getButtons() {
        return buttons;
    }
}
