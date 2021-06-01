package com.example.deberretrofit.models;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.deberretrofit.R;
import com.example.deberretrofit.interfaces.PostService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    EditText edtID;
    Button btn;
    TextView txtResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtID = findViewById(R.id.txtID);
        txtResult = findViewById(R.id.lblText);

        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                txtResult.setText("");
                resultado(edtID.getText().toString());
            }
        });
    }

    public void resultado(String q){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://revistas.uteq.edu.ec/ws/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PostService postService = retrofit.create(PostService.class);
        Call<List<Post>> call = postService.find(q);
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                List<Post> posts = response.body();
                for (Post p: posts){
                    String resultado = "";
                    resultado+= "Titulo: "+p.getTitle()+"\n";
                    resultado += "ID: "+p.getIssue_id()+"\n";
                    resultado += "Volumen: "+p.getVolume()+"\n";
                    resultado += "Año: "+p.getYear()+"\n";
                    resultado += "Número: "+p.getNumber()+"\n";
                    resultado += "Doi: "+p.getDoi()+"\n\n";
                    txtResult.append(resultado);

                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }
}