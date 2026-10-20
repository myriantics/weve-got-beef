package net.myriantics.weve_got_beef.core;

import net.minecraft.client.Minecraft;
import net.myriantics.weve_got_beef.WeveGotBeef;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class BeefManager {

    private final HashMap<String, BeefGroup> beefGroups = new HashMap<>();
    final BeefedPlayersHandler beefedPlayersRegistry = new BeefedPlayersHandler();

    private final @NotNull File workingDirectory;
    private final Minecraft minecraft;

    public BeefManager(Minecraft minecraft, @NotNull File workingDirectory) {
        this.workingDirectory = workingDirectory;
        this.minecraft = minecraft;

        this.load();
        WeveGotBeef.LOGGER.info("Loaded Beef Groups.");


        this.minecraft.services().profileResolver().fetchById()
    }

    private void load() {
        if (!this.workingDirectory.exists()) {
            return;
        }

        @Nullable File[] prospectiveBeefGroups = this.workingDirectory.listFiles((file, name) -> name.endsWith(".txt"));

        if (prospectiveBeefGroups == null) {
            return;
        }

        for (@Nullable File potentialBeefGroup : prospectiveBeefGroups) {
            if (potentialBeefGroup == null) {
                continue;
            }

            String name = potentialBeefGroup.getName();
            String id = name.substring(0, name.length() - 4);

            try {
                BeefGroup group = new BeefGroup(this, potentialBeefGroup);
                group.resetFromFile();
                this.beefGroups.put(id, group);
            } catch (IOException e) {
                WeveGotBeef.LOGGER.error("Encountered an error loading beef group [" + name + "], skipping.");
            }
        }
    }

    public void save() {
        if (!this.workingDirectory.exists() && !this.workingDirectory.mkdir()) {
            WeveGotBeef.LOGGER.error("Failed to detect or create beef group directory.");
        }

        this.beefGroups.forEach((id, beefGroup) -> {
            if (!beefGroup.file.exists()) {
                try {
                    if (beefGroup.file.delete()) {
                        beefGroup.file.createNewFile();
                    }
                } catch (IOException e) {
                    WeveGotBeef.LOGGER.error("Encountered error creating Beef Group storage file " + id);
                }
            }

            try (final PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(beefGroup.file), StandardCharsets.UTF_8))) {

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static BeefManager yoink() {
        return ((Access)Minecraft.getInstance()).weveGotBeef$yoinkBeef();
    }

    public interface Access {
        BeefManager weveGotBeef$yoinkBeef();
    }
}
