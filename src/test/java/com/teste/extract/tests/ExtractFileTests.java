package com.teste.extract.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.teste.extract.ExtractFile;

public class ExtractFileTests {

	static final String diretorio = "src/main/resources/";
	
	/**
	 * Teste de todos os numeros
	 * @author marina
	 */
	@Test
	public void testAll() {
		String allError = diretorio + "file.txt";
		assertEquals("123456789", ExtractFile.retornarDigitos(allError).toString().replace(", ", "").replace("-1", "?").substring(1, 10));
	}
}