package com.caimao.concurrent.queue;

import java.util.LinkedList;

import static java.lang.Thread.currentThread;

public class EventQueue {
    private final int max;
    static class Event{}

    private final LinkedList<Event> eventQueue = new LinkedList<>();
    private final static int DEFAULT_MAX_ENENT = 10;
    public EventQueue(){
        this(DEFAULT_MAX_ENENT);
    }
    public EventQueue(int max){
        this.max = max;
    }

    public void offer(Event event){
        synchronized (eventQueue){
            while(eventQueue.size()>=max){
                try{
                    console("the queue is full");
                    eventQueue.wait();
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
            console("the new event is submitted");
            eventQueue.addLast(event);
            eventQueue.notify();
        }
    }

    public Event take(){
        synchronized (eventQueue){
            while(eventQueue.isEmpty()){
                console("the queue is empty");
                try{
                    eventQueue.wait();
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
            Event event = eventQueue.removeFirst();
            this.eventQueue.notifyAll();
            console("the event "+ event + "is handled");
            return event;
        }
    }

    private void console(String msg){
        System.out.printf("%s:%s\n", currentThread().getName(), msg);
    }
}

























