package me.combimagnetron.sunscreen.command;

import me.combimagnetron.passport.util.data.Identifier;
import me.combimagnetron.sunscreen.SunscreenLibrary;
import me.combimagnetron.sunscreen.TestMenuTemplate;
import me.combimagnetron.sunscreen.neo.ActiveMenu;
import me.combimagnetron.sunscreen.user.SunscreenUser;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.command.CommandActor;

public class SunscreenCommand {

    @Command("sunscreen")
    public void sunscreen(CommandActor actor) {
        SunscreenUser<?> user = SunscreenLibrary.library().users().user(actor.uniqueId()).orElseThrow();
        ActiveMenu menu = new ActiveMenu(new TestMenuTemplate(), user, Identifier.of("aa"));
    }

}
