package com.example.voice;

import java.util.ArrayList;

/* File name : Animal.java */
interface IAudioDoneCallback {
    public void onPartial(ArrayList<String> results);
    public void onResults(ArrayList<String> results);
    public void onEnd();
    public void onError(int error);
}
