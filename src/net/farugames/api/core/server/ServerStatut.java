package net.farugames.api.core.server;

public enum ServerStatut {
	UNREGISTERED(),
	REGISTERED(),
	LOBBY(),
	STARTING(),
	INGAME(),
	FINISH(),
	DELETE();
}
