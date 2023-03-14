package com.tuto.taffmediator.main;

import androidx.annotation.NonNull;

import java.util.Objects;

public class MainViewState {

    private final String sentence;
    private final boolean isMinusButtonEnabled;

    public MainViewState(String sentence, boolean isMinusButtonEnabled) {
        this.sentence = sentence;
        this.isMinusButtonEnabled = isMinusButtonEnabled;
    }

    public String getSentence() {
        return sentence;
    }

    public boolean isMinusButtonEnabled() {
        return isMinusButtonEnabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MainViewState that = (MainViewState) o;
        return isMinusButtonEnabled == that.isMinusButtonEnabled && Objects.equals(sentence, that.sentence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sentence, isMinusButtonEnabled);
    }

    @NonNull
    @Override
    public String toString() {
        return "MainViewState{" +
                "sentence='" + sentence + '\'' +
                ", isMinusButtonEnabled=" + isMinusButtonEnabled +
                '}';
    }
}
