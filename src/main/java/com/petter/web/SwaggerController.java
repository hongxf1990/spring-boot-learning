package com.petter.web;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Swagger2默认将所有的Controller中的RequestMapping方法都会暴露，然而在实际开发中，我们并不一定需要把所有API都提现在文档中查看，
 * 这种情况下，使用注解@ApiIgnore来解决，如果应用在Controller范围上，则当前Controller中的所有方法都会被忽略，如果应用在方法上，则对应用的方法忽略暴露API。
 * 注解@ApiOperation和@ApiParam可以理解为API说明，多动手尝试就很容易理解了。
 * 如果我们不使用这样注解进行说明，Swagger2也是有默认值的
 * 在 http://localhost:8080/swagger-ui.html 显示页面的右上角有api_key ，springfox-swagger 2.2.2 版本并没有进行处理，
 * 我们可以自己添加拦截器拦截 /v2/api-docs来处理我们API文档的访问权限，如果要更严格更灵活的控制，可能需要修改源码来实现了。
 *
 * @author hongxf
 * @since 2017-02-23 16:07
 */
@Controller
@RequestMapping(value = "/api/test")
public class SwaggerController {

    @ResponseBody
    @RequestMapping(value = "/show", method= RequestMethod.POST)// 这里指定RequestMethod，如果不指定Swagger会把所有RequestMethod都输出，在实际应用中，具体指定请求类型也使接口更为严谨。
    @ApiImplicitParam(name = "name", value = "姓名", paramType = "form", dataType = "string")
    @ApiOperation(value = "测试接口", httpMethod = "POST", response = String.class, notes = "测试接口详细描述")
    public String show(
            @RequestParam(name = "name") String name){
        return "success";
    }
}
