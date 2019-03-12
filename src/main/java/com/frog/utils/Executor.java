package com.frog.utils;

/**
 * //TODO
 *
 * @author Kudou Shinichi
 * @version 1.0
 **/
public interface Executor {
    <T> T query(String statement, Object parameter);
}
