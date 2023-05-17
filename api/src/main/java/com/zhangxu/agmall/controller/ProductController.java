package com.zhangxu.agmall.controller;

import com.zhangxu.agmall.dao.ProductMapper;
import com.zhangxu.agmall.entity.Product;
import com.zhangxu.agmall.entity.ProductParams;
import com.zhangxu.agmall.service.ProductCommentService;
import com.zhangxu.agmall.service.ProductImgService;
import com.zhangxu.agmall.service.ProductService;
import com.zhangxu.agmall.vo.ResStatus;
import com.zhangxu.agmall.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author zhangxu
 * @create 2023-04-13
 */
@RestController
@CrossOrigin
@RequestMapping("/product")
@Api(value = "提供商品相关的接口", tags = "商品管理")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCommentService productCommentService;

    @GetMapping("/detail-info/{productId}")
    @ApiOperation("商品基本信息查询接口")
    public ResultVO getProductBasicInfo(@PathVariable("productId") String pid) {
        ResultVO productBaseInfo = productService.getProductBaseInfo(pid);
        return productBaseInfo;
    }

    @GetMapping("/detail-params/{productId}")
    @ApiOperation("商品参数信息查询接口")
    public ResultVO getProductParamsInfo(@PathVariable("productId") String pid) {
        ResultVO productParamsById = productService.getProductParamsById(pid);
        return productParamsById;
    }

    @GetMapping("/detail-comments/{productId}/{pageNum}/{limit}")
    @ApiOperation("商品评论信息查询接口")
    @ApiImplicitParams(
            {@ApiImplicitParam(dataType = "String", name = "productId", value = "商品ID", required = true),
                    @ApiImplicitParam(dataType = "int", name = "pageNum", value = "当前页码", required = true),
                    @ApiImplicitParam(dataType = "int", name = "limit", value = "每页条数", required = true)}

    )
    public ResultVO getProductcommentsInfo(@PathVariable("productId") String pid,
                                           @PathVariable("pageNum") int pageNum,
                                           @PathVariable("limit") int limit) {
        ResultVO resultVO = productCommentService.listCommentsByproduct(pid, pageNum, limit);
        return resultVO;
    }

    @GetMapping("/detail-comments-level-counts/{productId}")
    @ApiOperation("商品评论数量、评论等级、好评率查询接口")
    public ResultVO getProductCommentsCountInfo(@PathVariable("productId") String pid) {
        ResultVO resultVO = productCommentService.getCommentsCountByProductId(pid);
        return resultVO;
    }

    /**
     * 按照类别查询商品，并且查询到的商品进行分页。
     *
     * @param cid
     * @param pageNum
     * @param limit
     * @return
     */
    @GetMapping("/listByCid/{categoryId}")
    @ApiOperation("根据类别查询商品分页接口")
    @ApiImplicitParams(
            {@ApiImplicitParam(dataType = "String", name = "categoryId", value = "商品三级分类", required = true),
                    @ApiImplicitParam(dataType = "int", name = "pageNum", value = "当前页码", required = true),
                    @ApiImplicitParam(dataType = "int", name = "limit", value = "每页条数", required = true)}

    )
    public ResultVO getProductByCidAndPage(@PathVariable("categoryId") int cid,
                                           int pageNum,
                                           int limit) {
        ResultVO resultVO = productService.getProductsByCategoryId(cid, pageNum, limit);
        return resultVO;
    }

    /**
     * 根据类别 查询商品的品牌
     *
     * @param cid
     * @return
     */
    @GetMapping("/listBrandsByCid/{categoryId}")
    @ApiOperation("根据类别查询类别商品所有品牌接口")
    @ApiImplicitParams({@ApiImplicitParam(dataType = "String", name = "categoryId", value = "类别ID", required = true)})
    public ResultVO getBrandsInfo(@PathVariable("categoryId") int cid) {
        ResultVO resultVO = productService.listBrands(cid);
        return resultVO;
    }

    /**
     * 根据关键字查询商品，并且分页
     *
     * @param keyword
     * @param pageNum
     * @param limit
     * @return
     */
    @GetMapping("/listByKeyword")
    @ApiOperation("根据关键字查询商品接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "String", name = "keyword", value = "查询关键字", required = true),
            @ApiImplicitParam(dataType = "Integer", name = "pageNum", value = "当前页码", required = true),
            @ApiImplicitParam(dataType = "Integer", name = "limit", value = "每页数据条数", required = true)
    })
    public ResultVO searchBrand(String keyword, Integer pageNum, Integer limit) {
        ResultVO resultVO = productService.searchProduct(keyword, pageNum, limit);
        return resultVO;
    }

    /**
     * 根据关键字查询商品的品牌
     *
     * @param keyword
     * @return
     */
    @GetMapping("/list-BrandsByKeyword")
    @ApiOperation("根据关键字查询商品相关品牌接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "String", name = "keyword", value = "查询关键字", required = true)})
    public ResultVO getBrandsByKeyword(String keyword) {
        ResultVO resultVO = productService.listBrands(keyword);
        return resultVO;
    }

    @GetMapping("/listProductAll")
    @ApiOperation("查询商品所有的信息接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "String", name = "productId", value = "商品Id", required = true)})
    public ResultVO getProductAll(String productId) {
        ResultVO product = productService.getProductById(productId);
        ResultVO productImgs = productService.getProductImgById(productId);
        ResultVO productSkus = productService.getProductSkuById(productId);
        ResultVO productParam = productService.getProductParamsById(productId);
        HashMap<String, Object> map = new HashMap<>();
        map.put("product", product.getData());
        map.put("productImgs", productImgs.getData());
        map.put("productSkus", productSkus.getData());
        List<ProductParams> productParamsList = (List<ProductParams>) productParam.getData();
        if (productParamsList!=null){
            map.put("productParam",productParamsList.get(0));
        }
        return new ResultVO(ResStatus.OK, "success", map);

    }
    @GetMapping("/listProduct")
    @ApiOperation("查询商品信息接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "String", name = "productId", value = "商品Id", required = true)})
    public ResultVO getProduct(String productId) {
        ResultVO product = productService.getProductById(productId);
        return product;
    }

    @PutMapping("/updateProduct")
    @ApiOperation("根据主键更新商品")
    public ResultVO modifyProduct(@RequestHeader("token") String token, @RequestBody Product product){
        product.setUpdateTime(new Date());
        ResultVO resultVO = productService.updateProductById(product);
        return resultVO;
    }
    @PutMapping("/updateProductParam")
    @ApiOperation("根据主键更新商品参数")
    public ResultVO modifyProductParam(@RequestHeader("token") String token, @RequestBody ProductParams productParams){
        productParams.setUpdateTime(new Date());
        ResultVO resultVO = productService.updateProductParamById(productParams);
        return resultVO;
    }
    @GetMapping("/getParam")
    @ApiOperation("根据productId查询商品")
    public ResultVO modifyProduct(@RequestHeader("token") String token, String paramId){
        ResultVO resultVO = productService.getProductParamByPid(paramId);
        return resultVO;
    }

    @PostMapping("/insertProduct")
    @ApiOperation("新增商品【无需添加主键和时间相关的东西】")
    public ResultVO insertProduct(@RequestHeader("token") String token,@RequestBody Product p){
        ResultVO resultVO = productService.addProduct(p);
        return resultVO;
    }
    @PostMapping("/insertProduct-param")
    @ApiOperation("新增商品对应的参数【无需添仅仅需要传递productId】")
    public ResultVO insertProductParam(@RequestHeader("token") String token,@RequestBody ProductParams productParam){
        ResultVO resultVO = productService.addProductParam(productParam);
        return resultVO;
    }

    @DeleteMapping("/deleteParam")
    @ApiOperation("删除参数")
    public ResultVO deleteParam(@RequestHeader("token")String token,String paramId) {
        ResultVO resultVO = productService.deleteParam(paramId);
        return resultVO;
    }
    @DeleteMapping("/deleteProduct")
    @ApiOperation("删除商品所有")
    public ResultVO deleteProduct(@RequestHeader("token")String token,String productId) {
        ResultVO resultVO = productService.deleteProduct(productId);
        return resultVO;
    }
}
