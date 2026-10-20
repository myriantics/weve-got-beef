package net.myriantics.weve_got_beef.core;

import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public abstract sealed class BeefEntry permits BeefEntry.Online, BeefEntry.Offline {

    final BeefGroup group;

    protected BeefEntry(BeefGroup group) {
        this.group = group;
    }

    public abstract boolean test(Entity entity);

    public static BeefEntry decode(BeefGroup group, String inputLine) throws IllegalArgumentException {
        int colonIndex = inputLine.indexOf(":");

        if (colonIndex == -1) {

        } else {
            String specifier = inputLine.substring(0, colonIndex);
            switch (specifier) {
                case "online" -> {

                }
                case "offline" -> {

                }
                case "" -> throw new IllegalArgumentException("Empty specifier detected, skipping");
                default -> throw new IllegalArgumentException("Unrecognized specifier [" + specifier + "]");
            }
        }
    }

    public abstract String encode();

    public static final class Online extends BeefEntry {

        public final UUID uuid;

        public Online(BeefGroup group, UUID uuid) {
            super(group);
            this.uuid = uuid;
        }

        private static Online decodeFromString(BeefGroup group, String input) throws IllegalArgumentException {
            return new Online(group, UUID.fromString(input.substring(0, 36)));
        }

        @Override
        public boolean test(Entity entity) {
            return false;
        }

        @Override
        public String encode() {
            @Nullable String username = this.group.manager.beefedPlayersRegistry.getUsernameFromUUID(this.uuid);

            if (username == null) {
                return this.uuid.toString();
            } else {
                return this.uuid.toString() + " //" + username;
            }
        }
    }
    public static final class Offline extends BeefEntry {

        public final String username;

        public Offline(BeefGroup group, String username) {
            super(group);
            this.username = username;
        }

        public static Offline decodeFromString(BeefGroup group, String username) {
            return new use
        }

        @Override
        public boolean test(Entity entity) {
            return false;
        }

        @Override
        public String encode() {
            return username;
        }
    }
}
