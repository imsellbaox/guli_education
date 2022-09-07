package study.xxx.ucenter.service;

import study.xxx.ucenter.pojo.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import study.xxx.ucenter.pojo.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author xxx
 * @since 2022-01-10
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(UcenterMember member);

    void register(RegisterVo registerVo);

    UcenterMember getOpenIdMember(String openid);

    UcenterMember getMemberInfo(String id);

    Integer countRegister(String day);
}
