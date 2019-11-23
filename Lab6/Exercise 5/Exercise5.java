public class Exercise5 {

    public static int WordCount(String str){
        return str.split(" ").length;
    }

    public static String arrayToString(char[] arr){
        String str = "";
        for (int i = 0; i < arr.length; i++) {
            str+=arr[i];
        }
        return str;
    }

    public static String mostFrequent(String str){
        int[] count = new int[26];
        int max = 0;
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i)!=' '){
                count[str.charAt(i)-97]++;
            }
        }
        for (int i = 0; i < 26; i++) {
            if(count[i] > count[max]){
                max = i;
            }
        }
        return String.format("%c",max+97);
    }

    public static String replaceSubstring(String s1, String s2, String s3){
        return s1.replace(s2,s3);
    }
    public static void main(String[] args) {
        String str = "the dog jumped over the fence";
        System.out.println("Number of words in \"the dog jumped over the fence\" is "+WordCount(str));
        System.out.println("Most frequently occuring character: "+mostFrequent(str));
        System.out.println(replaceSubstring(str,"the","that"));

    }
}
