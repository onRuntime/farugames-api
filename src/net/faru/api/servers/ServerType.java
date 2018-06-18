package net.faru.api.servers;

public enum ServerType {

	NONE("Unknown", "unknown#", "null"),
	HUB("Hub", "hub#", "/hubs/");
	
	private String name;
	private String nameID;
	private String folder;
	
	ServerType(String name, String nameID, String folder) {
		this.nameID = nameID;
		this.name = name;
		this.folder = folder;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getNameID() {
		return this.nameID;
	}
	
	public String getFolder() {
		return this.folder;
	}
	
	public static ServerType getServerType(String name) {
		for(ServerType serverType : ServerType.values()) {
			if(serverType.getName().equalsIgnoreCase(name)) {
				return serverType;
			}
		}
		return null;
	}
}
