package com.example.cinema.bl.management;

import com.example.cinema.po.DateLike;
import com.example.cinema.vo.MovieBatchOffForm;
import com.example.cinema.vo.MovieForm;
import com.example.cinema.vo.MovieVO;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author fjj
 * @date 2019/3/12 6:24 PM
 */
public interface MovieService {
    /**
     * 上架电影
     *
     * @param addMovieForm
     */
    void addMovie(MovieForm addMovieForm);

    /**
     * 根据id搜索电影
     *
     * @param id
     * @param userId
     * @return
     */
    MovieVO searchOneMovieByIdAndUserId(int id, int userId);

    /**
     * 搜索全部电影
     *
     * @return
     */
    List<MovieVO> searchAllMovie();

    /**
     * 搜索全部电影(不包括已经下架的)
     *
     * @return
     */
    List<MovieVO> searchOtherMoviesExcludeOff();

    /**
     * 想看电影
     *
     * @param userId
     * @param movieId
     * @return
     */
    @Nullable String likeMovie(int userId, int movieId);

    /**
     * 取消想看电影
     *
     * @param userId
     * @param movieId
     * @return
     */
    @Nullable String unLikeMovie(int userId, int movieId);

    /**
     * 统计想看电影的人数
     *
     * @param movieId
     * @return
     */
    int getCountOfLikes(int movieId);


    /**
     * 根据关键字搜索电影
     *
     * @param keyword
     * @return
     */
    List<MovieVO> getMovieByKeyword(String keyword);

    /**
     * 获得电影每日的想看人数
     *
     * @param movieId
     * @return
     */
    List<DateLike> getLikeNumsGroupByDate(int movieId);

    /**
     * 批量下架电影
     *
     * @param movieBatchOffForm
     * @return
     */
    @Nullable String pullOfBatchOfMovie(MovieBatchOffForm movieBatchOffForm);

    /**
     * 更新电影信息
     *
     * @param updateMovieForm
     * @return
     */
    @Nullable String updateMovie(MovieForm updateMovieForm);
}
