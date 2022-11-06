package com.mcdead.wallpass;

import java.util.concurrent.LinkedBlockingQueue;

public class AppEventQueue {
    private LinkedBlockingQueue<AppEvent> m_appEventsQueue;

    public AppEventQueue() {
        m_appEventsQueue = new LinkedBlockingQueue<>();
    }

    public AppEvent take() {
        return m_appEventsQueue.poll();
    }

    public boolean put(final AppEvent newItem) {
        return m_appEventsQueue.offer(newItem);
    }
}
