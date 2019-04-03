package com.github.perryvaldez.seebooks.datalayer;

public interface WorkSession extends AutoCloseable {
    void commit();
    void rollback();
    void close();
}
