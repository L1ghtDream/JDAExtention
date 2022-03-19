package dev.lightdream.jdaextension.dto;

import java.util.ArrayList;
import java.util.Arrays;

public class JDAConfig {

    @SuppressWarnings("unused")
    public String token = "";

    public JDAEmbed notAllowed = new JDAEmbed(
            255,
            0,
            0,
            "Not Allowed",
            "",
            "You do not have the necessary permission to do this.",
            new ArrayList<>(),
            new ArrayList<>()
    );

    public JDAEmbed serverCommand = new JDAEmbed(
            255,
            0,
            0,
            "Server Required",
            "",
            "This command is required to be run in a server rather then on private messages",
            new ArrayList<>(),
            new ArrayList<>()
    );

    public JDAEmbed stats = new JDAEmbed(
            0,
            0,
            0,
            "Stats",
            "",
            "",
            Arrays.asList(new JDAField("RAM", "%ram%MB", true),
                    new JDAField("CPU", "%cpu%%", true),
                    new JDAField("Java Version", "%java%", true)),
            new ArrayList<>()
    );

}
