package com.aryanadewi_tugaspraktikum.responsi_17030033_17030035;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class music_activity11 extends AppCompatActivity {
    //Deklarasi Variable
    private Button Play, Pause, Stop;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inisialisasi Button
        Play = findViewById(R.id.play);
        Pause = findViewById(R.id.pause);
        Stop = findViewById(R.id.stop);

        //Menambahkan Listener pada Button
        Play.setOnClickListener((View.OnClickListener) this);
        Pause.setOnClickListener((View.OnClickListener) this);
        Stop.setOnClickListener((View.OnClickListener) this);

        stateAwal();
    }

    //Untuk menentukan kondisi saat aplikasi pertama kali berjalan
    private void stateAwal(){
            Play.setEnabled(true);
            Pause.setEnabled(false);
            Stop.setEnabled(false);
    }

    //Method untuk memainkan musik
    private void playAudio(){
            //Menentukan resource audio yang akan dijalankan
            mediaPlayer = MediaPlayer.create(this, R.raw.beatles_obladioblada);

            //Kondisi Button setelah tombol play di klik
            Play.setEnabled(false);
            Pause.setEnabled(true);
            Stop.setEnabled(true);

            //Menjalankan Audio / Musik
            try{
                mediaPlayer.prepare();
            }catch (IllegalStateException ex){
                ex.printStackTrace();
            }catch (IOException ex1){
                ex1.printStackTrace();
            }
            mediaPlayer.start();

            //Setelah audio selesai dimainkan maka kondisi Button akan kembali seperti awal
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stateAwal();
                }
            });
        }


    //Method untuk mengentikan musik
    @SuppressLint("SetTextI18n")
    private void pauseAudio(){
            //Jika audio sedang dimainkan, maka audio dapat di pause
            if(mediaPlayer.isPlaying()){
                if(mediaPlayer != null){
                    mediaPlayer.pause();
                    Pause.setText("Lanjutkan");
                }
            }else {

                //Jika audio sedang di pause, maka audio dapat dilanjutkan kembali
                if(mediaPlayer != null){
                    mediaPlayer.start();
                    Pause.setText("Pause");
                }
            }
    }

    //Method untuk mengakhiri musik
    private void stopAudio(){
        mediaPlayer.stop();
        try {
            //Menyetel audio ke status awal
            mediaPlayer.prepare();
            mediaPlayer.seekTo(0);
        }catch (Throwable t){
            t.printStackTrace();
        }
        stateAwal();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play:
                playAudio();
                break;

            case R.id.pause:
                pauseAudio();
                break;

            case R.id.stop:
                stopAudio();
                break;
        }
    }
}
