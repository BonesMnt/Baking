package com.mnt.bones.baking;

/**
 * Created by fabio.a on 05/02/18.
 */

public interface AsyncTaskDelegate {
    void onPreStart();
    void onFinish(Object output);
}
