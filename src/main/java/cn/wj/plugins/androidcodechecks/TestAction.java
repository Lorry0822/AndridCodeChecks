package cn.wj.plugins.androidcodechecks;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;

public class TestAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent event) {
        Messages.showMessageDialog("我是一条消息", "提示", Messages.getInformationIcon());
    }
}
