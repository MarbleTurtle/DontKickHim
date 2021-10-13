package com.doNotKick;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("donotkick")
public interface DoNotKickConfig extends Config
{
	@ConfigItem(
			keyName = "Scope",
			name = "FC: Remove kicks from",
			description = "For Friend's Chat changes the range on who has the kick option removed from."
	)
	default KickMode scope()
	{
		return KickMode.All;
	}
}
