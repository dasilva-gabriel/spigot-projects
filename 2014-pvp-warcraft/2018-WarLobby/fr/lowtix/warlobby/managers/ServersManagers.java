package fr.lowtix.warlobby.managers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.bukkit.ChatColor;

import fr.lowtix.warlobby.WarLobby;
import fr.lowtix.warlobby.tasks.ServersTask;

public class ServersManagers {

	public int factionsCount = -1;
	public int pvpboxCount = -1;
	
	public final int factionsPort = 25201;
	public final int pvpboxPort = 25202;

	public ServersManagers() {
		new ServersTask().runTaskTimerAsynchronously(WarLobby.getInstance(), 20, 40);
	}
	
	public int onlinePlayers(int port) {
		try {
			
			 @SuppressWarnings("resource")
			Socket socket = new Socket();
             socket.connect(new InetSocketAddress("127.0.0.1", port), 1 * 1000);
            
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             DataInputStream in = new DataInputStream(socket.getInputStream());
            
             out.write(0xFE);
            
             StringBuilder str = new StringBuilder();
            
             int b;
             while ((b = in.read()) != -1) {
                     if (b != 0 && b > 16 && b != 255 && b != 23 && b != 24) {
                             str.append((char) b);
                     }
             }
            
             String[] data = str.toString().split(String.valueOf(ChatColor.COLOR_CHAR));
             //String motd = data[0];
             //int onlinePlayers = Integer.valueOf(data[1]);
             //int maxPlayers = Integer.valueOf(data[2]);
			
			return Integer.parseInt(data[1]);
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return -1;
	}

}
