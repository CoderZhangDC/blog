package com.dachang.web;

import com.dachang.service.FileService;
import com.dachang.util.UUidUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : xsh
 * @create : 2020-03-17 - 2:28
 * @describe:https://blog.csdn.net/qq_33924360/article/details/89153493?depth_1-utm_source=distribute.pc_relevant.none-task&utm_source=distribute.pc_relevant.none-task
 */
@RestController
@RequestMapping("/upload")
public class UploadController {


    @Autowired
    private FileService fileService;

    @Value("${baseUploadUrl}")
    private String url;

    private final Logger logger= LoggerFactory.getLogger(this.getClass());


    /**
     * 后台博客添加页面 上传图片
     * @param upfile 上传文件对象
     * @param request
     * @return
     * @throws IOException
     * @describe   Editormd编辑器前端规定了后台必须返回给前端一个map且形式为{"success":1,message:"上传成功","url":图片链接}
     */
    @PostMapping(value = "/uploadBlogInputImg")
    public Map<String,Object> uploadBlogInputImg(@RequestParam(value = "editormd-image-file", required = false) MultipartFile upfile, HttpServletRequest request )throws IOException {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        System.out.println(request.getContextPath());
        String fileName = upfile.getOriginalFilename();//获取上传的图片文件名
        System.out.println("上传图片的文件名："+fileName);
        /*先将图片保存到缓存路径中，再从缓存路径内将图片上传到七牛云*/
        File file=new File(url+System.currentTimeMillis()+fileName);
        try{
            //将MulitpartFile文件转化为file文件格式
            upfile.transferTo(file);
            //根据uploadBlogImg+_UUID_+原文件名组合成新上传的文件名,防止文件名重复
            String uploadFileName="uploadBlogImg_"+ UUidUtils.getUUID().substring(0,5)+fileName;

            Map response = fileService.uploadFile(file,uploadFileName);
            logger.info("上传之后的文件名为："+uploadFileName);
            String imageUrl = (String)response.get("imageUrl");

            resultMap.put("success", 1);
            resultMap.put("message", "上传成功！");
            resultMap.put("url",imageUrl);  //上传图片后，图片链接在markdown内回显
        }catch (Exception e){
            resultMap.put("success", 0);
            resultMap.put("message", "上传失败！");
            e.printStackTrace();
        }
        return resultMap;
    }
}
