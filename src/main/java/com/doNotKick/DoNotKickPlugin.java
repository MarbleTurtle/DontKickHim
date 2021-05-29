package com.doNotKick;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.FriendsChatManager;
import net.runelite.api.MenuEntry;
import net.runelite.api.clan.ClanChannel;
import net.runelite.api.clan.ClanSettings;
import net.runelite.api.events.MenuEntryAdded;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.util.Text;

import java.util.ArrayList;

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
		final FriendsChatManager friendsChatManager = client.getFriendsChatManager();
		if(friendsChatManager!=null) {
			if (!event.getTarget().isEmpty() && friendsChatManager.findByName(Text.removeTags(event.getTarget())) != null) {
				switch (config.scope()) {
					case All:
						if (friendsChatManager.findByName(Text.removeTags(event.getTarget())).getRank().getValue() == -1)
							return;
						break;
					case Some:
						if (friendsChatManager.findByName(Text.removeTags(event.getTarget())).getRank().getValue() < friendsChatManager.findByName(client.getLocalPlayer().getName()).getRank().getValue())
							return;
						break;
					case Every:
						break;
				}
				ArrayList<MenuEntry> Test = new ArrayList<MenuEntry>();

				for (MenuEntry entry : client.getMenuEntries()) {
					if (!entry.getOption().matches("Kick"))
						Test.add(entry);
				}
				MenuEntry[] convertArray = new MenuEntry[Test.size()];
				for (int x = 0; x < Test.size(); x++) {
					convertArray[x] = Test.get(x);
				}
				client.setMenuEntries(convertArray);
			}
		}
		final ClanChannel clanChannel= client.getClanChannel();
		if(clanChannel!=null){
			if(!event.getTarget().isEmpty()&&clanChannel.findMember(Text.removeTags(event.getTarget()))!=null){
				System.out.println(clanChannel.findMember(Text.removeTags(event.getTarget())).getRank());
			}
		}
		final ClanChannel guestChannel= client.getGuestClanChannel();
		if(guestChannel!=null){
			if(!event.getTarget().isEmpty()&&guestChannel.findMember(Text.removeTags(event.getTarget()))!=null){
				System.out.println(guestChannel.findMember(Text.removeTags(event.getTarget())).getRank());
			}
		}
	}

	@Provides
	DoNotKickConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(DoNotKickConfig.class);
	}
}
