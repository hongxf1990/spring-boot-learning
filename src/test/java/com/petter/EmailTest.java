package com.petter;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @since 2017-02-19 16:54
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailTest {

    @Resource
    private JavaMailSender mailSender;

    //@Test
    public void sendSimpleEmail() {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("570140280@qq.com");//发送者.
        message.setTo("1027510845@qq.com");//接收者.
        message.setSubject("测试邮件（邮件主题）");//邮件主题.
        message.setText("这是邮件内容");//邮件内容.
        message.setSentDate(new Date());

        mailSender.send(message);//发送邮件
    }

    /**
     * 测试发送附件.(这里发送图片.)
     * @throws MessagingException
     */
    //@Test
    public void sendAttachmentsEmail() throws MessagingException {
        //这个是javax.mail.internet.MimeMessage下的，不要搞错了。
        MimeMessage mimeMessage =  mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        //基本设置.
        helper.setFrom("570140280@qq.com");//发送者.
        helper.setTo("1027510845@qq.com");//接收者.
        helper.setSubject("测试附件（邮件主题）");//邮件主题.
        helper.setText("这是邮件内容（有附件哦.）");//邮件内容.

        //org.springframework.core.io.FileSystemResource下的:
        //附件1,获取文件对象.
        FileSystemResource file1 = new FileSystemResource(new File("C:\\Users\\Administrator\\Pictures\\加菲猫\\加菲1.jpg"));
        //添加附件，这里第一个参数是在邮件中显示的名称，也可以直接是head.jpg，但是一定要有文件后缀，不然就无法显示图片了。
        helper.addAttachment("加菲猫1.jpg", file1);
        //附件2
        FileSystemResource file2 = new FileSystemResource(new File("C:\\Users\\Administrator\\Pictures\\加菲猫\\加菲2.jpg"));
        helper.addAttachment("加菲猫2.jpg", file2);

        mailSender.send(mimeMessage);
    }

    /**
     * 邮件中使用静态资源.
     * @throws Exception
     */
    //@Test
    public void sendInlineMail() throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        //基本设置.
        helper.setFrom("570140280@qq.com");//发送者.
        helper.setTo("1027510845@qq.com");//接收者.
        helper.setSubject("测试静态资源（邮件主题）");//邮件主题.
        // 邮件内容，第二个参数指定发送的是HTML格式
        //说明：嵌入图片<img src='cid:head'/>，其中cid:是固定的写法，而aaa是一个contentId。
        helper.setText("<body>这是图片：<img src='cid:head' /><a href='www.baidu.com' target='_blank'>百度</a></body>", true);

        FileSystemResource file = new FileSystemResource(new File("C:\\Users\\Administrator\\Pictures\\加菲猫\\加菲3.jpg"));
        //这里需要注意的是addInline函数中资源名称head需要与正文中cid:head对应起来
        helper.addInline("head",file);

        mailSender.send(mimeMessage);
    }


    /**
     * 模板邮件；
     * @throws Exception
     */
    //@Test
    public void sendTemplateMail() throws Exception {

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        //基本设置.
        helper.setFrom("570140280@qq.com");//发送者.
        helper.setTo("1027510845@qq.com");//接收者.
        helper.setSubject("模板邮件（邮件主题）");//邮件主题.

        Map<String, Object> model = new HashMap<>();
        model.put("username", "洪学飞");

        //使用freemarker生成字符串
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_25);
        // 设定去哪里读取相应的ftl模板
        cfg.setClassForTemplateLoading(this.getClass(), "/templates");
        // 在模板文件目录中寻找名称为name的模板文件
        Template template   = cfg.getTemplate("email.ftl");

        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        System.out.println(html);
        helper.setText(html, true);

        mailSender.send(mimeMessage);
    }

    @Test
    public void test() {
        System.out.println("test");
    }
}
