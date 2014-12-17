package de.uniba.wiai.lspi.chord.service;
public interface AsynChord {
	...
	public void retrieve(Key key, ChordCallback callback);
	public void insert(Key key, Serializable entry, ChordCallback callback);
	public void remove(Key key, Serializable entry, ChordCallback callback);
	public ChordRetrievalFuture retrieveAsync(Key key);
	public ChordFuture insertAsync(Key key, Serializable entry);
	public ChordFuture removeAsync(Key key, Serializable entry);
}