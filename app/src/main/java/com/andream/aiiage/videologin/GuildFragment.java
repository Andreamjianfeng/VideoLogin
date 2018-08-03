package com.andream.aiiage.videologin;

/**
 * Created by 吴剑锋 on 2017/4/29.
 * 视频播放背景fragment
 */

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class GuildFragment extends Fragment {

    private CustomVideoView customVideoView;
    private Uri uri;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        customVideoView = new CustomVideoView(getContext());
        /*获取参数，根据不同的参数播放不同的视频**/
        uri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.guide_2);

        return customVideoView;
    }

    @Override
    public void onResume() {
        super.onResume();
         /*播放视频**/
        customVideoView.playVideo(uri);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (customVideoView != null) {
            customVideoView.stopPlayback();
        }
    }

    /**
     * 记得在销毁的时候让播放的视频终止
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (customVideoView != null) {
            customVideoView.stopPlayback();
        }
    }
}