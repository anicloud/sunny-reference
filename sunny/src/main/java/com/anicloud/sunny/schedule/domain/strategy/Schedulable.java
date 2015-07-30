package com.anicloud.sunny.schedule.domain.strategy;

import java.io.Serializable;

/**
 * Created by huangbin on 7/18/15.
 */
public interface Schedulable {
    public boolean start();
    public boolean stop();
    public boolean pause();
    public boolean resume();
}
