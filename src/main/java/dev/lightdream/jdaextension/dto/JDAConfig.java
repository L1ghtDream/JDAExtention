package dev.lightdream.jdaextension.dto;

import java.util.ArrayList;
import java.util.Arrays;

public class JDAConfig {

    @SuppressWarnings("unused")
    public String token = "";
    public JdaEmbed usage = new JdaEmbed(0, 0, 0, "%command%", "", "+%command% %usage%", new ArrayList<>(), new ArrayList<>());

    public JdaEmbed helpEmbed = new JdaEmbed(
            0,
            0,
            0,
            "Help",
            "",
            "+help\n" + "+link [username]\n" + "+unlink <username>\n" + "+changePassword <username> [newPassword] - In DMs\n" + "+accounts <discordID>\n" + "+stats\n" + "+unregister <discordID>\n" + "\n" + "[] - Mandatory arguments\n" + "<> - Optional / Contextual arguments",
            new ArrayList<>(),
            new ArrayList<>()
    );

    public JdaEmbed notAllowed = new JdaEmbed(
            255,
            0,
            0,
            "Not Allowed",
            "",
            "You do not have the necessary permission to do this.",
            new ArrayList<>(),
            new ArrayList<>()
    );

    public JdaEmbed serverCommand = new JdaEmbed(
            255,
            0,
            0,
            "Server Required",
            "",
            "This command is required to be run in a server rather then on private messages",
            new ArrayList<>(),
            new ArrayList<>()
    );

    public JdaEmbed stats = new JdaEmbed(
            0,
            0,
            0,
            "Stats",
            "",
            "",
            Arrays.asList(new JdaField("RAM", "%ram%MB", true),
                    new JdaField("CPU", "%cpu%%", true),
                    new JdaField("Java Version", "%java%", true)
            ),
            new ArrayList<>()
    );

}
