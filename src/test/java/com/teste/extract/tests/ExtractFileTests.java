package com.teste.extract.tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import com.teste.extract.ExtractFile;

public class ExtractFileTests {

	
	/**
	 * Teste de todos os numeros
	 * @author marina
	 */
	@Test
	public void testAll() {
		String allError = "src/main/resources/all.txt";
		assertEquals("123456789", ExtractFile.retornarDigitos(allError).toString().replace(", ", "").replace("-1", "?").substring(1, 10));
	}
	
	
	/**
	 * Teste de todos os numeros
	 * @author marina
	 */
	@Test
	public void checksum() {
		String allError = "src/main/resources/all.txt";
		ArrayList<Integer> entrada = ExtractFile.retornarDigitos(allError);
		assertEquals(true, ExtractFile.checksum(entrada));
	}
	
	@Test
	public void testEntrada() {
		String allError = "src/main/resources/all.txt";
		ArrayList<Integer> entrada = ExtractFile.retornarDigitos(allError);
		assertEquals("123456789 ERR", ExtractFile.testaEntrada(entrada));
	}
}