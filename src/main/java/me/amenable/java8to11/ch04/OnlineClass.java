package me.amenable.java8to11.ch04;

import java.util.Optional;

public class OnlineClass {

    private Integer id;
    private String title;
    private boolean closed;

    public Progress progress;

    public OnlineClass(Integer id, String title, boolean closed) {
        this.id = id;
        this.title = title;
        this.closed = closed;
    }


    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isClosed() {
        return closed;
    }

    public Optional<Progress> getProgress() {
        return Optional.of(progress);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }
}
