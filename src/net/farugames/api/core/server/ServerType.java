package net.farugames.api.core.server;

public enum ServerType {
	
	//PROXY("Proxy", "pxy#", "\\template\\proxy\\", null),
	HUB("hub", "hub", "template/hub", 100),
	
	BDB("bdb", "bdb", "template/bdb", 24);
	
	private String name;
	private String nameId;
	private String folder;
	private Integer slots;
	
	ServerType(String name, String nameId, String folder, Integer slots) {
		this.nameId = nameId;
		this.name = name;
		this.folder = folder;
		this.slots = slots;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getNameId() {
		return this.nameId;
	}
		
	public String getFolder() {
		return this.folder;
	}
		
	public Integer getSlots() {
		return this.slots;
	}
		
	public static ServerType getServerType(String name) {
		for(ServerType serverType : ServerType.values()) {
			if(serverType.getName().toUpperCase().equalsIgnoreCase(name)) {
				return serverType;
			}
		}
		return null;
	}
}
