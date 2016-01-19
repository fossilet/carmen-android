package com.siwei.carmen;

/**
 * Created by tux on 1/19/16.
 */
public class Utils {
    public String getEmijoByUnicode(int unicode){
        return new String(Character.toChars(unicode));
    }
}
