package com.pany.mods.vapi.Text;

import net.minecraft.text.LiteralTextContent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Splitter {
    public static List<Text> BreakUpText(Text TextToBreak) {
        List<Text> BrokenUpList = new ArrayList<>();
        BrokenUpList.add( Text.literal( ((LiteralTextContent)TextToBreak.getContent()).string() ).setStyle(TextToBreak.getStyle()) );
        for (Text SubText : TextToBreak.getSiblings()) {
            BrokenUpList.addAll(BreakUpText(SubText));
        }
        return BrokenUpList;
    }

    public static List<String> SplitString(String Input,String Splitter) {
        List<String> ToReturn = new ArrayList<>();
        /*
        if (Input.startsWith(Splitter)) {
            ToReturn.add("");
        }

         */
        ToReturn.addAll( Arrays.stream(Input.split(Splitter)).toList() );
        if (Input.equals(Splitter)) {
            ToReturn.add("");
            ToReturn.add("");
        } else if (Input.endsWith(Splitter)) {
            ToReturn.add("");
        }
        return ToReturn;
    }

    public static List<MutableText> SplitTextByNewlines(Text text) {
        List<MutableText> ToAdd = new ArrayList<>();
        MutableText WriteHere = Text.literal("");
        for (Text Txt : BreakUpText(text)) {
            // Breaking new line operators up
            List<String> Lines = SplitString( Txt.getString().replaceAll(Character.toString(10),"\n") ,"\n" );
            if (Lines.size() > 1) {
                boolean First = true;
                for (String CutLine : Lines ) {
                    if (!First) {
                        ToAdd.add(WriteHere);
                        WriteHere = Text.literal("");
                    }
                    WriteHere.append( Text.literal(CutLine).setStyle(Txt.getStyle()) );

                    First = false;
                }
            } else {
                WriteHere.append( Txt );
            }
        }
        ToAdd.add(WriteHere);
        return ToAdd;
    }
}
