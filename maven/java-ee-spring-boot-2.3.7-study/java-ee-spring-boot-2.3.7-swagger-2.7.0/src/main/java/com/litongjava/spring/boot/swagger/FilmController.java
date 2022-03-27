package com.litongjava.spring.boot.swagger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "电影Controller", tags = { "电影访问接口" })
@RequestMapping("/film")
public class FilmController {

  /**
   * 添加一个电影数据
   *
   * @param
   * @return
   */
  @ApiOperation(value = "添加一部电影")
  @PostMapping("/addFilm")
  @ApiResponses(value = { @ApiResponse(code = 1000, message = "成功"), @ApiResponse(code = 1001, message = "失败"),
      @ApiResponse(code = 1002, response = Film.class, message = "缺少参数") })
  public ResultModel addFilm(@ApiParam("电影名称") @RequestParam("filmName") String filmName,
      @ApiParam(value = "分数", allowEmptyValue = true) @RequestParam("score") Short score,
      @ApiParam("发布时间") @RequestParam(value = "publishTime", required = false) String publishTime,
      @ApiParam("创建者id") @RequestParam("creatorId") Long creatorId) {

    return null;
  }

  /**
   * 根据电影名字获取电影
   *
   * @param fileName
   * @return
   */
  @GetMapping("/getFilms")
  @ApiOperation(value = "根据名字获取电影")
  @ApiResponses(value = { @ApiResponse(code = 1000, message = "成功"), @ApiResponse(code = 1001, message = "失败"),
      @ApiResponse(code = 1002, message = "缺少参数") })
  public ResultModel getFilmsByName(@ApiParam("电影名称") @RequestParam("fileName") String fileName) {
    return null;
  }

  /**
   * 根据电影名更新
   *
   * @return
   */
  @PostMapping("/updateScore")
  @ApiOperation(value = "根据电影名修改分数")
  @ApiResponses(value = { @ApiResponse(code = 1000, message = "成功"), @ApiResponse(code = 1001, message = "失败"),
      @ApiResponse(code = 1002, message = "缺少参数") })
  public ResultModel updateFilmScore(@ApiParam("电影名称") @RequestParam("fileName") String fileName,
      @ApiParam("分数") @RequestParam("score") Short score) {
    return null;
  }

  /**
   * 根据电影名删除电影
   *
   * @param request
   * @return
   */
  @PostMapping("/delFilm")
  @ApiOperation(value = "根据电影名删除电影")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "filmName", value = "电影名", dataType = "String", paramType = "query", required = true),
      @ApiImplicitParam(name = "id", value = "电影id", dataType = "int", paramType = "query") })
  public ResultModel deleteFilmByNameOrId(HttpServletRequest request) {
    return null;
  }

  /**
   * 根据id获取电影
   *
   * @param id
   * @return
   */
  @PostMapping("/{id}")
  @ApiOperation("根据id获取电影")
  @ApiImplicitParam(name = "id", value = "电影id", dataType = "long", paramType = "path", required = true)
  public ResultModel getFilmById(@PathVariable Long id) {

    return null;
  }

  /**
   * 修改整个电影
   *
   * @param film
   * @return
   */
  @PostMapping("/insertFilm")
  @ApiOperation("插入一部电影")
  public ResultModel insertFilm(@ApiParam("电影实体对象") @RequestBody Film film) {
    return null;
  }
}