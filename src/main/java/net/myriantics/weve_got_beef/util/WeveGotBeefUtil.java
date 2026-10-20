package net.myriantics.weve_got_beef.util;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Util;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public abstract class WeveGotBeefUtil {

    public static boolean isValidUsername(String name) {
        for (char c : name.toCharArray()) {
            if (!validUsernameChar(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean validUsernameChar(final char c) {
        return c == '_' || c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z' || c >= '0' && c <= '9';
    }

    private static CompletableFuture<String> getPlayerNameForUUID(UUID uuid) {
        return CompletableFuture.supplyAsync(() -> Minecraft.getInstance().services().sessionService().fetchProfile(this.user.getProfileId(), true), Util.nonCriticalIoPool());
    }
}
