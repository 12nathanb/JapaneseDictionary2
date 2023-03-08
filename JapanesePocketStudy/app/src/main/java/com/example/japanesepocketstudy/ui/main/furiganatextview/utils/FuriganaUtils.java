package com.example.japanesepocketstudy.ui.main.furiganatextview.utils;


import com.example.japanesepocketstudy.ui.main.furiganatextview.furiganaview.FuriganaTextView;

@Deprecated
public class FuriganaUtils {
    /**
     * The method parseRuby converts kanji enclosed in ruby tags to the
     * format which is supported by the textview {Kanji:furigana}
     *
     * @param textWithRuby
     * @deprecated Use the set{@link FuriganaTextView}
     */
    public static String parseRuby(String textWithRuby) {
        String parsed = textWithRuby.replace("<ruby>", "{");
        parsed = parsed.replace("<rt>", ";");
        parsed = parsed.replace("</rt>", "");

        return parsed.replace("</ruby>", "}");
    }
}
