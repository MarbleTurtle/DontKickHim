package com.doNotKick;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("Do Not Kick")
public interface DoNotKickConfig extends Config
{
	@ConfigItem(
			keyName = "Scope",
			name = "Remove kicks from:",
			description = "Changes the range on who has the kick option removed from."
	)
	default KickMode scope()
	{
		return KickMode.All;
	}
}
