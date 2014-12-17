package de.uniba.wiai.lspi.test;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import de.uniba.wiai.lspi.chord.com.Entry;
import de.uniba.wiai.lspi.chord.data.ID;

public class MapDbTest {

	private DB db;
	private Map<ID, Set<Entry>> entries = null;
	
	private DB db1;
	
	@Before
	public void init()
	{
		db = DBMaker
				.newMemoryDirectDB()
				.make(); 		
		db1 = DBMaker
				.newMemoryDirectDB()
				.make(); 				
	}
	
	@Test
	public void createMap()
	{
		entries = db.getHashMap("repo");
		ID id = new ID("id".getBytes());
		Set<Entry> entrySet = new HashSet<Entry>();
		Entry entry = new Entry(id, "value");
		entrySet.add(entry);
		entries.put(id, entrySet);
		
		Set<Entry> retrieved = entries.get(id);
		
		for (Entry e:retrieved)
		{
			System.err.println(e);
		}
	}
	
	@Test
	public void createMap1()
	{
		entries = db1.getHashMap("repo");
		ID id = new ID("id".getBytes());
		Set<Entry> entrySet = new HashSet<Entry>();
		Entry entry = new Entry(id, "value");
		entrySet.add(entry);
		entries.put(id, entrySet);
		
		Set<Entry> retrieved = entries.get(id);
		
		for (Entry e:retrieved)
		{
			System.err.println(e);
		}
	}	
	
	@Test
	public void createMultiDB()
	{
		Map<String, String> map = db.getHashMap("repo");
		map.put("key", "value");
		System.err.println(map.get("key"));
		Map<String, String> map1 = db1.getHashMap("repo");
		map1.put("key", "value1");
		System.err.println(map1.get("key"));
		System.err.println(map.get("key"));
	}
	public void tearUp()
	{
		db.close();
		db1.close();
	}
}
