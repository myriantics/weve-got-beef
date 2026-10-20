package net.myriantics.weve_got_beef.mixin;

import net.minecraft.client.Minecraft;
import net.myriantics.weve_got_beef.core.BeefManager;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin implements BeefManager.Access {

	@Shadow
	@Final
	public File gameDirectory;

	@Unique
	private BeefManager weveGotBeef$beefManager;

	@Inject(at = @At("TAIL"), method = "<init>")
	private void weveGotBeef$initBeefManager(CallbackInfo info) {
		this.weveGotBeef$beefManager = new BeefManager((Minecraft) (Object) this, new File(this.gameDirectory, "config/weve_got_beef/groups"));
	}

	@Override
	public BeefManager weveGotBeef$yoinkBeef() {
		return this.weveGotBeef$beefManager;
	}
}