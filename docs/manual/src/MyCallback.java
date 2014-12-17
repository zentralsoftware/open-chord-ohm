public class MyCallback implements de.uniba.wiai.lspi.chord.service.ChordCallback{
	public void retrieved(Key key, Set<Serializable> entries, Throwable t){
		...
	}
	public void inserted(Key key, Serializable entry, Throwable t){
		...
	}
	public void removed(Key key, Serializable entry, Throwable t){
		if (t == null) {
			System.out.println("Successfully removed " 
				+ entry + " with key " 
				+ key); 
		} else {
			System.err.println("Removal of " 
				+ entry + " with key " 
				+ key + " failed!");
			t.printStackTrace();  			
		}
	}
}