import java.util.*;
public class Chapter1 {
	public static void main(String args[]) {
		testIsUnique("yyy");
		testIsUnique2("dghk,vcx");
		testCheckPermutation("yeH", "hey");
		testURLify("bu  y");
		testIsPalindromePermutation("heyheyy");
		testIsOneEditAway("HeYjjjj", "heyjjjj");
		testCompressString("as");
		int[][] NxN = {{1,1,1},{2,2,2},{3,3,3}};
		int[][] MxN = {{1,1,1},{2,0,2},{3,3,3},{4,4,4}};
		testRotateMatrix(NxN);
		testZeroMatrix(MxN);
		testStringRotation("sdf","dfs");
	}

	//##### My solution methods
	public static boolean isUnique(String s) {
		HashSet<Character> chars = new HashSet<>(); 
		for(int a=0; a<s.length(); a++) {
			if(chars.contains(s.charAt(a))) {
				return false;
			}else {
				chars.add(new Character(s.charAt(a)));
			}
		}
		return true;
	}
	public static boolean isUnique2(String s) {
		for(int a=0; a<s.length()-1; a++) {
			char c = s.charAt(a);
			for(int b=a+1; b<s.length(); b++) {
				if(c==s.charAt(b))return false;
			}
		}
		return true;
	}	
	public static boolean checkPermutation(String s1, String s2) {
		if(s1.length()!=s2.length()) {
			return false;
		}else {
			HashSet<Character> chars1 = new HashSet<>();
			HashSet<Character> chars2 = new HashSet<>();
			for(int a=0; a<s1.length(); a++) {
				chars1.add(new Character(s1.charAt(a)));
			}
			for(int a=0; a<s2.length(); a++) {
				chars2.add(new Character(s2.charAt(a)));
			}
			if(chars1.equals(chars2))return true;
			else return false;
		}
	}
	public static char[] URLify(char[] charString) {
		int spaceCount = 0;
		for(int a=0; a<charString.length; a++) {
			if(charString[a]==' ')spaceCount++;
		}
		if(spaceCount==0)return charString;
		else {
			char[] newArray = new char[(2*spaceCount)+charString.length];
			int spacesAdded = 0;
			for(int a=0; a<charString.length; a++) {
				if(charString[a]==' ') {
					newArray[a+(spacesAdded*2)] = '%';
					newArray[a+(spacesAdded*2)+1] = '2';
					newArray[a+(spacesAdded*2)+2] = '0';
					spacesAdded++;
				}else {
					newArray[a+(spacesAdded*2)] = charString[a];
				}
			}
			return newArray;
		}
	}	
	public static boolean isPalindromePermutation(String s) {
		HashMap<Character, Integer> chars = new HashMap<>();
		for(int a=0; a<s.length(); a++) {
			if(chars.containsKey(s.charAt(a))) {
				chars.put(s.charAt(a), chars.get(s.charAt(a))+1);
			}else {
				chars.put(s.charAt(a), 1);
			}
		}
		int oddChars = 0;
		for(int charCount:chars.values()) {
			if(charCount%2!=0) {
				oddChars++;
				if(oddChars>1)return false;
			}
		}
		return true;
	}
	public static boolean isOneEditAway(String s1, String s2) {
		if(Math.abs(s1.length()-s2.length())>1)return false;
		else {
			String longerString,shorterString;
			if(s1.length()>s2.length()) {
				longerString = s1;
				shorterString = s2;
			}else {
				longerString = s2;
				shorterString = s1;
			}
			int outOfPlaceCharCountReplace = 0,outOfPlaceCharCountInsertDelete = 0;
			int b=0;
			for(int a=0; a<shorterString.length(); a++,b++) {
				if (shorterString.charAt(a)!=longerString.charAt(b)) {
					outOfPlaceCharCountReplace++;
					try {
						if(shorterString.charAt(a)!=longerString.charAt(b+1)) {
							outOfPlaceCharCountInsertDelete++;
						}
					}catch(StringIndexOutOfBoundsException e) {
						outOfPlaceCharCountInsertDelete++;
					}
					
				}
				if(outOfPlaceCharCountReplace>1 && outOfPlaceCharCountInsertDelete>1)return false;
			}
			return true;
		}
	}
	public static String compressString(String s) {
		StringBuilder sb = new StringBuilder();
		int charCount = 0;
		char currentChar = s.charAt(0);
		if(s.length()<2)return s;
		for(int a=0; a<s.length(); a++) {
			if(currentChar==s.charAt(a)) {
				charCount++;
				continue;
			}else {
				sb.append(currentChar);
				sb.append(charCount);
				currentChar = s.charAt(a);
				charCount = 1;
			}
		}
		if(sb.length()>=s.length())return s;
		else return sb.toString();
	}
	public static int[][] rotateMatrix(int[][] matrix){
		int[][] newMatrix = new int[matrix.length][matrix.length];
		for(int a=0; a<matrix.length; a++) {
			for(int b=0; b<matrix.length; b++) {
				newMatrix[b][matrix.length-a-1] = matrix[a][b];
			}
		}
		return newMatrix;
	}
	public static int[][] zeroMatrix(int[][] MxN){
		HashSet<Integer> zeroColumns = new HashSet<>();
		HashSet<Integer> zeroRows = new HashSet<>();
		for(int col=0; col<MxN[0].length; col++) {
			for(int row=0; row<MxN.length; row++) {
				if(MxN[row][col]==0) {
					zeroColumns.add(col);
					zeroRows.add(row);
				}
			}
		}
		for(int col=0; col<MxN[0].length; col++) {
			for(int row=0; row<MxN.length; row++) {
				if(zeroColumns.contains(col)||zeroRows.contains(row)) {
					MxN[row][col]=0;
				}
			}
		}
		return MxN;
	}
	public static boolean isStringRotation(String s1, String s2) {
		return isSubString(s1.concat(s1), s2);
	}
	
