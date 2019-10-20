package com.holin.util;

import org.junit.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {

	private Properties properties = new Properties();

	private static TestProperties instance = null;

	private TestProperties(){
		try {
			properties.load(new FileInputStream(new File("./application.properties")));
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail("Ошибка при чтении файла");
		}
	}

	public static TestProperties getInstance(){
		if (instance == null){
			instance = new TestProperties();
		}
		return instance;
	}

	public Properties getProperties(){
		return properties;
	}
}
