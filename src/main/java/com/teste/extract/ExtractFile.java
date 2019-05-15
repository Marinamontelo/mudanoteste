package com.teste.extract;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExtractFile {
	static final int linhasPorVez = 3;

	public static String obtemFragmentoDigito(String linha, int startIndex) {
		int offsetIndex = startIndex + 3;
		int tamanhoLinha = linha.length();
		return linha.substring(startIndex, offsetIndex <= tamanhoLinha ? offsetIndex : tamanhoLinha);
	}

	public static void main(String[] args) throws IOException {
		String filePath = "src/main/resources/all.txt";
		retornarDigitos(filePath);
	}
	
	public void checksum() {
		
	}

	public static ArrayList<String> retornarDigitos(String filePath) {		
		List<String> rows;
		ArrayList<String> digitosEmNumeros = new ArrayList<String>();
		try {
			HashMap<String, Integer> numeros = new HashMap<String, Integer>();
			numeros.put("_ | ||_|", 0);
			numeros.put("|  |", 1);
			numeros.put("_  _||_", 2);
			numeros.put("_  _| _|", 3);
			numeros.put("|_|  |", 4);
			numeros.put("_ |_  _|", 5);
			numeros.put("_ |_ |_|", 6);
			numeros.put("_   |  |", 7);
			numeros.put("_ |_||_|", 8);
			numeros.put("_ |_| _|", 9);
			
			
			rows = Files.readAllLines(new File(filePath).toPath(), Charset.defaultCharset() );
			List<String> digitos = new ArrayList<String>();

			for (int linha = 0; linha < rows.size(); linha += linhasPorVez) {
				for (int digitoIndex = 0; digitoIndex < 27; digitoIndex += 3) {
					StringBuilder digito = new StringBuilder();
					digito.append(obtemFragmentoDigito(rows.get(linha), digitoIndex));
					digito.append(obtemFragmentoDigito(rows.get(linha + 1), digitoIndex));
					digito.append(obtemFragmentoDigito(rows.get(linha + 2), digitoIndex));
					digitos.add(digito.toString());
				}
			}
			
			for (String string : digitos) {
				digitosEmNumeros.add(numeros.get(string.trim()) == null ? "?" : numeros.get(string.trim()).toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return digitosEmNumeros;
	}
}