package br.edu.ladoss.simpifladoss.models;

import android.support.annotation.NonNull;

/**
 * Created by juan on 01/11/17.
 */

public class Contribuitor implements Comparable<Contribuitor>{
    private String name;
    private String github;
    private String function;
    private String profile_image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public Contribuitor(String name, String github, String function, String profile_image) {
        this.profile_image = profile_image;

        this.name = name;
        this.github = github;
        this.function = function;
    }

    @Override
    public int compareTo(@NonNull Contribuitor o) {
        return this.getFunction().compareTo(o.getFunction());
    }
}
