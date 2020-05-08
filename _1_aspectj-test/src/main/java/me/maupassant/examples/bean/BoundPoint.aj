package me.maupassant.examples.bean;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2020-01-09
 * @Desc:
 */
aspect BoundPoint {
    /*
     * privately declare a field on Point to hold the property
     * change support object.  `this' is a reference to a Point object.
     *//*
    public PropertyChangeSupport Point.support = new PropertyChangeSupport(this);

    *//*
     * Declare property change registration methods on Point,
     * and introduce implementation of the Serializable interface.
     *//*

    public void Point.addPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }

    public void Point.addPropertyChangeListener(String propertyName,
                                                PropertyChangeListener listener){
        support.addPropertyChangeListener(propertyName, listener);
    }

    public void Point.removePropertyChangeListener(String propertyName,
                                                   PropertyChangeListener listener) {
        support.removePropertyChangeListener(propertyName, listener);
    }

    public void Point.removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    public void Point.hasListeners(String propertyName) {
        support.hasListeners(propertyName);
    }

    declare parents: Point implements Serializable;

    *//**
     * Send property change event after X setter completes normally.
     * Use around advice to keep the old value on the stack.
     *//*
    void around(Point p): execution(void Point.setX(int)) && target(p) {
        int oldValue = p.getX();
        proceed(p);
        firePropertyChange(p, "x", oldValue, p.getX());
    }

    *//**
     * Send property change event after Y setter completes normally.
     * Use around advice to keep the old value on the stack.
     *//*
    void around(Point p): execution(void Point.setY(int)) && target(p) {
        int oldValue = p.getY();
        proceed(p);
        firePropertyChange(p, "y", oldValue, p.getY());
    }

    *//*
     * Utility to fire the property change event.
     *//*
    void firePropertyChange(Point p,
                            String property,
                            double oldval,
                            double newval) {
        p.support.firePropertyChange(property,
                new Double(oldval),
                new Double(newval));
    }*/
}
