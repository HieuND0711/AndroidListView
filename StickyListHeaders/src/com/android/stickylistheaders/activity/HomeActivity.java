package com.android.stickylistheaders.activity;

import static android.widget.Toast.LENGTH_SHORT;

import com.android.stickylistheaders.library.StickyListHeadersListView;
import com.android.stickylistheaders.library.StickyListHeadersListView.OnHeaderClickListener;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AbsListView.OnScrollListener;

public class HomeActivity extends Activity implements OnScrollListener,
		AdapterView.OnItemClickListener, OnHeaderClickListener {

	private static final String KEY_LIST_POSITION = "KEY_LIST_POSITION";
	private int firstVisible;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_home);

		StickyListHeadersListView stickyList = (StickyListHeadersListView) findViewById(R.id.list);
		stickyList.setDivider(new ColorDrawable(0xffffffff));
		stickyList.setDividerHeight(1);
		stickyList.setOnScrollListener(this);
		stickyList.setOnItemClickListener(this);
		stickyList.setOnHeaderClickListener(this);

		if (savedInstanceState != null) {
			firstVisible = savedInstanceState.getInt(KEY_LIST_POSITION);
		}

		stickyList.setAdapter(new TestBaseAdapter(this));
		stickyList.setSelection(firstVisible);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(KEY_LIST_POSITION, firstVisible);
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
			int totalItemCount) {
		this.firstVisible = firstVisibleItem;
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Toast.makeText(this, "Item " + position + " clicked!", LENGTH_SHORT).show();
	}

	@Override
	public void onHeaderClick(StickyListHeadersListView l, View header, int itemPosition,
			long headerId, boolean currentlySticky) {
		Toast.makeText(this, "header " + headerId, Toast.LENGTH_SHORT).show();
	}

}
