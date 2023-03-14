package com.atguigu.cart.service.impl;

import com.atguigu.cart.mapper.CartMapper;
import com.atguigu.cart.service.CartService;
import com.atguigu.clients.ProductClient;
import com.atguigu.param.CartSaveParam;
import com.atguigu.param.ProductCollectParam;
import com.atguigu.param.ProductIdParam;
import com.atguigu.pojo.Cart;
import com.atguigu.pojo.Product;
import com.atguigu.utils.R;
import com.atguigu.vo.CartVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author hp
 */
@Service
@Slf4j
public class CartServiceImpl implements CartService {

    @Autowired
    private ProductClient productClient;

    @Autowired
    private CartMapper cartMapper;

    /**
     * 添加商品到购物车
     *
     * @return 001(成功) 002（已经存在） 003（没有库存）
     */
    @Override
    public R save(CartSaveParam cartSaveParam) {
        //查询商品数据
        ProductIdParam productIdParam = new ProductIdParam();
        productIdParam.setProductID(cartSaveParam.getProductId());
        Product product = productClient.productDetail(productIdParam);

        if (product == null) {
            return R.fail("商品已经被删除,无法添加到购物车!");
        }
        //检查库存
        if (product.getProductNum() == 0) {
            R ok = R.ok("没有库存数据!无法购买");
            ok.setCode("003");
            log.info("CartServiceImpl.save业务结束，结果:{}", ok);
            return ok;
        }

        //检查是否添加过
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", cartSaveParam.getUserId());
        queryWrapper.eq("product_id", cartSaveParam.getProductId());
        Cart cart = cartMapper.selectOne(queryWrapper);


        if (cart != null) {
            //证明购物车存在!
            //原有的数量+1
            if (cart.getNum() < product.getProductNum()) {
                cart.setNum(cart.getNum() + 1);
                cartMapper.updateById(cart);
                //返回002 提示即可
                R ok = R.ok("购物车存在该商品,数量+1");
                ok.setCode("002");
                log.info("CartServiceImpl.save业务结束，结果:{}", ok);
                return ok;
            }
            R ok = R.ok("已到达该商品库存最大值，无法添加更多到购物车！");
            ok.setCode("003");
            log.info("CartServiceImpl.save业务结束，结果:{}", ok);
            return ok;
        }
        //添加购物车
        cart = new Cart();
        cart.setNum(1); //第一次添加 1
        cart.setUserId(cartSaveParam.getUserId());
        cart.setProductId(cartSaveParam.getProductId());
        int rows = cartMapper.insert(cart);
        log.info("CartServiceImpl.save业务结束，结果:{}", rows);
        //结果封装和返回
        CartVo cartVo = new CartVo(product, cart);

        return R.ok("购物车数据添加成功!", cartVo);
    }

    /**
     * 根据用户id，返回购物车数据
     *
     * @return 确保要返回一个数组，要不然前端报错
     */
    @Override
    public R list(Integer userId) {
        //1.用户id查询 购物车数据
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<Cart> cartList = cartMapper.selectList(queryWrapper);

        //2.判断是否存在,不存在,返回一个空集合!
        if (cartList == null || cartList.size() == 0) {
            cartList = new ArrayList<>();//必须返回空数据
            return R.ok("购物车空空如也!", cartList);
        }

        //3.存在获取商品的id集合,并且调用商品服务查询
        List<Integer> productIds = new ArrayList<>();
        for (Cart cart : cartList) {
            productIds.add(cart.getProductId());
        }
        ProductCollectParam productCollectParam = new ProductCollectParam();
        productCollectParam.setProductIds(productIds);
        List<Product> productList = productClient.cartList(productCollectParam);
        // 4. 进行vo的封装
        // 商品集合 - 商品map  商品的id = key    商品 = value
        //jdk 8 stream
        Map<Integer, Product> productMap = productList.stream().collect(Collectors.toMap(Product::getProductId, v -> v));

        //4.进行vo的封装
        List<CartVo> cartVoList = new ArrayList<>();

        for (Cart cart : cartList) {
            CartVo cartVo = new CartVo(productMap.get(cart.getProductId()), cart);
            cartVoList.add(cartVo);
        }
        R r = R.ok("数据库数据查询成功!", cartVoList);
        log.info("CartServiceImpl.list业务结束，结果:{}", r);
        return r;
    }

    /**
     * 更新购物车业务
     * 1.查询商品数据
     * 2.判断库存是否可用
     * 3.正常修改即可
     * @param cart
     * @return
     */
    @Override
    public R update(Cart cart) {
        // 1.查询商品数据
        ProductIdParam productIdParam = new ProductIdParam();
        productIdParam.setProductID(cart.getProductId());
        Product product = productClient.productDetail(productIdParam);
        // 2.判断库存是否可用
        if (cart.getNum() > product.getProductNum()) {
            log.info("CartServiceImpl.list业务结束，结果:{}", "修改失败！库存不足！");
            return R.fail("修改失败！库存不足！");
        }
        // 3. 正常修改
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", cart.getUserId());
        queryWrapper.eq("product_id",cart.getProductId());
        Cart newCart = cartMapper.selectOne(queryWrapper);
        newCart.setNum(cart.getNum());
        int rows = cartMapper.updateById(newCart);
        log.info("CartServiceImpl.update业务结束，结果:{}", rows);
        return R.ok("修改购物车数量成功！");
    }

    /**
     * 删除购物车数据
     * @param cart
     * @return
     */
    @Override
    public R remove(Cart cart) {
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", cart.getUserId());
        queryWrapper.eq("product_id", cart.getProductId());
        int rows = cartMapper.delete(queryWrapper);
        log.info("CartServiceImpl.remove业务结束，结果:{}", rows);
        return R.ok("删除购物车商品成功！");

    }

    /**
     * 清空对应用户的购物车项
     *
     * @param cardIds
     */
    @Override
    public void clearIds(List<Integer> cardIds) {
        cartMapper.deleteBatchIds(cardIds);
        log.info("CartServiceImpl.clearIds业务结束，结果:{}",cardIds);
    }

    /**
     * 后台查询购物车项
     *
     * @param productId
     * @return
     */
    @Override
    public R check(Integer productId) {
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", productId);
        Long count = cartMapper.selectCount(queryWrapper);
        if(count>0) {
              return R.fail("有"+count+"件购物车对商品引用！删除失败！");
          }
        return R.ok("购物车无商品引用！");
    }
}
