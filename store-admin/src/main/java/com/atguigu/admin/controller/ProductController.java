package com.atguigu.admin.controller;


import com.atguigu.admin.service.ProductService;
import com.atguigu.admin.utils.AliyunOSSUtils;
import com.atguigu.param.ProductSaveParam;
import com.atguigu.param.ProductSearchParam;
import com.atguigu.pojo.Product;
import com.atguigu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private AliyunOSSUtils aliyunOSSUtils;

    @GetMapping("/list")
    public R adminSearch(ProductSearchParam productSearchParam) {
        return productService.search(productSearchParam);
    }

    @PostMapping("/upload")
    public Object upload(MultipartFile img) throws Exception {

        String filename = img.getOriginalFilename();
        String contentType = img.getContentType();
        long millis = System.currentTimeMillis();

        filename = millis + filename; //防止重复

        String url = aliyunOSSUtils.uploadImage(filename, img.getBytes(), contentType, 1000);
        System.out.println("url = " + url);
        return R.ok("上传成功", url);
    }

    @PostMapping("/save")
    public R adminSave(ProductSaveParam productSaveParam) {
        return productService.save(productSaveParam);
    }

    @PostMapping("/update")
    public R adminUpdate(Product product) {
        return productService.update(product);
    }

    @PostMapping("/remove")
    public R adminRemove(Integer productId){
        return productService.remove(productId);
    }
}
