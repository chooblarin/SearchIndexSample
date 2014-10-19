package com.chooblarin.searchindexsample;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chooblarin on 2014/10/19.
 */
public class ItemAdapter extends BindableAdapter<Item> implements SectionIndexer {

    private List<Item> mItemList;

    private List<Item> mFilteredItemList = new ArrayList<Item>();

    private ItemFilter mItemFilter;

    private SectionItem[] mSections;

    private static class SectionItem {
        public String label;
        public int position;

        public SectionItem(String label, int position) {
            this.label = label;
            this.position = position;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    public static class DataItem implements Item {
        public String displayName;
        public int section;
        public boolean isEnabled;
        public String sectionLabel;

        public DataItem(String displayName) {
            this.displayName = displayName;
        }

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

    public ItemAdapter(Context context, List<Item> items) {
        super(context, items);
        mItemList = items;
        setup(items);
    }

    private void setup(List<Item> items) {
        mFilteredItemList.clear();

        ArrayList<SectionItem> sections = new ArrayList<SectionItem>();

        int section = -1;
        String oldLabel = null;

        for (Item item : items) {

            String newLabel = item.getDisplayName().substring(0, 1).toUpperCase();

            if (oldLabel == null || !oldLabel.equals(newLabel)) {
                section = sections.size();
                sections.add(new SectionItem(newLabel, mFilteredItemList.size()));

                // Insert separator
                mFilteredItemList.add(new DataItem(newLabel, section, false));
            }

            mFilteredItemList.add(new DataItem(item.getDisplayName(), section, true));
            oldLabel = newLabel;
        }
        mSections = new SectionItem[sections.size()];
        sections.toArray(mSections);
    }

    @Override
    public int getCount() {
        return mFilteredItemList.size();
    }

    @Override
    public boolean isEnabled(int position) {
        return getItem(position).isEnabled();
    }

    @Override
    public Item getItem(int position) {
        return mFilteredItemList.get(position);
    }

    @Override
    public View newView(LayoutInflater inflater, int position, ViewGroup parent) {
        return inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
    }

    @Override
    public void bindView(Item item, View view) {
        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        textView.setText(item.getDisplayName());

        textView.setBackgroundColor(item.isEnabled() ? Color.TRANSPARENT : Color.LTGRAY);
    }

    @Override
    public Object[] getSections() {
        return mSections;
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        return mSections[sectionIndex].position;
    }

    @Override
    public int getSectionForPosition(int position) {
        return getItem(position).getSectionIndex();
    }

    @Override
    public Filter getFilter() {
        if (mItemFilter == null) {
            mItemFilter = new ItemFilter();
        }

        return mItemFilter;
    }

    private class ItemFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            constraint = constraint.toString().toLowerCase();

            ArrayList<Item> filteredItems = new ArrayList<Item>();
            if (!TextUtils.isEmpty(constraint)) {

                for (Item item : mItemList) {
                    if (item.getDisplayName().toLowerCase().contains(constraint)) {
                        filteredItems.add(item);
                    }
                }
                results.values = filteredItems;
                results.count = filteredItems.size();

            } else {
                synchronized (this) {
                    results.values = mItemList;
                    results.count = mItemList.size();
                }
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            setup((ArrayList<Item>) results.values);
            notifyDataSetChanged();
        }
    }
}
