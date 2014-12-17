package de.uniba.wiai.lspi.chord.service;
public interface ChordRetrievalFuture extends ChordFuture {
	public Set<Serializable> getResult() throws ServiceException,
			InterruptedException;
}