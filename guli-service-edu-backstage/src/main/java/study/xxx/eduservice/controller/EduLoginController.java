package study.xxx.eduservice.controller;

import org.springframework.web.bind.annotation.*;
import study.xxx.publicutils.Result;

/**
 * @author: V
 * @param:
 * @description:
 */
@RestController
@RequestMapping("eduservice/user")
/*@CrossOrigin*/ /**开发时 不用api网关 临时解决跨域问题*/   //在api_gatway网关已经统一配置，无需再配置，否则报错
public class EduLoginController {
    @PostMapping("login")
    public Result login(){
        return Result.ok().Data("token","admin");
    }
    @GetMapping("info")
    public Result info(){
        return Result.ok().Data("roles","[admin]").Data("name","admin").Data("avatar","https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fi0.hdslb.com%2Fbfs%2Farticle%2F31f4929b009e6156003a6181b25b070cd65c8bdd.jpg&refer=http%3A%2F%2Fi0.hdslb.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1642296243&t=6326c9ded6971561d3ec7d4ea71f677c");
    }
}
