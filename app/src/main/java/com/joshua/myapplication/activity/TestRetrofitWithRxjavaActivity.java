package com.joshua.myapplication.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.joshua.myapplication.R;
import com.lhf.javacommonlib.net.retrofit.GitHubServiceWithRxjava;
import com.lhf.javacommonlib.net.retrofit.Repo;
import com.lhf.javacommonlib.utils.CommonUtils;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 测试Retrofit结合RxJava使用
 *
 * @author Joshua
 * @date 2021/4/18 14:08
 */
public class TestRetrofitWithRxjavaActivity extends AppCompatActivity {

    @BindView(R.id.btn_download_update)
    Button btnDownloadUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_retrofit_with_rxjava);
        ButterKnife.bind(this);


    }

    public void downloadAndUpdate(View view) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GitHubServiceWithRxjava.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        GitHubServiceWithRxjava gitHubService = retrofit.create(GitHubServiceWithRxjava.class);

        Observable<List<Repo>> call = gitHubService.listRepos(GitHubServiceWithRxjava.MY_NAME);
        // 切换到io线程执行网络请求
        call.subscribeOn(Schedulers.io())
                // 切换到主线程，更新UI
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Repo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("TestRetrofitWithRxjavaActivity.onSubscribe() called with: d = [" + d + "]");
                    }

                    @Override
                    public void onNext(List<Repo> repos) {
                        CommonUtils.printThreadName();
                        System.out.println("TestRetrofitWithRxjavaActivity.onNext() called with: repos = [" + repos + "]");

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("TestRetrofitWithRxjavaActivity.onComplete() called");
                        btnDownloadUpdate.setText("download success");
                    }
                });
    }
}