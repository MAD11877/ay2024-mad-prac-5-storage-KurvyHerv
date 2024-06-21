package sg.edu.np.mad.madpractical5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        DatabaseHandler dbHandler = new DatabaseHandler(this, null, null, 1);

        TextView tvName = findViewById(R.id.tvName);
        TextView tvDescription = findViewById(R.id.tvDescription);
        Button btnFollow = findViewById(R.id.btnFollow);

        Intent receivingIntent = getIntent();
        String name = receivingIntent.getStringExtra("name");
        String description = receivingIntent.getStringExtra("description");
        boolean followed = receivingIntent.getBooleanExtra("followed", false);
        int id = receivingIntent.getIntExtra("id", 0);

        User user = new User(name, description, id, followed);
        tvName.setText(user.name);
        tvDescription.setText(user.description);
        btnFollow.setText(user.getFollowed() ? "Unfollow" : "Follow");

        // OnClickListener for Follow Button
        btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.followed) {
                    btnFollow.setText("Unfollow");
                    Toast.makeText(getApplicationContext(), "Followed", Toast.LENGTH_LONG).show();
                    user.followed = false;
                } else {
                    btnFollow.setText("Follow");
                    Toast.makeText(getApplicationContext(), "Unfollow", Toast.LENGTH_LONG).show();
                    user.followed = true;
                }
                dbHandler.updateUser(user.name, user.followed);
            }
        });
    }
}

class User {
    String name;
    String description;
    int id;
    boolean followed;

    public User(String name, String description, int id, boolean followed){
        this.name = name;
        this.description = description;
        this.id = id;
        this.followed = followed;
    }

    public void setName(String username) {this.name = username;}
    public void setDescription(String description) {this.description = description;}
    public void setId(int id) {this.id = id;}
    public void setFollowed(boolean followed) {this.followed = followed;}

    public String getName() {return name;}
    public String getDescription() {return description;}
    public int getId() { return this.id; }
    public boolean getFollowed() { return this.followed; }
}

