package com.petter.web;

import com.petter.entity.Validation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * @author hongxf
 * @since 2017-02-21 11:16
 */
@Controller
public class ValidateController {

    @RequestMapping("/validation")
    public String validation(Model model){
        model.addAttribute("validation",new Validation());
        return "validation";
    }


    @RequestMapping("/validationAdd")
    public String validationAdd(@Valid Validation validation, BindingResult result, Model model){
        //有错误信息.
        model.addAttribute("validation", validation);
        //添加自定义的错误信息
        result.rejectValue("name", "misFormat", "这个name已经注册过了！");
        if(result.hasErrors()){
            List<ObjectError> list = result.getAllErrors();
            for(ObjectError  error:list){
                System.out.println(error.getCode()+"---" +
                        Arrays.toString(error.getArguments()) +"---"+ error.getDefaultMessage());
            }

            return "validation";
        }

        return "validation";
    }
}
