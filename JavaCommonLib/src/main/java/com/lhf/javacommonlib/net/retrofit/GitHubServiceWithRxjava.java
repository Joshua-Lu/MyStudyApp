package com.lhf.javacommonlib.net.retrofit;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author JoshuaLu
 * @date 2021/4/9 17:02
 */
public interface GitHubServiceWithRxjava {

    String BASE_URL = "https://api.github.com/";
    String MY_NAME = "Joshua-Lu";

    /**
     * 获取GitHub用户的repos
     * 请求地址：https://api.github.com/users/Joshua-Lu/repos
     *
     * @param user 用户名
     * @return RxJava的Observable包装后的Call
     */
    @GET("users/{user}/repos")
    Observable<List<Repo>> listRepos(@Path("user") String user);
}
