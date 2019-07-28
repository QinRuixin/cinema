package com.example.cinema.bl.promotion;

import com.example.cinema.po.VIPCard;
import com.example.cinema.vo.VIPCardForm;
import com.example.cinema.vo.VIPInfoVO;
import org.jetbrains.annotations.Nullable;


/**
 * Created by liying on 2019/4/14.
 */

public interface VIPService {
    /**
     * @param userId
     * @return
     */
    VIPCard addVIPCard(int userId);

    /**
     * @param id
     * @return
     */
    @Nullable VIPCard getCardById(int id);

    /**
     * @return
     */
    VIPInfoVO getVIPInfo();

    /**
     * @param vipCardForm
     * @return
     */
    @Nullable VIPCard charge(VIPCardForm vipCardForm);

    @Nullable VIPCard getCardByUserId(int userId);


}
