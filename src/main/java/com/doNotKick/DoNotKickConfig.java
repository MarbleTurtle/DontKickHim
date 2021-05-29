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
	@ConfigItem(
			keyName = "Scope2",
			name = "CC: Remove kicks from guest",
			description = "For Clan Chat removes the kick option from guest."
	)
	default boolean guest()
	{
		return false;
	}
	@ConfigItem(
			keyName = "Scope2",
			name = "CC: Remove bans from guest",
			description = "For Clan Chat removes the ban option from guest."
	)
	default boolean guest2()
	{
		return false;
	}
}
