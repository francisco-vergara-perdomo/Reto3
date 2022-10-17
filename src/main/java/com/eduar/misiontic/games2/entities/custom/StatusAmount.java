package com.eduar.misiontic.games2.entities.custom;

public class StatusAmount {

    private int completed;
    private int cancelled;

    public StatusAmount(int complete, int cancelled) {
        this.completed = complete;
        this.cancelled = cancelled;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public int getCancelled() {
        return cancelled;
    }

    public void setCancelled(int cancelled) {
        this.cancelled = cancelled;
    }
}
