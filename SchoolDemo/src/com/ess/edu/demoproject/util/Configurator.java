package com.ess.edu.demoproject.util;

import java.io.*;
import java.util.Properties;

import com.ess.edu.demoproject.entities.Subject;


public class Configurator {
	public static String HOME_PATH = "c:\\temp\\schooldemo";
	public static String CONFIG_FILE = "config.txt";
	
	public static String LAYOUT_KEY = "layout";
	public static String SUBJECT_NAMES_KEY = "subjectNames";
	
	
	public static String LAYOUT_FLAT = "flat";
	public static String LAYOUT_TABS = "tabs";
	public static String LAYOUT_COMPACT = "compact";

	private static Properties props = new  Properties();
	
	private static Configurator theInstance = null;

	public static Configurator getInstance() {
		if (theInstance  == null)
			theInstance = new Configurator();
			
		return theInstance;
	}
	
	
	private Configurator () {
		// empty
	}

	public static void configureApplication () throws Exception {
		loadConfig();
		
		configureSubjectNames();
	}
	
	private static void configureSubjectNames() throws InvalidSubjectNameException {
		String sNames = Configurator.getInstance().getProperty(Configurator.SUBJECT_NAMES_KEY);
		String[] aNames = sNames.split (",");
		for (String s: aNames) {
			Subject.addTitle (s.trim());
		}
	}
	
	public String getProperty (String key) {
		return (String)props.get (key);
	}

	public String setProperty (String key, String value) {
		return (String)props.setProperty(key, value);
	}
	
	public static void loadConfig () {
		try {
			props.load (new FileInputStream (getConfigFileName()));
		} catch (IOException e) {
			initDefaultConfig ();
			saveConfig ();
		}

	}

	private static void initDefaultConfig () {
		props.setProperty (LAYOUT_KEY, LAYOUT_FLAT);
		props.setProperty (SUBJECT_NAMES_KEY, "Math, Physics, Chemistry, Literature, IT");
	}
	
	private static void saveConfig () {
		File f = new File (HOME_PATH);
		f.mkdirs();
		
		boolean toSave = true;
		
		FileOutputStream fos = null;
		f = new File (getConfigFileName());
		if (! f.exists()) {
			try {
				toSave = f.createNewFile();
				if (toSave) {
					fos = new FileOutputStream (f);
					props.store (fos, null);
					fos.flush();
					fos.close();
				}
			} catch (Exception e) {
				System.out.println("Config properties not saved.");
				e.printStackTrace();
			}
		}
		
	}
	
	private static String getConfigFileName() {
		return HOME_PATH + File.separator + CONFIG_FILE;
	}
}
