package fr.lowtix.palatraining.handlers;

import java.util.UUID;

public class TeamRequest {
	
	private UUID receiver;
	private UUID sender;
	private long at;

	public TeamRequest(UUID receiver, UUID sender, long at) {
		super();
		this.receiver = receiver;
		this.sender = sender;
		this.at = at;
	}

	public UUID getReceiver() {
		return receiver;
	}

	public void setReceiver(UUID receiver) {
		this.receiver = receiver;
	}

	public UUID getSender() {
		return sender;
	}

	public void setSender(UUID sender) {
		this.sender = sender;
	}

	public long getAt() {
		return at;
	}

	public void setAt(long at) {
		this.at = at;
	}

}
