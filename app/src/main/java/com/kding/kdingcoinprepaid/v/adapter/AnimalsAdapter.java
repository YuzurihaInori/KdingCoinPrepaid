package com.kding.kdingcoinprepaid.v.adapter;

import android.support.v7.widget.RecyclerView;

import com.kding.kdingcoinprepaid.bean.SelectBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;


/**
 * Adapter holding a list of animal names of type String. Note that each item must be unique.
 */
public abstract class AnimalsAdapter<VH extends RecyclerView.ViewHolder>
    extends RecyclerView.Adapter<VH> {
  private ArrayList<SelectBean> items = new ArrayList<SelectBean>();

  public AnimalsAdapter() {
    setHasStableIds(true);
  }

  public void add(SelectBean object) {
    items.add(object);
    notifyDataSetChanged();
  }

  public void add(int index, SelectBean object) {
    items.add(index, object);
    notifyDataSetChanged();
  }

  public void addAll(Collection<? extends SelectBean> collection) {
    if (collection != null) {
      items.addAll(collection);
      notifyDataSetChanged();
    }
  }

  public void addAll(SelectBean... items) {
    addAll(Arrays.asList(items));
  }

  public void clear() {
    items.clear();
    notifyDataSetChanged();
  }

  public void remove(String object) {
    items.remove(object);
    notifyDataSetChanged();
  }

  public SelectBean getItem(int position) {
    return items.get(position);
  }

  @Override
  public long getItemId(int position) {
    return getItem(position).hashCode();
  }

  @Override
  public int getItemCount() {
    return items.size();
  }
}
