package cn.chenyh.springbootseed.service.impl;

import cn.chenyh.common.utils.PasswordUtil;
import cn.chenyh.common.utils.RandomUtil;
import cn.chenyh.springbootseed.mapper.user.UserMapper;
import cn.chenyh.springbootseed.service.IUserService;
import cn.chenyh.common.exception.BusinessException;
import cn.chenyh.springbootseed.model.user.User;
import cn.chenyh.springbootseed.vo.user.UserTableVo;
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

    private final UserMapper userMapper;

    @Override
    public User queryByIdOrName(Long userId, String userName) {
        return userMapper.queryByIdOrName(userId, userName);
    }

    @Override
    public int update(User user){
        return userMapper.updateByPrimaryKey(user);
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
//        User currentUser = SessionUtil.getCurrentUser();
//        String userName = currentUser.getLoginName();
        String userName = null;
        if(StringUtils.isBlank(userName)){
            userName = "lisi";
        }
        user.setCreator(userName);
        user.setLastUpdator(userName);
        Date now = new Date();
        validateUser(user);
        // 随机盐
        String salt = RandomUtil.getSalt();
        user.setSalt(salt);
        // 密码密文
        String password = PasswordUtil.encrypt("123456", salt);
        user.setPassword(password);
        user.setCreateTime(now);
        user.setLastUpdateTime(now);

        log.info("新增用户user={}",user);
        userMapper.insertSelective(user);
    }

    @Override
    public List<UserTableVo> queryByPage(UserTableVo userTableVo){
        if (userTableVo.getPageNum() != null && userTableVo.getRows() != null) {
            PageHelper.startPage(userTableVo.getPageNum(), userTableVo.getRows());
        }
        return userMapper.queryByPage(userTableVo);
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
    }

}
