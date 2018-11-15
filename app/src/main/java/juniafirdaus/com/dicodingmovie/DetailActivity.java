package juniafirdaus.com.dicodingmovie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class DetailActivity extends AppCompatActivity {

    TextView mTxtTitle, mTxtGenre, mTxtOverview;
    ImageView mImgBackdrop, mImgPoster;
    android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initView();
        loadDetail();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void initView() {
        mTxtTitle = findViewById(R.id.txtTitle);
        mTxtGenre = findViewById(R.id.txtGenre);
        mTxtOverview = findViewById(R.id.txtOverview);
        mImgBackdrop = findViewById(R.id.imgBackdrop);
        mImgPoster = findViewById(R.id.fab);
        toolbar = findViewById(R.id.toolbar);
    }

    private void loadDetail() {
        mTxtTitle.setText(getIntent().getStringExtra("title"));
        mTxtGenre.setText(getIntent().getStringExtra("date"));
        mTxtOverview.setText(getIntent().getStringExtra("overview"));
        toolbar.setTitle(getIntent().getStringExtra("title"));
        Glide.with(DetailActivity.this)
                .load(BuildConfig.IMAGE + getIntent().getStringExtra("banner"))
                .into(mImgBackdrop);

        Glide.with(DetailActivity.this)
                .load(BuildConfig.IMAGE + getIntent().getStringExtra("poster"))
                .into(mImgPoster);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();

        return super.onOptionsItemSelected(menuItem);
    }
}
