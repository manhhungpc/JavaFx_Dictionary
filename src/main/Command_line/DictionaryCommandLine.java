package main.Command_line;

public class DictionaryCommandLine {

    public static void main(String[] args){
        DictionaryManagement test = new DictionaryManagement();
        //tạo các class sau khi hoàn thiện bên DictionaryManagement
        test.insertFromCommandline();
        test.showAllWords();
    }
}
