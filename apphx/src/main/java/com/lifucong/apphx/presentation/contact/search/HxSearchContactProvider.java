package com.lifucong.apphx.presentation.contact.search;

import android.content.SearchRecentSuggestionsProvider;

/**
 *根据最近查询/浏览，提供简单的搜索建议
 *
 * Created by Administrator on 2016/10/18.
 */

public class HxSearchContactProvider extends SearchRecentSuggestionsProvider {

    public static final int MODE = DATABASE_MODE_QUERIES;

    public static final String AUTHORITY = "com.lifucong.apphx.HxSearchContactProvider";

    public HxSearchContactProvider(){
        setupSuggestions(AUTHORITY, MODE);
    }
}
