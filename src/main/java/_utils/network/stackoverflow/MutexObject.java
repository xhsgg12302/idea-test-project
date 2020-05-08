package _utils.network.stackoverflow;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-12-13
 * @Desc:
 */
public class MutexObject {

    private static class getInstance {
        private static MutexObject singleton = new MutexObject();
    }

    public static MutexObject getInstance() {
        return MutexObject.getInstance.singleton;
    }

}
