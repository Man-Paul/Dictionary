package com.example.dictionary;

import java.util.HashMap;
import java.util.Map;

public class CreatingDict {
    private HashMap<String,String> map;
    public CreatingDict(){
        map = new HashMap<String,String>();
        ListofWords();
    }

    public void ListofWords(){
        AddWord("abyss","a dark bottomless pit");
        AddWord("abysmal","extremely bad");
        AddWord("abysm","the dark backward");
        AddWord("anguish","extremely distressed about something");
    }

    public void AddWord(String word, String meaning){
        map.put(word,meaning);
    }

    public String[] getSuggestions(String word){
        String[] suggestionList = new String[5];
        int i=0;
        for(Map.Entry<String,String> entry : map.entrySet()){
            int lastindex = Math.min(word.length(), entry.getKey().length());
            if(word.compareTo(entry.getKey().substring(0,lastindex))==0)
                suggestionList[i++] = entry.getKey();
            if(i==4)
                break;
        }
        return suggestionList;
    }

    public String meaning(String word){
        if(!map.containsKey(word))
            return "word not found";
        else
            return map.get(word);
    }

}
