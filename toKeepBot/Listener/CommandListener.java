package de.Main.Listener;

import de.Main.Start;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter {
	
	 @Override
	 public void onMessageReceived(MessageReceivedEvent event) {
		 
		 String message = event.getMessage().getContentDisplay();
				 
		if(event.isFromType(ChannelType.TEXT)) {
			TextChannel channel = event.getTextChannel();
			
			if(message.startsWith("!")) {
				String[] args = message.substring(1).split(" ");
				
				if(args.length > 0) {
					if(!Start.INSTANCE.getcmdMan().perform(args[0], event.getMember(), channel, event.getMessage())) {
						channel.sendMessage("'Unbekannter Befehl'").queue();
						
					}
				}
			}
		}
	 }
}
