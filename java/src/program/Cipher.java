package program;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Descriptografa a frase utilizando a cifra de cesar e retorna o texto descriptografado
public class Cipher {
	
	private static final List<Character> alphabet = new ArrayList<>(
			Arrays.asList('a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'));
	
	public static String decipher(Integer salt, String cipher){
		 String deciphered = "";
		 cipher.toLowerCase();
		 Integer cipherSize = cipher.length();
		 for(int i = 0; i < cipherSize; i++) {
			 Character letter = cipher.charAt(i);
			 deciphered += setSalt(letter,salt);
			 
		 }
		 
		 return	 deciphered;
	}
	
	private static Character setSalt(Character letter, Integer salt) {
		Integer alphabetIndexLetter = alphabet.indexOf(letter);
		
		if(alphabetIndexLetter != -1 && (alphabetIndexLetter - salt) >= 0) {
			return  alphabet.get(alphabetIndexLetter - salt);
		}else if(alphabetIndexLetter == -1){
			return letter;
			
		}else {
			
			int index =  alphabet.size()-1 - (salt-1 - alphabetIndexLetter);
			return  alphabet.get(index);
		}
	}
}
