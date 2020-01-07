package cn.chenxun.system.service.impl;

import cn.chenxun.common.utils.PasswordUtil;
import cn.chenxun.common.utils.RandomUtil;
import cn.chenxun.system.mapper.user.UserMapper;
import cn.chenxun.system.model.user.Resource;
import cn.chenxun.system.model.user.Role;
import cn.chenxun.system.service.IRoleService;
import cn.chenxun.system.service.IUserService;
import cn.chenxun.common.exception.BusinessException;
import cn.chenxun.system.model.user.User;
import cn.chenxun.system.vo.user.UserTableVo;
import cn.chenxun.system.vo.user.UserVo;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.util.CollectionUtils;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

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

    private final IRoleService roleService;

    @Override
    public User queryByIdOrName(String loginName, String name) {
        User user = userMapper.queryByIdOrName(loginName, name);
        return user;
    }

    public UserVo queryCuerrentUserInfo(String loginName) {
        User user = userMapper.queryByIdOrName(loginName, null);
        Set<Resource> resourceSet = new HashSet<>();
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        if (!CollectionUtils.isEmpty(user.getRoleList())) {
            user.getRoleList().forEach(r -> {
                Role role = roleService.queryByIdOrName(r.getId(), null);
                resourceSet.addAll(role.getResourceList());
            });
        }
        List<Resource> resourceList = new ArrayList<>();
        resourceList.addAll(resourceSet);
        userVo.setResourceList(resourceList);
        return userVo;
    }

    @Override
    public void update(User user) {
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public void delete(List<Integer> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
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
        if (StringUtils.isBlank(userName)) {
            userName = "lisi";
        }
        user.setCreator(userName);
        user.setLastUpdator(userName);
        Date now = DateTime.now().toDate();
        validateUser(user);
        // 随机盐
        String salt = RandomUtil.getSalt();
        user.setSalt(salt);
        // 密码密文
        String password = PasswordUtil.encrypt("123456", salt);
        user.setPassword(password);
        user.setCreateTime(now);
        user.setLastUpdateTime(now);

        log.info("新增用户user={}", user);
        userMapper.insertSelective(user);
    }

    @Override
    public List<UserTableVo> queryByPage(UserTableVo userTableVo) {
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
