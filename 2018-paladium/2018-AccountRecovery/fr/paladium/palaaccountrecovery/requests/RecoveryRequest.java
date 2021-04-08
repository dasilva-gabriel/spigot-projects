package fr.paladium.palaaccountrecovery.requests;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.paladium.palaaccountrecovery.enumeration.RequestsType;

public class RecoveryRequest {
	
	private String id;
	private RequestsType type;
	private long time;
	private UUID from;
	private UUID to;

	public RecoveryRequest(String id, RequestsType type, UUID from, UUID to) {
		super();
		this.id = id;
		this.type = type;
		this.time = System.currentTimeMillis();
		this.from = from;
		this.to = to;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public RequestsType getType() {
		return type;
	}

	public void setType(RequestsType type) {
		this.type = type;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public UUID getFrom() {
		return from;
	}

	public void setFrom(UUID from) {
		this.from = from;
	}

	public UUID getTo() {
		return to;
	}

	public void setTo(UUID to) {
		this.to = to;
	}

	public boolean toIsOnline() {
		return (Bukkit.getPlayer(getTo()) != null && Bukkit.getPlayer(getTo()).isOnline());
	}
	
	public Player getToPlayer() {
		if (toIsOnline()) {
			return Bukkit.getPlayer(getTo());
		}
		return null;
	}

}
