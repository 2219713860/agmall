package com.zhangxu.agmall.service.impl;

import com.zhangxu.agmall.dao.IndexImgMapper;
import com.zhangxu.agmall.entity.IndexImg;
import com.zhangxu.agmall.service.IndexImgService;
import com.zhangxu.agmall.vo.ResStatus;
import com.zhangxu.agmall.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.lang.model.util.ElementScanner6;
import java.util.List;

/**
 * @author zhangxu
 * @create 2023-04-09
 */
@Service
public class IndexImgServiceaImpl implements IndexImgService {
    @Autowired
    private IndexImgMapper indexImgMapper;

    @Override
    public ResultVO listIndexImg() {
        List<IndexImg> indexImgs = indexImgMapper.listIndexImg();
        if (indexImgs.size() == 0) {
            return new ResultVO(ResStatus.NO, "fail", null);
        } else {
            return new ResultVO(ResStatus.OK, "success", indexImgs);

        }
    }
}
