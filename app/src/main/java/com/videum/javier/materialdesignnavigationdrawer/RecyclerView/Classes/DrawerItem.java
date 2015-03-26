package com.videum.javier.materialdesignnavigationdrawer.RecyclerView.Classes;

import android.graphics.drawable.Drawable;

public class DrawerItem {

    Drawable itemIcon;
    String itemTitle;

    public DrawerItem(Drawable itemIcon, String itemTitle) {

        this.itemIcon = itemIcon;
        this.itemTitle = itemTitle;

    }

    public Drawable getItemIcon() {
        return itemIcon;
    }

    public String getItemTitle() {
        return itemTitle;
    }
}