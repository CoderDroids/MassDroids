package com.CoderDroids.MassDroids.Sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {

    //region Singleton

    private static SoundManager instance = null;
    public static SoundManager getInstance()
    {
        if( instance == null )
            instance = new SoundManager();

        return instance;
    }
    //endregion

    public Music backgroundMusic;
    public Sound click;

    private SoundManager()
    {
        backgroundMusic =  Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        backgroundMusic.setLooping(true);
        click = Gdx.audio.newSound(Gdx.files.internal("click.mp3"));
    }


}
