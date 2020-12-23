package com.doNotKick;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.MenuEntry;
import net.runelite.api.events.MenuEntryAdded;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.FriendChatManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
	name = "Do Not Kick"
)
public class DoNotKickPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private DoNotKickConfig config;

	@Inject
	private FriendChatManager friendChatManager;

	@Override
	protected void startUp() throws Exception
	{
		log.info("DoNotKick started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("DoNotKick stopped!");
	}

	@Subscribe
	public void onMenuEntryAdded(MenuEntryAdded event){
		switch(config.scope()){
			case All:
				if(friendChatManager.getRank(event.getTarget()).getValue()==-1)
					return;
				break;
			case Some:
				if(friendChatManager.getRank(event.getTarget()).getValue()<friendChatManager.getRank(client.getLocalPlayer().getName()).getValue())
					return;
				break;
			case Every:
				break;
		}
		MenuEntry[] Test = new MenuEntry[client.getMenuEntries().length-1];
		int inc=0;
		for (int x=0; x<client.getMenuEntries().length; x++){
			if(!client.getMenuEntries()[x].getOption().matches("Kick")){
				Test[inc]=client.getMenuEntries()[x];
				inc++;
			}
		}
		client.setMenuEntries(Test);
	}

	@Provides
	DoNotKickConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(DoNotKickConfig.class);
	}
}