package com.spadea.xqipao.ui.me.contacter;

import android.support.v4.app.FragmentActivity;

import com.luck.picture.lib.entity.LocalMedia;
import com.spadea.xqipao.data.ApplyImageItem;
import com.spadea.xqipao.data.SkillApplyModel;
import com.spadea.xqipao.ui.base.presenter.IPresenter;
import com.spadea.xqipao.ui.base.view.IView;

import java.util.List;

public final class QualificationContacts {


    public interface View extends IView<FragmentActivity> {
        void uploadImageSuccess(ApplyImageItem item, int position, int mimeType);

        void uploadVoiceSuccess(String voiceUrl);

        void addApplySuccess(Boolean model);

        void randomWords(String words);

        void rules(List<String> rules);
    }

    public interface IQualificationPre extends IPresenter {
        void uploadImage(List<LocalMedia> mediaList, int position);

        void uploadVoice(String filePath);

        void addApply(SkillApplyModel model);

        void updateApply(SkillApplyModel model);

        void getRandomWords(int skillId);

        void getRules(int skillId);

    }
}