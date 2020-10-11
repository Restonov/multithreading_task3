package by.restonov.multithreading.entity;

import by.restonov.multithreading.state.BaseState;

public interface BaseEntity {
    BaseState getCurrentState();
    void setCurrentState(BaseState state);
    public long getId();
    public void setId(long terminalId);
}
