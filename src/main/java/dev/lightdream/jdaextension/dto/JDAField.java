package dev.lightdream.jdaextension.dto;

public class JDAField {

    public String title;
    public String content;
    public boolean inline;

    public JDAField() {

    }

    public JDAField(String title, String content, boolean inline) {
        this.title = title;
        this.content = content;
        this.inline = inline;
    }

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public JDAField clone() {
        return new JDAField(title, content, inline);
    }

    public JDAField parse(String target, String replacement) {
        JDAField jdaField = clone();
        jdaField.title = jdaField.title.replace("%" + target + "%", replacement);
        jdaField.content = jdaField.content.replace("%" + target + "%", replacement);
        return jdaField;
    }

}
