package de.uniba.wiai.lspi.chord.service;

public interface ChordCallback {
	public void retrieved(Key key, Set<Serializable> entries, Throwable t);
	public void inserted(Key key, Serializable entry, Throwable t); 
	public void removed(Key key, Serializable entry, Throwable t); 
}