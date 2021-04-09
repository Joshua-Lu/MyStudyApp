package com.lhf.javacommonlib.net.okhttp;

import com.lhf.javacommonlib.common.Constants;
import com.lhf.javacommonlib.utils.CommonUtils;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author JoshuaLu
 * @date 2021/4/8 10:09
 */
public class OkHttpTest {
    @Test
    public void test() {
        OkHttpClient client = new OkHttpClient.Builder().build();

        Request request = new Request.Builder().url(Constants.Url.BaiDu).build();
        Call call = client.newCall(request);

        // 1.同步请求 execute()
        // 放在try()里的资源（要实现AutoCloseable），try结束后，会被自动关闭
        try (Response response = call.execute()) {
            System.out.println("OkHttpTest.test: 同步请求 response = [" + response + "]");
            System.out.println("OkHttpTest.test: 同步请求 response.body() = [" + response.body().string() + "]");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("同步请求结束");

        // 2.异步请求 enqueue()
        // call只能执行一次，所以要先clone才能重新执行
        call = call.clone();
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println("OkHttpTest.onFailure() called with:异步请求 call = [" + call + "], e = [" + e + "]");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                System.out.println("OkHttpTest.onResponse() called with:异步请求 call = [" + call + "], response = [" + response + "]");
            }
        });
        System.out.println("异步请求结束");
        CommonUtils.threadSleep(2000);
    }
}
