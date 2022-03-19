package dev.lightdream.jdaextension.dto;

public class JdaField {

    public String title;
    public String content;
    public boolean inline;

    public JdaField() {

    }

    public JdaField(String title, String content, boolean inline) {
        this.title = title;
        this.content = content;
        this.inline = inline;
    }

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public JdaField clone() {
        return new JdaField(title, content, inline);
    }

    public JdaField parse(String target, String replacement) {
        JdaField jdaField = clone();
        jdaField.title = jdaField.title.replace("%" + target + "%", replacement);
        jdaField.content = jdaField.content.replace("%" + target + "%", replacement);
        return jdaField;
    }

}
