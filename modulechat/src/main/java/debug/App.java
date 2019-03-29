package debug;

import com.example.baselibrary.base.BaseModuleApplication;
import com.ldl.modulechat.utils.ChatHelper;

/**
 * Author：LDL Time：2019/3/20
 * Class Comment：
 */
public class App extends BaseModuleApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        ChatHelper.instance.init(this);
    }
}
