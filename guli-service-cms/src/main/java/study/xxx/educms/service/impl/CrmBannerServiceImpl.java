package study.xxx.educms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.cache.annotation.Cacheable;
import study.xxx.educms.pojo.CrmBanner;
import study.xxx.educms.mapper.CrmBannerMapper;
import study.xxx.educms.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author xxx
 * @since 2022-01-09
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {
    @Override
    @Cacheable(value = "crmBanners",key = "'selectIndexList'")
    public List<CrmBanner> selectBanner() {
        QueryWrapper<CrmBanner> wrapper = new QueryWrapper();
        wrapper.orderByDesc("id");
        wrapper.last("limit 2");
        return baseMapper.selectList(wrapper);
    }
}
