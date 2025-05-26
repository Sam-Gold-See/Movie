package com.samgoldsee.movie.controller;

import com.samgoldsee.movie.dto.UserPasswordDTO;
import com.samgoldsee.movie.dto.UserProfileDTO;
import com.samgoldsee.movie.dto.UserRegisterDTO;
import com.samgoldsee.movie.dto.UserSession;
import com.samgoldsee.movie.result.Result;
import com.samgoldsee.movie.service.UserService;
import com.samgoldsee.movie.vo.UserVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@Slf4j
@Tag(name = "用户功能管理接口")
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户注册
     *
     * @param userRegisterDTO 用户注册DTO对象
     */
    @PostMapping("/register")
    @Operation(summary = "用户注册")
    @ApiResponse(responseCode = "200", description = "成功", content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = Result.class)
    ))
    public Result<String> register(@RequestBody UserRegisterDTO userRegisterDTO) {
        log.info("用户注册:{}", userRegisterDTO);
        userService.register(userRegisterDTO);
        return Result.success();
    }

    /**
     * 用户修改密码
     *
     * @param userPasswordDTO 用户修改密码DTo
     */
    @PostMapping("/updatePassword")
    @Operation(summary = "用户修改密码")
    @ApiResponse(responseCode = "200", description = "成功", content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = Result.class)
    ))
    public Result<String> updatePassword(@RequestBody UserPasswordDTO userPasswordDTO) {
        log.info("用户(email:{})修改密码:{}", userPasswordDTO.getEmail(), userPasswordDTO.getPassword());
        userService.updatePassword(userPasswordDTO);
        return Result.success();
    }

    /**
     * 查询用户信息
     */
    @GetMapping("/profile")
    @Operation(summary = "用户查询信息")
    @ApiResponse(responseCode = "200", description = "成功", content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = UserVOResult.class)
    ))
    public Result<UserVO> getProfile() {
        UserSession userSession = (UserSession) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserVO userVO = userService.getProfile(userSession.getId());
        log.info("用户(id:{})查询信息:{}", userSession.getId(), userVO);
        return Result.success(userVO);
    }

    @Schema(name = "Result<UserVO>", description = "包含UserVO的统一响应对象")
    private static class UserVOResult extends Result<UserVO> {
    }

    /**
     * 用户修改信息
     *
     * @param userProfileDTO 用户信息DTO
     */
    @PostMapping("/updateProfile")
    @Operation(summary = "用户修改信息")
    @ApiResponse(responseCode = "200", description = "成功", content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = Result.class)
    ))
    public Result<String> updateProfile(@RequestBody UserProfileDTO userProfileDTO) {
        UserSession userSession = (UserSession) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("用户(id:{})修改信息:{}", userSession.getId(), userProfileDTO);
        userService.updateProfile(userProfileDTO);
        return Result.success();
    }

    /**
     * 用户上传头像
     *
     * @param file 上传文件
     */
    @PostMapping("/uploadAvatar")
    @Operation(summary = "用户上传头像")
    @ApiResponse(responseCode = "200", description = "成功", content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = Result.class)
    ))
    public Result<String> uploadAvatar(@RequestPart("avatar") MultipartFile file) {
        UserSession userSession = (UserSession) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("用户(id:{})修改头像", userSession.getId());
        userService.uploadAvatar(file);
        return Result.success();
    }
}
