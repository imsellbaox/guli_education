package study.xxx.educms.service;

import study.xxx.educms.pojo.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author xxx
 * @since 2022-01-09
 */
public interface CrmBannerService extends IService<CrmBanner> {

    List<CrmBanner> selectBanner();
}
