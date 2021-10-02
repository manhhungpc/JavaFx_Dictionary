package main.Command_line;

public class DictionaryCommandLine {

    public static void showWord(String number, String target, String explain) {
        System.out.printf("%1$-5s| %2$-25s" , number, target);
        System.out.println("| " + explain);
    }

    public static void showAllWords(){
        showWord("No", "English", "Vietnamese");
        for(int i=0; i< Dictionary.getWords().size(); i++){
            showWord(Integer.toString(i+1), Dictionary.getWords().get(i).getWord_target(), Dictionary.getWords().get(i).getWord_explain());
        }
    }

    public static void dictionaryBasic() {
        DictionaryManagement.insertFromCommandline();
        DictionaryCommandLine.showAllWords();
    }


}
