package com.doNotKick;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class DoNotKickPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(DoNotKickPlugin.class);
		RuneLite.main(args);
	}
}