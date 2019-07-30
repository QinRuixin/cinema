package com.example.cinema.blImpl.management;

import com.example.cinema.bl.management.MovieService;
import com.example.cinema.data.management.MovieMapper;
import com.example.cinema.data.management.ScheduleMapper;
import com.example.cinema.data.statistics.MovieLikeMapper;
import com.example.cinema.data.user.AccountMapper;
import com.example.cinema.po.DateLike;
import com.example.cinema.vo.MovieBatchOffForm;
import com.example.cinema.vo.MovieForm;
import com.example.cinema.vo.MovieVO;
import com.example.cinema.vo.ScheduleItem;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author fjj
 * @date 2019/3/12 6:43 PM
 */
@Service
public class MovieServiceImpl implements MovieService {

    private static final String ALREADY_LIKE_ERROR_MESSAGE = "用户已标记该电影为想看";
    private static final String UNLIKE_ERROR_MESSAGE = "用户未标记该电影为想看";
    private static final String USER_NOT_EXIST_ERROR_MESSAGE = "用户不存在";
    private static final String MOVIE_NOT_EXIST_ERROR_MESSAGE = "电影不存在";
    private static final String SCHEDULE_ERROR_MESSAGE = "有电影后续仍有排片或已有观众购票且未使用";

    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private MovieLikeMapper movieLikeMapper;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private ScheduleMapper scheduleMapper;

    @Override
    @Transactional(rollbackFor = DuplicateKeyException.class)
    public void addMovie(MovieForm addMovieForm) {
        movieMapper.insertOneMovie(addMovieForm);
    }

    @Override
    public MovieVO searchOneMovieByIdAndUserId(int id, int userId) {
        return movieMapper.selectMovieByIdAndUserId(id, userId);
    }

    @Override
    public List<MovieVO> searchAllMovie() {
        return movieMapper.selectAllMovie();
    }

    @Override
    public List<MovieVO> searchOtherMoviesExcludeOff() {
        return movieMapper.selectOtherMoviesExcludeOff();
    }

    @Override
    @Transactional(rollbackFor = DuplicateKeyException.class)
    public @Nullable String likeMovie(int userId, int movieId) {
        //user 判空(已补充
        if (userLikeTheMovie(userId, movieId)) {
            return ALREADY_LIKE_ERROR_MESSAGE;
        } else if (movieMapper.selectMovieById(movieId) == null) {
            return MOVIE_NOT_EXIST_ERROR_MESSAGE;
        } else if(accountMapper.getAccountById(userId)==null){
            return USER_NOT_EXIST_ERROR_MESSAGE;
        }
        movieLikeMapper.insertOneLike(movieId, userId);
        return null;
    }

    @Override
    public @Nullable String unLikeMovie(int userId, int movieId) {
        if (!userLikeTheMovie(userId, movieId)) {
            return UNLIKE_ERROR_MESSAGE;
        }
//        else if (movieMapper.selectMovieById(movieId) == null) {
//            return MOVIE_NOT_EXIST_ERROR_MESSAGE;
//        }
        movieLikeMapper.deleteOneLike(movieId, userId);
        return null;
    }

    @Override
    public int getCountOfLikes(int movieId) {
        return movieLikeMapper.selectLikeNums(movieId);
    }

    @Override
    public List<MovieVO> getMovieByKeyword(String keyword) {
        if (keyword == null || keyword.equals("")) {
            return movieMapper.selectAllMovie();
        }
        return movieMapper.selectMovieByKeyword(keyword);
    }

    @Override
    public List<DateLike> getLikeNumsGroupByDate(int movieId) {
        return movieLikeMapper.getDateLikeNum(movieId);
    }

    @Override
    public @Nullable String pullOfBatchOfMovie(MovieBatchOffForm movieBatchOffForm) {
        List<Integer> movieIdList = movieBatchOffForm.getMovieIdList();
        String s = preCheck(movieIdList);
        if (s != null) {
            return s;
        }
        movieMapper.updateMovieStatusBatch(movieIdList);
        return null;
    }

    @Override
    public @Nullable String updateMovie(MovieForm updateMovieForm) {
        String s = preCheck(Collections.singletonList(updateMovieForm.getId()));
        if (s != null) {
            return s;
        }
        movieMapper.updateMovie(updateMovieForm);
        return null;
    }


    /**
     * 下架和修改电影的前置检查
     *
     * @param movieIdList
     * @return
     */
    private @Nullable String preCheck(List<Integer> movieIdList) {
        Date thisTime = new Date();
        List<ScheduleItem> scheduleItems = scheduleMapper.selectScheduleByMovieIdList(movieIdList);

        // 检查是否有电影后续有排片及是否有观众购票未使用
        for (ScheduleItem s : scheduleItems) {
            if (s.getEndTime().after(thisTime)) {
                return SCHEDULE_ERROR_MESSAGE;
            }
        }
        return null;
    }

    private boolean userLikeTheMovie(int userId, int movieId) {
        return movieLikeMapper.selectLikeMovie(movieId, userId) != 0;
    }

}
