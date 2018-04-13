/*
 * Copyright (c) Siméon BLOCH
 * April 2018
 * Licence : CC BY-NC-SA 4.0
 */

package androidbasicsnanodegree.sbl.pornictourguideapp;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ContentFragment extends Fragment {

    // Following variables are used to retrieve arguments when the fragment is loaded

    private static final String CONTENT = "param1";
    private static final String AUDIO = "param2";
    private static final String SHOWAUDIOBUTTON = "param3" ;

    // Following variables are related to the chosen location

    private String content;
    private int audioId;
    boolean showAudioButton ;

    //Following objects are used for the audio playback

    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;

    public ContentFragment() {
        // Required empty public constructor
    }

    //Following method is used to release the media player when not needed anymore, in order
    //to free some memory

    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
            audioManager.abandonAudioFocus(audioFocusChangeListener);
        }
    }

    //Following method is initializing the audiofocuschangelistener

    private AudioManager.OnAudioFocusChangeListener audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mediaPlayer.start();
            }
            //Audio Playback is paused during a transient focus loss. Ducking seems unappropriated
            // because the whole text is useful
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mediaPlayer.pause();
            }
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }
        }
    };

    //When the audio file has been entirely played, the media player is released

    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    // newInstance() method is defined in order for the fragment to accept and display
    // 2 parameters related to the chosen location

    public static ContentFragment newInstance(String content, int audioId, boolean showAudioButton) {
        ContentFragment fragment = new ContentFragment();
        Bundle args = new Bundle();
        args.putString(CONTENT, content);
        args.putInt(AUDIO, audioId);
        args.putBoolean(SHOWAUDIOBUTTON, showAudioButton);
        fragment.setArguments(args);
        return fragment;
    }

    //In the onCreate() method, the arguments are retrieved

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            content = getArguments().getString(CONTENT);
            audioId = getArguments().getInt(AUDIO);
            showAudioButton = getArguments().getBoolean(SHOWAUDIOBUTTON) ;
        }
    }

    // The media player is released when the fragment lifecycle is stopped

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_content, container, false);

        //Initialize the audio manager
        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        //Initialize and set the back button
        Button backButton = rootView.findViewById(R.id.button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), MainActivity.class);
                startActivity(i);
            }
        });

        // Initialize and set the content text view
        TextView content = rootView.findViewById(R.id.contentText);
        content.setText(this.content);

        // Initialize and set the audio description button
        LinearLayout playAudioDescription = rootView.findViewById(R.id.audioDescriptionButton);

        if (showAudioButton) {
        playAudioDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                releaseMediaPlayer();

                int result = audioManager.requestAudioFocus(audioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(getContext(), audioId);
                    mediaPlayer.start();
                }
                mediaPlayer.setOnCompletionListener(onCompletionListener);
            }
        }); }
        else {
            ConstraintSet newSet = new ConstraintSet() ;
            ConstraintLayout contentLayout = rootView.findViewById(R.id.contentConstraintLayout) ;
            newSet.clone(contentLayout);
            newSet.setVisibility(R.id.audioDescriptionButton, ConstraintSet.INVISIBLE);
            newSet.setVisibility(R.id.audioDescriptionText, ConstraintSet.INVISIBLE);
            newSet.setVisibility(R.id.imageView, ConstraintSet.INVISIBLE);
            newSet.connect(R.id.contentText, ConstraintSet.TOP, R.id.contentConstraintLayout, ConstraintSet.TOP);
            contentLayout.setConstraintSet(newSet);
        }
        return rootView;
    }
}
