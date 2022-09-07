package study.xxx.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import study.xxx.exception.guliException;
import study.xxx.publicutils.JwtUtils;
import study.xxx.publicutils.MD5;
import study.xxx.ucenter.pojo.UcenterMember;
import study.xxx.ucenter.mapper.UcenterMemberMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import study.xxx.ucenter.pojo.vo.RegisterVo;
import study.xxx.ucenter.service.UcenterMemberService;


/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author xxx
 * @since 2022-01-10
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @Override
    public String login(UcenterMember member) {
        //获取登录手机和密码
        String phone = member.getMobile();
        String password = member.getPassword();

        //手机和密码非空判断
        if (StringUtils.isEmpty(phone)||StringUtils.isEmpty(password)) {
            throw new guliException(20001, "登录失败!用户名或密码不能为空");
        }
        //判断手机密码是否正确
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper();
        wrapper.eq("mobile",phone);
        UcenterMember selectMember = baseMapper.selectOne(wrapper);
        if (selectMember == null){
         throw new guliException(20001,"登录失败！用户名或密码不正确！");
        }
        if (!MD5.encrypt(password).equals(selectMember.getPassword())){
        throw new guliException(20001,"登录失败！用户名或密码不正确！");
        }
        //判断用户是否被禁用
        if (selectMember.getIsDisabled()){
        throw new guliException(20001,"登录失败！该用户已被禁用，请联系管理员！");
        }
        //登录成功
        String jwtToken = JwtUtils.getJwtToken(selectMember.getId(),selectMember.getNickname());
        return jwtToken;
    }

    @Override
    public void register(RegisterVo registerVo) {
    String code = registerVo.getCode();
    String phone = registerVo.getMobile();
    String name = registerVo.getNickname();
    String password = registerVo.getPassword();

    //非空判断
        if (StringUtils.isEmpty(phone)||StringUtils.isEmpty(password)||StringUtils.isEmpty(name)){
        throw new guliException(20001,"注册失败，手机,密码,用户名不可为空！");
    }
    //判断验证码
    String redisCode = redisTemplate.opsForValue().get(phone);
    if (!redisCode.equals(code)){
        throw new guliException(20001,"注册失败，验证码不正确！");
    }
    //判断手机是否重复
    QueryWrapper<UcenterMember> wrapper = new QueryWrapper();
    wrapper.eq("mobile",phone);
    UcenterMember selectOne = baseMapper.selectOne(wrapper);
    if (selectOne != null){
        throw new guliException(20001,"注册失败，该手机号已经注册！");
    }
    //注册成功 保存到数据库
    UcenterMember ucenterMember = new UcenterMember();
    ucenterMember.setMobile(phone);
    ucenterMember.setNickname(name);
    ucenterMember.setPassword(MD5.encrypt(password));
    ucenterMember.setIsDisabled(false);
    ucenterMember.setAvatar("https://guli-edu-20201.oss-cn-beijing.aliyuncs.com/2020/10/08/3a6bf3d4a85f415693e062db5fb17df8file.png");
    baseMapper.insert(ucenterMember);
    }

    @Override
    public UcenterMember getOpenIdMember(String openid) {
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("openid",openid);
        UcenterMember member = baseMapper.selectOne(wrapper);
        return member;
    }

    @Override
    @Cacheable(value = "memberInfo",key = "'frontMemberInfo'")
    public UcenterMember getMemberInfo(String id) {
        UcenterMember memberInfo = baseMapper.selectById(id);
        return memberInfo;
    }

    @Override
    public Integer countRegister(String day) {
        return baseMapper.countRegister(day);
    }
}
