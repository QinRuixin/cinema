package com.example.cinema.blImpl.promotion;

import com.example.cinema.bl.promotion.VIPService;
import com.example.cinema.data.promotion.VIPCardMapper;
import com.example.cinema.po.VIPCard;
import com.example.cinema.vo.VIPCardForm;
import com.example.cinema.vo.VIPInfoVO;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author liying
 * @date 2019/4/14
 */
@Service
public class VIPServiceImpl implements VIPService {
    @Autowired
    VIPCardMapper vipCardMapper;

    @Override
    @Transactional(rollbackFor = DuplicateKeyException.class)
    public VIPCard addVIPCard(int userId) {
        VIPCard vipCard = new VIPCard();
        vipCard.setUserId(userId);
        vipCard.setBalance(0);
        int id = vipCardMapper.insertOneCard(vipCard);
        return vipCardMapper.selectCardById(id);
    }

    @Override
    public @Nullable VIPCard getCardById(int id) {
        return vipCardMapper.selectCardById(id);
    }

    @Override
    public VIPInfoVO getVIPInfo() {
        VIPInfoVO vipInfoVO = new VIPInfoVO();
        vipInfoVO.setDescription(VIPCard.DESCRIPTION);
        vipInfoVO.setPrice(VIPCard.PRICE);
        return vipInfoVO;
    }

    @Override
    public @Nullable VIPCard charge(VIPCardForm vipCardForm) {
        VIPCard vipCard = vipCardMapper.selectCardById(vipCardForm.getVipId());
        if (vipCard == null) {
            return null;
        }
        double balance = vipCard.calculate(vipCardForm.getAmount());
        vipCard.setBalance(vipCard.getBalance() + balance);
        vipCardMapper.updateCardBalance(vipCardForm.getVipId(), vipCard.getBalance());
        return vipCard;
    }

    @Override
    public @Nullable VIPCard getCardByUserId(int userId) {
        return vipCardMapper.selectCardByUserId(userId);
    }

}
