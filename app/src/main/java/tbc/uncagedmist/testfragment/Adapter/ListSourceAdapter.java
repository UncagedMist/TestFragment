package tbc.uncagedmist.testfragment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tbc.uncagedmist.testfragment.Common.Common;
import tbc.uncagedmist.testfragment.Fragments.SecondFragment;
import tbc.uncagedmist.testfragment.Interface.IconBetterIdeaService;
import tbc.uncagedmist.testfragment.Interface.ItemClickListener;
import tbc.uncagedmist.testfragment.Model.IconBetterIdea;
import tbc.uncagedmist.testfragment.Model.WebSite;
import tbc.uncagedmist.testfragment.R;

class ListSourceViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {
    ItemClickListener itemClickListener;

    TextView source_title;
    CircleImageView source_image;

    public ListSourceViewHolder(View itemView) {
        super(itemView);

        source_image =  itemView.findViewById(R.id.source_image);
        source_title = (TextView)itemView.findViewById(R.id.source_name);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}

public class ListSourceAdapter extends RecyclerView.Adapter<ListSourceViewHolder>{
    private Context context;
    private WebSite webSite;

    private IconBetterIdeaService mService;

    public ListSourceAdapter(Context context, WebSite webSite) {
        this.context = context;
        this.webSite = webSite;

        mService = Common.getIconService();
    }

    @Override
    public ListSourceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.source_layout,parent,false);
        return new ListSourceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ListSourceViewHolder holder, int position) {



        holder.source_title.setText(webSite.getSources().get(position).getName());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                SecondFragment secondFragment = new SecondFragment();

                FragmentTransaction transaction = ((AppCompatActivity)context)
                        .getSupportFragmentManager().beginTransaction();

                Common.CURRENT_SOURCE_ID = webSite.getSources().get(position).getId();

                transaction.replace(R.id.mainLayout,secondFragment).commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return webSite.getSources().size();
    }
}