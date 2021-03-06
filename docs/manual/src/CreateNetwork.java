public static void main(String[] args) {
	de.uniba.wiai.lspi.chord.service.PropertiesLoader.loadPropertyFile(); 
	String protocol = URL.KNOWN_PROTOCOLS.get(URL.SOCKET_PROTOCOL); 
	URL localURL = null; 
	try {
		localURL = new URL(protocol + "://localhost:8080/");
	} catch (MalformedURLException e){
		throw new RuntimeException(e); 
	}
	Chord chord = new de.uniba.wiai.lspi.chord.service.impl.ChordImpl(); 
	try {
		chord.create(localURL);
	} catch (ServiceException e) {
		throw new RuntimeException("Could not create DHT!", e); 
	}
	...
}