	////###### Testing Helper Methods
	private static void testIsUnique(String s) {
		if(isUnique(s)) {
			System.out.println("String \""+s+"\" contains all unique characters");
		}else {
			System.out.println("String \""+s+"\" does not contain all unique characters");
		}
	}
	public static void testIsUnique2(String s) {
		if(isUnique2(s)) {
			System.out.println("String \""+s+"\" contains all unique characters");
		}else {
			System.out.println("String \""+s+"\" does not contain all unique characters");
		}
	}
	public static void testCheckPermutation(String s1, String s2) {
		if(checkPermutation(s1,s2)) {
			System.out.println("\""+s1+"\" is a permutation of \""+s2+"\"");
		}else {
			System.out.println("\""+s1+"\" is not a permutation of \""+s2+"\"");
		}
	}
	public static void testURLify(String s) {
		System.out.println("String \""+s+"\" becomes \""+(new String(URLify(s.toCharArray())))+ "\" when URLified.");
	}
	public static void testIsPalindromePermutation(String s) {
		if(isPalindromePermutation(s)) {
			System.out.println("String \""+s+"\" is a permutation of its palindrome");
		}else {
			System.out.println("String \""+s+"\" is not a permutation of its palindrome");
		}
	}
	public static void testIsOneEditAway(String s1, String s2) {
		if(isOneEditAway(s1,s2)) {
			System.out.println("\""+s1+"\" is one edit away from \""+s2+"\"");
		}else {
			System.out.println("\""+s1+"\" is not one edit away from \""+s2+"\"");
		}
	}
	public static void testCompressString(String s) {
		System.out.println("\""+s+"\" is compressed to \""+compressString(s)+"\"");
	}
	public static void testRotateMatrix(int[][] a) {
		System.out.println("OG Matrix = "+Arrays.deepToString(a)+". Rotated = "+Arrays.deepToString(rotateMatrix(a)));
	}
	public static void testZeroMatrix(int[][] a) {
		System.out.println("OG Matrix = "+Arrays.deepToString(a)+" Zeroed Matrix = "+Arrays.deepToString(zeroMatrix(a)));
	}
	public static boolean isSubString(String s1, String s2) {
		if(s1.contains(s2))return true;
		else return false;
	}
	public static void testStringRotation(String s1, String s2) {
		if(isStringRotation(s1,s2))System.out.println("\""+s1+"\" is a rotation of \""+s2+"\"");
		else System.out.println("\""+s1+"\" is not a rotation of \""+s2+"\"");
	}
}
