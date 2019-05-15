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
		String allError = diretorio + "all.txt";
		assertEquals("1234?678?",  String.join("",ExtractFile.retornarDigitos(allError)));
	}
	
	/**
	 * Teste numero 4
	 * @author marina
	 */
	@Test
	public void testFour() {
		String numberFour = diretorio + "4.txt";
		assertEquals("444444444", String.join("",ExtractFile.retornarDigitos(numberFour)));
	}
	
	/**
	 * Teste numero 7
	 * @author marina
	 */
	@Test
	public void testSeven() {
		String numberSeven = diretorio + "7.txt";
		assertEquals("777777777", String.join("",ExtractFile.retornarDigitos(numberSeven)));
	}
	
	/**
	 * Teste numero 3
	 * @author marina
	 */
	@Test
	public void testThree() {
		String numberThree = diretorio + "3.txt";
		assertEquals("333333333",  String.join("",ExtractFile.retornarDigitos(numberThree)));
	}
}