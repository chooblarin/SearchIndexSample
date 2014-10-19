package com.chooblarin.searchindexsample;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chooblarin on 2014/10/19.
 */
public class SearchIndexFragment extends Fragment {

    private List<Item> mItemList;

    private ItemAdapter mItemAdapter;

    private SearchView mSearchView;

    private ListView mListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mItemList = new ArrayList<Item>();
        for (String data : sampleData) {
            mItemList.add(new ItemAdapter.DataItem(data));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search_index, container, false);
        mSearchView = (SearchView) rootView.findViewById(R.id.search_view_search_index);
        mListView = (ListView) rootView.findViewById(R.id.list_view_search_index);
        TextView emptyView = (TextView) rootView.findViewById(R.id.empty_list_view);
        mListView.setEmptyView(emptyView);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mItemAdapter = new ItemAdapter(getActivity(), mItemList);
        mListView.setAdapter(mItemAdapter);

        setupSearchView();
    }

    private void setupSearchView() {
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setSubmitButtonEnabled(false);
        mSearchView.setQueryHint("検索文字列を入力");
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String queryText) {
                Log.d("mimic", "onQueryTextChange -> " + queryText);
                mItemAdapter.getFilter().filter(queryText);
                return true;
            }
        });
    }

    private String[] sampleData = {
            "ACO",
            "advantage Lucy",
            "AJICO",
            "androp",
            "andymori",
            "APOGEE",
            "ASA-CHANGE & 巡礼",
            "ASPARAGUS",
            "Atoms For Peace",
            "Audio Safari",
            "Bank Band",
            "Beatles",
            "bird",
            "Bjork",
            "Black Eyed Peas",
            "Bon Jovi",
            "bonobos",
            "Britney Spears",
            "Budda Brand",
            "Caravan",
            "Cardigans",
            "cero",
            "Chara",
            "Chemical Brothers",
            "COLTEMONICA",
            "Cornelius",
            "CRAZY KEN BAND",
            "Curly Giraffe",
            "Cymbals",
            "d.v.d",
            "Daft Punk",
            "DE DE MOUSE",
            "Death Cab for Cutie",
            "Deerhoof",
            "DJ MOTIVE",
            "DOOPEES",
            "Dorian",
            "Dragon Ash",
            "ECCY",
            "EGO-WRAPPIN",
            "Eminem",
            "EVISBEATS",
            "Fantastic Plastic Machine",
            "FISHMANS",
            "Freetempo",
            "GOING UNDER GROUND",
            "GREAT3",
            "group_inou",
            "Gym Class Heros",
            "Hi-STANDARD",
            "HIATUS",
            "Jack Johnson",
            "Judy And Mary",
            "KAKATO",
            "Ken Yokoyama",
            "Kj",
            "KOCHITORA HAGURETIC EMCEE'S",
            "KREVA",
            "L.E.D",
            "Lamp",
            "LCD Soundsystem",
            "Liky Allen",
            "Little Creatures",
            "London Elektricity",
            "m-flo",
            "mabanua",
            "Massive Attack",
            "Meg",
            "Micro of Def Tech",
            "MISS MONDAY",
            "MITO",
            "miyauchi yuri",
            "Mondo Grosso",
            "MONOBRIGHT",
            "Mr.Children",
            "Nabowa",
            "neco眠る",
            "NiNA",
            "Number Girl",
            "ohana",
            "Oorutaichi",
            "PE'Z",
            "Pizzicato Five",
            "Polaris",
            "POLYSICS",
            "pupa",
            "Q;indivi",
            "Radiohead",
            "rega",
            "Regina Spektor",
            "Rei Harakami",
            "Rythmatrix",
            "RIP SLYME",
            "ROCKETMAN",
            "ryuichi sakamoto",
            "S.L.A.C.K",
            "SAKEROCK",
            "Salyu",
            "Seal",
            "Shingo2",
            "Shinichi Osawa",
            "Shugo Tokumaru",
            "Shuta Hasunuma",
            "SINGER SONGER",
            "Smiths",
            "So many tears",
            "SOUR",
            "SPECIAL OTHERS",
            "Spitz",
            "Steady & Co.",
            "SUPER BUTTER DOG",
            "sweet robots against the machine",
            "syrup 16g",
            "Tahiti 80",
            "TIN PAN ALLEY",
            "toe",
            "tofubeats",
            "TOWA TEI",
            "UA",
            "UNCHAIN",
            "Underworld",
            "UNICORN",
            "UQIYO",
            "Usher",
            "Utada",
            "VERBAL",
            "Weezer",
            "Yakenohara",
            "yanokami",
            "Yellow Magic Orchestra",
            "Yo La Tengo",
            "Yoshinori Sunahara",
            "YOUR SONG IS GOOD",
            "YUKI",
            "ZAZEN BOYS"
    };
}
