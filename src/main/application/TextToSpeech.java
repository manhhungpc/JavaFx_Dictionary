package main.application;

import com.sun.speech.freetts.VoiceManager;
import com.sun.speech.freetts.Voice;

public class TextToSpeech {

    Voice voice;

    public TextToSpeech(String words) {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        voice = VoiceManager.getInstance().getVoice("kevin16");
        if (voice != null) {
            voice.allocate();// Allocating Voice
            try {
//                voice.setRate(100);// Setting the rate of the voice
//                voice.setPitch(150);// Setting the Pitch of the voice
//                voice.setVolume(5);// Setting the volume of the voice
                voice.speak(words);;// Calling speak() method

            } catch (Exception e1) {
                e1.printStackTrace();
            }

        } else {
            throw new IllegalStateException("Cannot find voice: kevin16");
        }
    }

    public void SpeakText(String words) {
        voice.speak(words);
    }

    public static void main(String[] args) {
        TextToSpeech textToSpeech = new TextToSpeech("ability table coke cook pet cat dog");
    }

}
