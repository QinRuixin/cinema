package com.example.cinema.controller.management;

import com.example.cinema.bl.management.MovieService;
import com.example.cinema.vo.MovieBatchOffForm;
import com.example.cinema.vo.MovieForm;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

/**
 * 电影管理
 *
 * @author fjj
 * @date 2019/3/12 6:17 PM
 */
@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/movie/add")
    public ResponseVO addMovie(@RequestBody MovieForm addMovieForm) {
        try {
            movieService.addMovie(addMovieForm);
            return ResponseVO.buildSuccess();
        } catch (DuplicateKeyException e) {
            return ResponseVO.buildFailure("添加电影失败");
        }
    }

    @GetMapping("/movie/{id}/{userId}")
    public ResponseVO searchOneMovieByIdAndUserId(@PathVariable int id, @PathVariable int userId) {
        return ResponseVO.buildSuccess(movieService.searchOneMovieByIdAndUserId(id, userId));
    }

    @GetMapping("/movie/all")
    public ResponseVO searchAllMovie() {
        //返回结果中包括已经下架的电影
        return ResponseVO.buildSuccess(movieService.searchAllMovie());
    }

    @GetMapping("/movie/all/exclude/off")
    public ResponseVO searchOtherMoviesExcludeOff() {
        //返回结果中不包括已经下架的电影
        return ResponseVO.buildSuccess(movieService.searchOtherMoviesExcludeOff());
    }


    @PostMapping("/movie/{movieId}/like")
    public ResponseVO likeMovie(@PathVariable int movieId, @RequestParam int userId) {
        try {
            String s = movieService.likeMovie(userId, movieId);
            if (s != null) {
                return ResponseVO.buildFailure(s);
            }
            return ResponseVO.buildSuccess();
        } catch (DuplicateKeyException e) {
            return ResponseVO.buildFailure("喜欢电影失败，你已经喜欢该电影了。");
        }
    }

    @PostMapping("/movie/{movieId}/unlike")
    public ResponseVO unlikeMovie(@PathVariable int movieId, @RequestParam int userId) {
        String s = movieService.unLikeMovie(userId, movieId);
        if (s != null) {
            return ResponseVO.buildFailure(s);
        } else {
            return ResponseVO.buildSuccess();
        }
    }

    @GetMapping("/movie/{movieId}/like/count")
    public ResponseVO getMovieLikeCounts(@PathVariable int movieId) {
        return ResponseVO.buildSuccess(movieService.getCountOfLikes(movieId));
    }

    @GetMapping("/movie/{movieId}/like/date")
    public ResponseVO getMovieLikeCountByDate(@PathVariable int movieId) {
        return ResponseVO.buildSuccess(movieService.getLikeNumsGroupByDate(movieId));
    }

    @GetMapping("/movie/search")
    public ResponseVO getMovieByKeyword(@RequestParam String keyword) {
        return ResponseVO.buildSuccess(movieService.getMovieByKeyword(keyword));
    }

    @PostMapping("/movie/off/batch")
    public ResponseVO pullOfBatchOfMovie(@RequestBody MovieBatchOffForm movieBatchOffForm) {
        final String s = movieService.pullOfBatchOfMovie(movieBatchOffForm);
        if (s != null) {
            return ResponseVO.buildFailure(s);
        }
        return ResponseVO.buildSuccess();
    }

    @PostMapping("/movie/update")
    public ResponseVO updateMovie(@RequestBody MovieForm updateMovieForm) {
        final String s = movieService.updateMovie(updateMovieForm);
        if (s != null) {
            return ResponseVO.buildFailure(s);
        }
        return ResponseVO.buildSuccess();
    }


}
