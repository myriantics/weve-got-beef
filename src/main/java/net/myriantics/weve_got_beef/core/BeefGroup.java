package net.myriantics.weve_got_beef.core;

import com.google.common.io.Files;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BeefGroup {
    final ArrayList<UUID> beefedPlayerUUIDs = new ArrayList<>();
    final File file;
    final BeefManager manager;

    Map<UUID, String> playerNames = new HashMap<>();

    public BeefGroup(BeefManager manager, File file) {
        this.manager = manager;
        this.file = file;
    }

    public void resetFromFile() throws IOException {
        if (!this.file.exists()) {
            return;
        }

        try (BufferedReader reader = Files.newReader(this.file, StandardCharsets.UTF_8)) {
            this.beefedPlayerUUIDs.clear();
            reader.lines().forEach(line -> {
                String uuidSubstring = line.substring(0, 36);
                try {
                    UUID uuid = UUID.fromString(uuidSubstring);
                    this.beefedPlayerUUIDs.add(uuid);
                } catch (IllegalArgumentException ignored) {

                }
            });
        }
    }

    public void save(PrintWriter printWriter) {
        // String[] playerNames =
    }
}
