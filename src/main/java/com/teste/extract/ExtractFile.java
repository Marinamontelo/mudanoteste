package com.teste.extract;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ExtractFile {

	static final int linhasPorVez = 3;
	static final HashMap<String, Integer> numerosMap = new HashMap<String, Integer>();
	static final HashMap<Integer, String> patternMap = new HashMap<Integer, String>();

	
	public static String obtemFragmentoDigito(String linha, int startIndex) {
		int offsetIndex = startIndex + 3;
		int tamanhoLinha = linha.length();
		return linha.substring(startIndex, offsetIndex <= tamanhoLinha ? offsetIndex : tamanhoLinha);
	}

	public static void main(String[] args) throws IOException {
		String filePath = "src/main/resources/file.txt"; 
		String outPutFilePath = "src/main/resources/fileOut.txt"; 	

		ArrayList digitos = retornarDigitos(filePath);
		ArrayList<List<Integer>> digitosSegmentados = segmentarDigitos(digitos);
		for (List<Integer> entrada : digitosSegmentados) {
			File file = new File(outPutFilePath);
			FileWriter fr = new FileWriter(file, true);
			fr.write(testaEntrada(entrada));
			fr.write("\n");
			fr.close();
			;
		}

	}

	public static ArrayList<List<Integer>> segmentarDigitos(final List<Integer> digitos) {
		ArrayList<List<Integer>> segmentos = new ArrayList<List<Integer>>();
		for (int i = 0; i < digitos.size(); i += 9) {
			segmentos.add(new ArrayList<Integer>(digitos.subList(i, i + 9)));
		}

		return segmentos;
	}

	public static String testaEntrada(List<Integer> entrada) {
		StringBuilder sb = new StringBuilder();
		sb.append(entrada.toString().replace(", ", "").replace("-1", "?").substring(1, 10));

		if (entrada.contains(-1)) {
            return sb.append(" ILL").toString();
		}
		if (checksum(entrada)) {
			return sb.toString();
		}
        return sb.append(" ERR").toString();
	}

	public static boolean checksum(List<Integer> entrada) {
		int multiplicador = 9;
		int resultado = 0;
		for (Integer i : entrada) {
			resultado += i * multiplicador;
			multiplicador--;
		}
		if (resultado % 11 == 0) {
			return true;
		}
		return false;
	}

	public static ArrayList<Integer> retornarDigitos(String filePath) {
		numerosMap.put("_ | ||_|", 0);
		numerosMap.put("|  |", 1);
		numerosMap.put("_  _||_", 2);
		numerosMap.put("_  _| _|", 3);
		numerosMap.put("|_|  |", 4);
		numerosMap.put("_ |_  _|", 5);
		numerosMap.put("_ |_ |_|", 6);
		numerosMap.put("_   |  |", 7);
		numerosMap.put("_ |_||_|", 8);
		numerosMap.put("_ |_| _|", 9);

		patternMap.put(0, "_ | ||_|");
		patternMap.put(1, "|  |");
		patternMap.put(2, "_  _||_");
		patternMap.put(3, "_  _| _|");
		patternMap.put(4, "|_|  |");
		patternMap.put(5, "_ |_  _|");
		patternMap.put(6, "_ |_ |_|");
		patternMap.put(7, "_   |  |");
		patternMap.put(8, "_ |_||_|");
		patternMap.put(9, "_ |_| _|");
		
		List<String> rows;
		ArrayList<Integer> digitosEmNumeros = new ArrayList<Integer>();
		try {
			rows = Files.readAllLines(new File(filePath).toPath(), Charset.defaultCharset());
			rows.removeAll(Arrays.asList("", null));
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
				digitosEmNumeros.add(numerosMap.get(string.trim()) == null ? -1 : numerosMap.get(string.trim()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(digitosEmNumeros);
		return digitosEmNumeros;
	}
}
