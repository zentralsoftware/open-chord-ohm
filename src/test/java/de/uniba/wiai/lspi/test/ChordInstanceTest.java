package de.uniba.wiai.lspi.test;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.uniba.wiai.lspi.chord.console.command.entry.Key;
import de.uniba.wiai.lspi.chord.console.command.entry.Value;
import de.uniba.wiai.lspi.chord.data.URL;
import de.uniba.wiai.lspi.chord.service.Chord;
import de.uniba.wiai.lspi.chord.service.PropertiesLoader;
import de.uniba.wiai.lspi.chord.service.Report;
import de.uniba.wiai.lspi.chord.service.ServiceException;
import de.uniba.wiai.lspi.chord.service.impl.ChordImpl;

public class ChordInstanceTest {
	
	private List<Chord> chords = new ArrayList<Chord>();
	private Chord initChord;
	private String protocol = URL.KNOWN_PROTOCOLS.get(URL.SOCKET_PROTOCOL); 
	private int ROOT_PORT = 8080;
	private int NUMBER_OF_MEMBERS = 4;
	
	@BeforeClass
	public static void init()
	{
		PropertiesLoader.loadPropertyFile(); 
	}
	
	@Before
	public void setup() throws MalformedURLException, ServiceException 
	{
		initChord = getRootChord();
		for (int i=1;i<=NUMBER_OF_MEMBERS;i++)
		{
			Chord chord = getMemberChord(ROOT_PORT + i);
			chords.add(chord);
		}
	}
	
	private URL getBootstrapURL(int port) throws MalformedURLException
	{
		URL url = null; 
		url = new URL(protocol + "://localhost:"+ port +"/");
		return url;
	}
	
	private URL getBootstrapURL() throws MalformedURLException
	{
		return getBootstrapURL(ROOT_PORT);
	}
	
	private Chord getRootChord(URL bootstrapURL) throws ServiceException, MalformedURLException
	{
		Chord chord = new ChordImpl(); 
		chord.create(bootstrapURL);
		return chord;
	}
	
	private Chord getRootChord() throws ServiceException, MalformedURLException
	{
		return getRootChord(getBootstrapURL());
	}
	
	private Chord getMemberChord(int port) throws ServiceException, MalformedURLException
	{
		URL localURL = null; 
		localURL = new URL(protocol + "://localhost:"+ port +"/");
		Chord chord = new ChordImpl();
		chord.join(localURL, getBootstrapURL());
		return chord;		
	}
	
	@Test
	public void createRing() throws ServiceException, MalformedURLException
	{
		Assert.assertNotNull(initChord);
		Assert.assertEquals(chords.size(), NUMBER_OF_MEMBERS);
		System.err.println(initChord.getID());
		Report rootReport = (Report) initChord;
		System.err.println(rootReport.printFingerTable());
		System.err.println(rootReport.printPredecessor());
		System.err.println(rootReport.printReferences());
		System.err.println(rootReport.printSuccessorList());
		for (Chord chord:chords)
		{
			System.err.println(chord.getID());
			Report report = (Report) chord;
			System.err.println(report.printFingerTable());
			System.err.println(report.printPredecessor());
			System.err.println(report.printReferences());
			System.err.println(report.printSuccessorList());			
		}
	}
		
	@Test
	public void insert() throws ServiceException
	{
		Chord chord = chords.get(1);
		Key key = new Key("key");
		Value value1 = new Value("value1");
		chord.insert(key, value1);
		Set<Serializable> retrieved = chord.retrieve(key);
        Object[] values = retrieved.toArray(new Object[retrieved.size()]); 
        Assert.assertEquals(1, values.length);
        for (int i=0;i<values.length;i++)
        {
        	System.err.println(values[i]);
        }
	}
	
	@Test
	public void insertNodeLeave() throws ServiceException, InterruptedException
	{
		Chord chord = chords.get(1);
		Key key = new Key("key");
		Value value1 = new Value("value1");
		chord.insert(key, value1);
		Set<Serializable> retrieved = chord.retrieve(key);
        Object[] values = retrieved.toArray(new Object[retrieved.size()]); 
        Assert.assertEquals(1, values.length);
        for (int i=0;i<values.length;i++)
        {
        	System.err.println(values[i]);
        }
		for (Chord c:chords)
		{
			System.err.println(c.getID());
			Report report = (Report) c;
			System.err.println(report.printFingerTable());
			System.err.println(report.printPredecessor());
			System.err.println(report.printReferences());
			System.err.println(report.printSuccessorList());	
			System.err.println(report.printEntries());
		}      
		chord.leave();
		int waitingTime = 20*1000;
		System.err.println("node leave, wait for " + waitingTime/1000 + "s");
		Thread.sleep(60*1000);
		
		for (Chord c:chords)
		{
			System.err.println(c.getID());
			Report report = (Report) c;
			System.err.println(report.printFingerTable());
			System.err.println(report.printPredecessor());
			System.err.println(report.printReferences());
			System.err.println(report.printSuccessorList());	
			System.err.println(report.printEntries());
		}      
		Set<Serializable> retrieved2 = chords.get(0).retrieve(key);
        Object[] values2 = retrieved2.toArray(new Object[retrieved2.size()]); 
        Assert.assertEquals(1, values2.length);
        for (int i=0;i<values2.length;i++)
        {
        	System.err.println(values2[i]);
        }		
	}	
	
	@Test
	public void insertMultiKey() throws ServiceException
	{
		Chord chord = chords.get(1);
		Key key = new Key("multikey");
		Value value1 = new Value("value1");
		Value value2 = new Value("value2");
		chord.insert(key, value1);
		chord.insert(key, value2);
		Set<Serializable> retrieved = chord.retrieve(key);
        Object[] values = retrieved.toArray(new Object[retrieved.size()]); 
        Assert.assertEquals(2, values.length);
        for (int i=0;i<values.length;i++)
        {
        	System.err.println(values[i]);
        }
	}
	
	@After
	public void tearUp() throws ServiceException
	{
		for (Chord chord:chords)
		{
			chord.leave();
		}
		initChord.leave();
	}
	
}
