import cn.chenxun.SeedApplication;
import cn.chenxun.system.mapper.user.RoleMapper;
import cn.chenxun.system.model.user.Role;
import cn.chenxun.system.model.user.User;
import cn.chenxun.system.service.IRoleService;
import cn.chenxun.system.service.IUserService;
import cn.chenxun.system.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = SeedApplication.class)
@RunWith(SpringRunner.class)
public class ModuleInfoMapperTest {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IUserService userService;

    @Test
    public void findByModuleId() {
        Role role = roleService.queryByIdOrName(1L, null);
        System.out.println(role);
    }

    @Test
    public void  queybyidOrName(){
        User admin = userService.queryByIdOrName("admin", null);
        System.out.println(admin);

    }
}
