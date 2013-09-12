package net.gmx.nosefish.fishybalance;

import net.gmx.nosefish.fishylib.properties.Properties;
import net.canarymod.Canary;
import net.canarymod.logger.Logman;
import net.canarymod.plugin.Plugin;
import net.gmx.nosefish.fishybalance.listener.EntityListener;
import net.gmx.nosefish.fishybalance.properties.Key;

public class FishyBalance extends Plugin {
	public static Logman logger;
	public static Properties properties;

	@Override
	public void disable() {

	}

	@Override
	public boolean enable() {
		logger = getLogman();
		properties = new Properties(this);
		properties.addMissingKeys(Key.getAllKeys());
		Canary.hooks().registerListener(new EntityListener(), this);
		return true;
	}
}
