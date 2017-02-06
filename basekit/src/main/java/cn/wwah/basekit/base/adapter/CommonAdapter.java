/**
* <p>Title: </p>
* <p>Description: </p>
* <p>Company: </p> 
* @author Jixiang_Li
* @date 2015年12月14日 下午4:46:07
*/
package cn.wwah.basekit.base.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jixiang_Li
 *
 */
public abstract class CommonAdapter<T> extends BaseAdapter {

	public List<T> data;
	public Context activity;

	/**
	 * 记得注销
	 */
	private int count = 0;
	public void setCount(int count) {
		this.count = count;
	}

	public CommonAdapter(Activity activity) {
		this.activity = activity;
    }
	
	public CommonAdapter(Activity activity, ArrayList<T> data) {
	    this.activity = activity;
	    this.data = data;
    }

	public void setData(List<T> data){
		this.data = data;
	}

	public List<T> getData() {
		return data;
	}

	@Override
	public int getCount() {
		return null!=data?data.size():0;
	}

	public T getItem(int position) {
		return data.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return view(position, convertView, parent);
	}

	public abstract View view(int position, View convertView, ViewGroup parent);
}
