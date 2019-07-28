package com.example.cinema.controller.promotion;

import com.example.cinema.bl.promotion.VIPService;
import com.example.cinema.po.VIPCard;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.VIPCardForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

/**
 * @author liying
 * @date 2019/4/14
 */
@RestController()
@RequestMapping("/vip")
public class VIPCardController {
    private static final String ADD_VIP_ERR = "会员卡添加失败，可能已经购买，请勿重复购买";
    private static final String NO_CARD_ERR = "会员卡不存在";

    @Autowired
    VIPService vipService;

    @PostMapping("/add")
    public ResponseVO addVIP(@RequestParam int userId) {
        try {
            return ResponseVO.buildSuccess(vipService.addVIPCard(userId));
        } catch (DuplicateKeyException e) {
            return ResponseVO.buildFailure(ADD_VIP_ERR);
        }
    }

    @GetMapping("{userId}/get")
    public ResponseVO getVIP(@PathVariable int userId) {
        final VIPCard card = vipService.getCardByUserId(userId);
        if (card == null) {
            return ResponseVO.buildFailure(NO_CARD_ERR);
        } else {
            return ResponseVO.buildSuccess(card);
        }
    }

    @GetMapping("/getVIPInfo")
    public ResponseVO getVIPInfo() {
        return ResponseVO.buildSuccess(vipService.getVIPInfo());
    }

    @PostMapping("/charge")
    public ResponseVO charge(@RequestBody VIPCardForm vipCardForm) {
        final VIPCard vipCard = vipService.charge(vipCardForm);
        if (vipCard == null) {
            return ResponseVO.buildFailure(NO_CARD_ERR);
        }
        return ResponseVO.buildSuccess(vipCard);
    }

}
