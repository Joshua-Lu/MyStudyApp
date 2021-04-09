package com.lhf.javacommonlib.net.retrofit;

import com.lhf.javacommonlib.utils.CommonUtils;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author JoshuaLu
 * @date 2021/4/9 16:43
 */
public class RetrofitTest {
    @Test
    public void test() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GitHubService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubService gitHubService = retrofit.create(GitHubService.class);

        Call<List<Repo>> call = gitHubService.listRepos(GitHubService.MY_NAME);
//        HttpUrl url = call.request().url();
//        System.out.println("RetrofitTest.test: url = [" + url + "]");

        // 同步方式
        try {
            Response<List<Repo>> response = call.execute();
            System.out.println("RetrofitTest.test:同步方式 response.body() = [" + response.body() + "]");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 异步方式
        call = call.clone();
        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                System.out.println("RetrofitTest.onResponse()异步方式 called with: call = [" + call + "], response = [" + response.body() + "]");
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                System.out.println("RetrofitTest.onFailure()异步方式 called with: call = [" + call + "], t = [" + t + "]");
            }
        });
        CommonUtils.threadSleep(2000);
    }
}
