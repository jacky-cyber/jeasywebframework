package com.jeasywebframework.service.dev;

import com.jeasywebframework.domain.dev.Tracker;

/**
 * Created by jeasywebframework@gmail.com on 13-12-26.
 */
public class TrackerHolder{

    ThreadLocal<Tracker> threadLocal = new ThreadLocal<Tracker>();
    ThreadLocal<Tracker> current = new ThreadLocal<Tracker>();

    private static TrackerHolder holder = new TrackerHolder();

    public static TrackerHolder getInstance() {
        return holder;
    }

    public void setRoot(Tracker inside) {
        threadLocal.set(inside);
    }

    public Tracker getRoot() {
        return threadLocal.get();
    }

    public void removeRoot() {
        threadLocal.remove();
    }

    public void setCurrent(Tracker inside) {
        current.set(inside);
    }

    public Tracker getCurrent() {
        return current.get();
    }

    public void removeCurrent() {
        current.remove();
    }


}
