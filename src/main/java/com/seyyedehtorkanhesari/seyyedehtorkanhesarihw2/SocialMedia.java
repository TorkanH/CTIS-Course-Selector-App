package com.seyyedehtorkanhesari.seyyedehtorkanhesarihw2;

public class SocialMedia {
    private int id;
    private String mediaName;
    private int numOfLike;
    private int numOfComment;
    public SocialMedia(int id, String mediaName, int numOfLike, int numOfComment) {
        this.id = id;
        this.mediaName = mediaName;
        this.numOfLike = numOfLike;
        this.numOfComment = numOfComment;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getMediaName() {
        return mediaName;
    }
    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }
    public int getNumOfLike() {
        return numOfLike;
    }
    public void setNumOfLike(int numOfLike) {
        this.numOfLike = numOfLike;
    }
    public int getNumOfComment() {
        return numOfComment;
    }
    public void setNumOfComment(int numOfComment) {
        this.numOfComment = numOfComment;
    }
}
