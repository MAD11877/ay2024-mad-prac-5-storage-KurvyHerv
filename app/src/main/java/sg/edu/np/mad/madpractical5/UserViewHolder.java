package sg.edu.np.mad.madpractical5;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import sg.edu.np.mad.madpractical5.R;

public class UserViewHolder extends RecyclerView.ViewHolder {
    ImageView smallImage;
    ImageView bigImage;
    TextView name;
    TextView description;

    public UserViewHolder(View itemView) {
        super(itemView);

        smallImage = itemView.findViewById(R.id.smallimage);
        bigImage = itemView.findViewById(R.id.bigimage);
        name = itemView.findViewById(R.id.nameTxt);
        description = itemView.findViewById(R.id.descriptionTxt);
    }
}