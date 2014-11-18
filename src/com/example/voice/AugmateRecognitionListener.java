package com.example.voice;

import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;

public class AugmateRecognitionListener implements RecognitionListener{

    private static final String TAG = "com.augmate.ups.cycle.voice";
    private final IAudioDoneCallback myCallback;
    private boolean isProcessing = false;
    private boolean hasResults = false;

    AugmateRecognitionListener(IAudioDoneCallback iAudioDoneCallback){
        myCallback = iAudioDoneCallback;
    }

    @Override
    public void onReadyForSpeech(Bundle params) {
        isProcessing = true;
        Log.d(TAG, "on ready for speech");
    }

    @Override
    public void onBeginningOfSpeech() {
        hasResults = false;
        Log.d(TAG, "on beginning of speech");
    }

    @Override
    public void onRmsChanged(float rmsdB) {}

    @Override
    public void onBufferReceived(byte[] buffer) {
        Log.d(TAG, "on buffer received");
    }

    @Override
    public void onEndOfSpeech() {
        myCallback.onEnd();
        Log.d(TAG, "on end of speech");
    }

    @Override
    public void onError(int error) {
        myCallback.onError(error);
        isProcessing = false;
        Log.d(TAG, "on error: " + error);
    }

    @Override
    public void onResults(Bundle results) {
        ArrayList<String> stringArrayList = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

        for (String str : stringArrayList) {
            Log.d(TAG, "result=" + str);
        }
        myCallback.onResults(stringArrayList);
        isProcessing = false;
        hasResults = true;
        Log.d(TAG, "on results");
    }

    @Override
    public void onPartialResults(Bundle partialResults) {
        ArrayList<String> stringArrayList = partialResults.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        myCallback.onPartial(stringArrayList);
        Log.d(TAG, "on partial results "+ TextUtils.join(", ", stringArrayList));
    }

    @Override
    public void onEvent(int eventType, Bundle params) {
        Log.d(TAG, "on event");
    }

    public boolean isProcessing(){
        return isProcessing;
    }

    public boolean hasResults() {
        return hasResults;
    }
}
