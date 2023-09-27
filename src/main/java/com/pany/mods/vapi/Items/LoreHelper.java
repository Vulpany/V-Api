package com.pany.mods.vapi.Items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static com.pany.mods.vapi.Text.Splitter.SplitTextByNewlines;

public class LoreHelper {

    /**

     API is used across some of my private servermods
     Uploaded for easy dependency use

     */

    public static void GiveLore(ItemStack stack, MutableText ToInsert) {
        //String JSON = Text.Serializer.toJson(ToInsert);
        NbtCompound NBT = stack.getOrCreateNbt();
        NbtCompound SubNbt;
        if (!NBT.contains("display", NbtElement.COMPOUND_TYPE)) {
            SubNbt = new NbtCompound();

        } else {
            SubNbt = NBT.getCompound("display");
        }
        NbtList LoreList = new NbtList();

        for (MutableText Content : SplitTextByNewlines(ToInsert) ) {
            LoreList.add( NbtString.of(   MutableText.Serializer.toJson(Content)   )  );
        }
        SubNbt.put("Lore",LoreList);
        NBT.put("display",SubNbt);
    }


    private record ItemLoreEntry(Function<ItemStack,MutableText> function, int Weight) {}
    private static List<ItemLoreEntry> ItemLoreFunctions = new ArrayList<>();

    public static void AddItemLoreFunction(int Weight,Function<ItemStack,MutableText> function) {
        ItemLoreFunctions.add(new ItemLoreEntry(function,Weight));
        ItemLoreFunctions.sort((a,b) -> {
            if (a.Weight > b.Weight) {
                return 1;
            } else {
                return -1;
            }
        });
    }

    public static <WorldType> void SpecialItemLore(ItemStack stack,WorldType world) {
        boolean IsClient = false;
        Item item = stack.getItem();
        List<MutableText> AdditionLore = new ArrayList<>();
        for (ItemLoreEntry Entry : ItemLoreFunctions) {
            MutableText Add = Entry.function.apply(stack);
            if (Add != null) {
                AdditionLore.add(Add);
            }
        }
        if (AdditionLore.size() > 0) {
            MutableText ToAdd = null;
            boolean First = true;
            for (MutableText TextContent : AdditionLore) {
                if (First) {
                    ToAdd = TextContent;
                } else {
                    ToAdd.append( Text.literal("\n").append(TextContent) );
                }
                First = false;
            }
            GiveLore(stack,ToAdd);
        }
    }

}
