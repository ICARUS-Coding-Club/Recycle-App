import android.content.Context
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.icarus.recycle_app.R;

public class NumberAdaptar extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    String[] arrNumberword;
    int[] arrNumberImage;

    public NumberAdaptar(Context context, String[] arrNumberword, int[] arrNumberImage) {
        this.context = context;
        this.arrNumberword = arrNumberword;
        this.arrNumberImage = arrNumberImage;
    }

    @Override
    public int getCount() {
        return arrNumberword.length;
    }

    @Override
    public Object getItem(int position) {
        return arrNumberword[position];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if(inflater==null){
            inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)
        }
        if(view==null){

            view = inflater.inflate(R.layout.test,null);
        }
        ImageView numberImage = view.findViewById(R.id.number);
        TextView numberword = view.findViewById(R.id.numberText);

        numberImage.setImageResource(arrNumberImage[position]);
        numberword.setText(arrNumberword[position]);

        return view;
    }
}
