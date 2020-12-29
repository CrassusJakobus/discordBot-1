package de.Main;

import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStreamReader;

import javax.security.auth.login.LoginException;

import de.Main.Listener.CommandListener;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

public class Start {
	
	public static Start INSTANCE;
	
	public ShardManager shardMan;
	private CommandManager cmdMan;

	public static void main(String[] args){
		try {
		new Start();
		}catch (LoginException | IllegalArgumentException a) {
			a.printStackTrace();
		}
	}
	
	
	public Start() throws LoginException, IllegalArgumentException  {
		INSTANCE = this;
		
		
		
		DefaultShardManagerBuilder builder = new DefaultShardManagerBuilder();
				
		builder.setToken("Njk5MzE3MDc2MzAwNTI5NzU1.XpXAXQ.acVsSfU5FyKZhdg7gWemGp9hQgA");
		builder.setActivity(Activity.playing("IfureadthisurGAY"));
		builder.setStatus(OnlineStatus.ONLINE);
		
		this.cmdMan = new CommandManager();
		
		builder.addEventListeners(new CommandListener());
		
		shardMan = builder.build();
		System.out.println("Bot Online");
		
		shutdown();
		
		
		
	}
	
	
	public void shutdown() {
		
		new Thread(() -> {
			
			String line = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			try {
				while((line = reader.readLine()) != null) {
					if(line.equalsIgnoreCase("shutdown")) {
						if(shardMan != null) {
							shardMan.setStatus(OnlineStatus.OFFLINE);
							shardMan.shutdown();
							System.out.println("Bot Offline");
						}
						
						reader.close();
						
					}else {
						System.out.println("Schreibe 'shutdown' um den Bot Abzuschalten");
					}
				}
				
			}catch (IOException a) {
				a.printStackTrace();
			}
		
		}).start();
	}
	
	public CommandManager getcmdMan() {
		return cmdMan;
	}
}
