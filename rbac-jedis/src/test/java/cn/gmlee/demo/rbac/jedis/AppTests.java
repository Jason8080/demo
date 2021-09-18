package cn.gmlee.demo.rbac.jedis;

import cn.hll.css.dependencies.starter.rbac.bean.RbacRedis;
import cn.hll.css.dependencies.starter.rbac.mod.RbacUser;
import cn.hll.tools.base.mod.Login;
import cn.lalaframework.utils.Constants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * .
 *
 * @author Jas°
 * @date 2021/8/11 (周三)
 */
@ActiveProfiles("dev")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class AppTests {

    static {
        System.setProperty(Constants.KEY_HLL_ENV, "dev");
        System.setProperty(Constants.KEY_HLL_APP_ID, "info-rbac-starter-svc");
        System.setProperty(Constants.KEY_CONSUL_HOST, "consul-dev.myhll.cn");
        System.setProperty(Constants.KEY_CONSUL_PORT, "80");
        System.setProperty(Constants.KEY_CONSUL_TOKEN, "616bb402-e250-2d7a-668e-8d1540556907");
    }

    @Resource
    private RbacRedis rbacRedis;

    @Test
    public void test() throws Exception {
        Login<RbacUser, Object, Object, Object, Object> login = rbacRedis.getLogin("103:wIGB18FabF4qxphjpAPYWjaNZDA3bz97", RbacUser.class);
        System.out.println(login.getUser());
    }
}
