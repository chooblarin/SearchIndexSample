package com.chooblarin.searchindexsample;

/**
 * Created by chooblarin on 2014/10/20.
 */
public class DataItem implements Item {
    public String displayName;
    public int section;
    public boolean isEnabled;
    public String sectionLabel;

    public DataItem(String displayName, int section, boolean isEnabled) {
        this.displayName = displayName;
        this.section = section;
        this.isEnabled = isEnabled;
        this.sectionLabel = displayName.substring(0, 1).toUpperCase();
    }

    @Override
    public String toString() {
        return displayName;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public int getSectionIndex() {
        return section;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
