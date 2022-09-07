package study.xxx.ucenter.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import study.xxx.pojo.VoMemberWebOrder;
import study.xxx.publicutils.JwtUtils;
import study.xxx.publicutils.Result;
import study.xxx.ucenter.pojo.vo.RegisterVo;
import study.xxx.ucenter.pojo.UcenterMember;
import study.xxx.ucenter.service.UcenterMemberService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author xxx
 * @since 2022-01-10
 */
@RestController
@RequestMapping("/educenter/member")
@Api("前端登陆注册接口")
//@CrossOrigin   //在api_gatway网关已经统一配置，无需再配置，否则报错
public class UcenterMemberController {
    @Autowired
    private UcenterMemberService ucenterMemberService;

    @ApiOperation(value = "登录")
    @PostMapping("login")
    public Result loginUser(@RequestBody UcenterMember member) {
        //member封装手机和密码
        //调用login，返回token
        //token由jwt生成
        String token = ucenterMemberService.login(member);
        return Result.ok().Data("token", token);
    }

    @ApiOperation(value = "注册")
    @PostMapping("register")
    public Result registerUser(@RequestBody RegisterVo registerVo) {
        ucenterMemberService.register(registerVo);
        return Result.ok();
    }

    @ApiOperation(value = "根据token获取用户信息")
    @GetMapping("getMemberInfo")
    public Result getMemberInfo(HttpServletRequest request){
        //根据token获取id
        String id = JwtUtils.getMemberIdByJwtToken(request);
        //根据id查询对象返回
        UcenterMember memberInfo = ucenterMemberService.getMemberInfo(id);
        return Result.ok().Data("userInfo",memberInfo);
    }

    @ApiOperation(value = "feign远程调用用户信息到订单")
    @PostMapping("getMemberToOrder/{memberId}")
    public VoMemberWebOrder getMemberToOrder(@PathVariable String memberId){
        UcenterMember memberInfo = ucenterMemberService.getMemberInfo(memberId);
        VoMemberWebOrder voMemberWebOrder = new VoMemberWebOrder();
        BeanUtils.copyProperties(memberInfo,voMemberWebOrder);
        return voMemberWebOrder;
    }

    @ApiOperation(value = "feign远程获得该日期的注册人数")
    @GetMapping("countRegister/{day}")
    public Result countRegister(@PathVariable String day) {
        Integer count = ucenterMemberService.countRegister(day);
        return Result.ok().Data("countRegister",count);
    };
}

