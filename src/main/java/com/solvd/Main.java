package com.solvd;

import com.solvd.textops.TextManager;

public class Main {
    public static void main(String[] args) {
        TextManager textManager = new TextManager("I, love Asuka...!");
        textManager.outputAndCountLetters();
    }
}
