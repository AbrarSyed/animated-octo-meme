package me.abrarsyed.animatedoctomeme.util;

public class StringCursor
{
    public final String str;
    private int index;

    public StringCursor(String str)
    {
        this.str = str;
        this.index = 0;
    }
    
    public StringCursor(String str, int startIndex)
    {
        this.str = str;
        this.index = startIndex;
    }
    
    public int getIndex()
    {
        return index;
    }
    
    public boolean isStart()
    {
        return index == 0;
    }
    
    public boolean isEnd()
    {
        return index == str.length()-1;
    }
    
    public StringCursor move(int ammount)
    {
        index += index;
        clamp();
        
        return this;
    }
    
    public StringCursor increment()
    {
        index++;
        clamp();
        
        return this;
    }
    
    public StringCursor decrement()
    {
        index--;
        clamp();
        
        return this;
    }
    
    private void clamp()
    {
        if (index < 0)
            index = 0;
        else if (index > str.length() - 1)
            index = str.length() - 1;
    }
    
    public char charAt()
    {
        return str.charAt(index);
    }
    
    public StringCursor skipWhitespace()
    {
        for (; index < str.length(); index++)
        {
            if (!Character.isWhitespace(charAt()))
                break;
        }
        
        return this;
    }
    
    /**
     * Moves if possible, otherwise doesnt move.
     * @param c char to find and move to
     */
    public StringCursor skipToChar(char c)
    {
        index = str.indexOf(c, index) +1;
        return this;
    }
    
    /**
     * Moves if possible, otherwise doesnt move.
     * @param c char to find and move to
     */
    public String substringTill(char c)
    {
        int oldIndex = index;
        skipToChar(c);
        return str.substring(oldIndex, index-1);
    }
    
    /**
     * Moves if possible, otherwise doesnt move.
     * @param c char to find and move to
     */
    public String substringTill(char... chars)
    {
        int oldIndex = index;
        for (; index < str.length(); index++)
        {
           boolean found = false;
           for (char c : chars)
           {
               if (charAt() == c)
               {
                   found = true;
                   break;
               }
           }
           
           if (found)
               break;
        }
        return str.substring(oldIndex, index-1);
    }
}
