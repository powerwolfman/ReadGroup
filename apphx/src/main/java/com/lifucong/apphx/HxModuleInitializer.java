package com.lifucong.apphx;

import com.lifucong.apphx.model.HxContactManager;
import com.lifucong.apphx.model.repository.ILocalUsersRepo;
import com.lifucong.apphx.model.repository.IRemoteUserRepo;

/**
 * Created by Administrator on 2016/10/18.
 */

public class HxModuleInitializer {

    private static HxModuleInitializer sInstance;

    public static HxModuleInitializer getInstance() {
        if (sInstance == null) {
            sInstance = new HxModuleInitializer();
        }
        return sInstance;
    }

    public void init(
            IRemoteUserRepo remoteUsersRepo,
            ILocalUsersRepo localUsersRepo){

        // 初始化联系操作内的本地及远程用户仓库
        HxContactManager.getInstance()
                .initLocalUsersRepo(localUsersRepo)
                .initRemoteUserRepo(remoteUsersRepo);

    }
}
