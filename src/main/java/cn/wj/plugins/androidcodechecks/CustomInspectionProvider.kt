package cn.wj.plugins.androidcodechecks

import com.intellij.codeInspection.InspectionToolProvider

class CustomInspectionProvider : InspectionToolProvider {
    override fun getInspectionClasses(): Array<Class<*>> {
        return arrayOf(CamelcaseInspection::class.java)
    }
}
