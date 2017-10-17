package com.piousbox.training2.dummy;

import java.util.ArrayList;
import java.util.List;

import com.piousbox.training2.rest.Newsitem;

public class NewsitemsContent {
    public static final List<Newsitem> mItems = new ArrayList<Newsitem>();
    private int _count;

    private static Newsitem createOne() {
        Newsitem n = new Newsitem("123", "title", "descr");
        return n;
    }
    private static Newsitem createOne(String random) {
        Newsitem n = new Newsitem("123", "title " + random, "descr " + random);
        return n;
    }

    private static void addItem(Newsitem i) {
      mItems.add(i);
    }

}