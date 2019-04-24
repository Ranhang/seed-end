package cn.chenyh.springbootseed.service.impl;

import cn.chenyh.common.utils.PasswordUtil;
import cn.chenyh.common.utils.RandomUtil;
import cn.chenyh.springbootseed.mapper.user.UserMapper;
import cn.chenyh.springbootseed.service.IUserService;
import cn.chenyh.common.exception.BusinessException;
import cn.chenyh.springbootseed.model.user.User;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * 用户Service
 *
 * @author zhaozhenyao
 * @date 2018/5/9
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

//    @Autowired
    private final UserMapper userMapper;

    @Override
    public User queryByIdOrName(Long userId, String userName) {
        return userMapper.queryByIdOrName(userId, userName);
    }

    @Override
    public void update(User user){
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public void delete(List<Integer> ids) {
        if(!CollectionUtils.isEmpty(ids)){
            ids.forEach(id -> {
                userMapper.deleteByPrimaryKey(id);
            });
        }
    }

    @Override
    public List<User> queryUserByIds(List<Long> userIds) {
        return this.userMapper.selectUserByIds(userIds);
    }

    @Override
    public void addUser(User user) throws ParseException {
        Date now = new Date();
        validateUser(user);
        // 随机盐
        String salt = RandomUtil.getSalt();
        user.setSalt(salt);
        // 密码密文
        String password = PasswordUtil.encrypt(user.getPassword(), salt);
        user.setPassword(password);
        user.setCreateTime(now);
        user.setLastUpdateTime(now);

        log.info("新增用户user={}",user);
        userMapper.insert(user);
    }

    @Override
    public List<User> queryByPage(User user){
        if (user.getPage() != null && user.getRows() != null) {
            PageHelper.startPage(user.getPage(), user.getRows());
        }
        return userMapper.queryByPage(user);
    }
    /**
     * 检查用户信息是否正确
     *
     * @param user 用户信息
     */
    private void validateUser(User user) {
        if (user == null) {
            throw new BusinessException("用户不存在");
        }
        if (StringUtils.isBlank(user.getLoginName())) {
            throw new BusinessException("用户名不能为空");
        }
        if (StringUtils.isBlank(user.getPassword())) {
            throw new BusinessException("用户密码不能为空");
        }
    }

}
