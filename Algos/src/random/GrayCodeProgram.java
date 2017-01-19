package random;

public class GrayCodeProgram {
//This is for Amazon test
	public boolean isGray(int n, int m) {
		int xor = (n ^ m);

		return (xor & (xor - 1)) == 0 ? true : false;

	}
	
	String removeVowels(String s){
		int vowel_count = 0;
		int vowel_start = -1;
		char[] str = s.toCharArray();
		for(int i = 0; i < s.length(); i++){
			char ch = str[i];
			
			if(isVowel(ch)){
				
				if(vowel_start == -1){
					vowel_start = i;
					vowel_count += 1;
				}
				else
					vowel_count += 1;
			}
			
			else if(vowel_start != -1 && !isVowel(ch)){
				char temp = str[vowel_start];
				str[vowel_start] = str[i];
				str[i] = temp;
				vowel_start += 1;
			}
		}
		String sss = new String(str);
		sss = sss.substring(0, sss.length() - vowel_count);
		
		System.out.println(sss);
		return sss;
	}
	
	private boolean isVowel(char ch) {
		// TODO Auto-generated method stub
		char[] vowels = {'a','e','i','o','u'};
		for(int i = 0; i < vowels.length; i++){
			if(ch == vowels[i])
				return true;
		}
		return false;
	}
/*
	String replaceSpace(String s){
		int spaceCount = 0;
		for(int i = 0; i < s.length(); i++){
			char ch = s.charAt(i);
			if(ch == ' ') spaceCount++;
		}
		
		int newLength = s.length() + (spaceCount * 2);
		char[] newString = s.toCharArray();
		newString[newLength] = '0';
		for(int j = s.length() - 1; j > 0; j++){
			if(newString[j] == ' '){
				newString[newLength - 1] = '0';
				newString[newLength - 2] = '2';
				newString[newLength - 3] = '%';
			}
			else
				newString[newLength - 1] = newString[j];
				newLength = newLength - 1;
		}
		String ss = new String(newString);
		return ss;
	}
*/
	public boolean isUnique(String s) {
		boolean[] numbers = new boolean[256];
		char[] c = s.toCharArray();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (numbers[ch] == true) {
				return false;
			} else {
				numbers[ch] = true;
			}
		}
		return true;
	}

	/*
	 * public String reverse(String s){ if(s == null) return null; if(s.length()
	 * == 1) return s; int i = 0; while(i < s.length()){ int j =0; char ch =
	 * s.charAt(j); while(ch != ' '){ j++; } int j_next = 0; while(i < j){
	 * j_next = j+1; char start = s.charAt(i); char end = s.charAt(j-1); char
	 * temp = start; start = end; end = temp; i++; j--; } j = j_next; i =
	 * j_next; }
	 * 
	 * }
	 */

	public String removeDuplicates(String s) {
		if (s == null)
			return null;
		if (s.length() < 2)
			return null;
		char[] ch = s.toCharArray();
		boolean[] hit = new boolean[256];
		hit[s.charAt(0)] = true;
		int tail = 1;
		int i;
		for (i = 1; i < s.length(); i++) {
			if (!hit[s.charAt(i)]) {
				ch[tail] = ch[i];
				tail++;
				hit[s.charAt(i)] = true;

			}
		}
		// this is to nullify the other remaining characters in the string. bcoz
		// as we remove char length of the string will be reduced
		while (tail < i) {
			ch[tail] = 0;
			tail++;
		}
		String newStr = new String(ch);
		return newStr;
	}
	
	boolean anagram(String s,String t){
		
		char[] source = s.toCharArray();
		int unique_count = 0;
		int completed_count = 0;
		int[] numbers = new int[256];
		for(int i = 0; i < source.length; i++){
			int ch = (int)s.charAt(i);
			if(numbers[ch] == 0) unique_count++;
			numbers[ch]++;
		}
		
		for(int i = 0; i < t.length(); i++){
			int ch = (int)t.charAt(i);
			if(numbers[ch] == 0) return false;
			else{
				numbers[ch]--;
				if(numbers[ch] == 0) completed_count++;
			}
		}
		if(unique_count == completed_count) return true;
		else return false;
	}
	
	boolean isRotate(String s,String t){
		if(s.length()!= t.length()) return false;
		
		String s3 = s+s;
		if(isSubstring(s3,t)) return true;
		else return false;
	}
	
	boolean isSubstring(String s1,String s2){
		boolean fullContainsSub = s1.toUpperCase().indexOf(s2.toUpperCase()) != -1;
		return fullContainsSub;
	}
	
	

	public static void main(String[] args) {
		GrayCodeProgram gp = new GrayCodeProgram();
		System.out.println(gp.isGray(-125, 126));
		System.out.println(gp.isUnique("welcome"));
		System.out.println(gp.removeDuplicates("aabbcdeff"));
		System.out.println(gp.anagram("hello", "olleh"));
		
		
		System.out.println(gp.isRotate("water", "erwat"));
		//System.out.println(gp.replaceSpace("Hi this is anup"));
		System.out.println(gp.removeVowels("peeper"));
	}
}
