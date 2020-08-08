package lockdown.org.videocapturedemoapp;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity
{
    VideoView vv;
    Button btnShoot;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 23)
        {
            if (ContextCompat.checkSelfPermission(MainActivity.this, "android.permission.CAMERA") != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{"android.permission.CAMERA"},2);
            }
        }
        setContentView(R.layout.activity_main);
        vv = findViewById(R.id.vv);
        btnShoot = findViewById(R.id.btnShoot);
        btnShoot.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent i = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                startActivityForResult(i,1);
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK)
        {
            Uri ob = data.getData();
            vv.setVideoURI(ob);
            vv.start();
        }
    }
}