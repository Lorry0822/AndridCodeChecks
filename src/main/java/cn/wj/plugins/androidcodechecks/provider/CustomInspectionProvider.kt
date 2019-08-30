//package cn.wj.plugins.androidcodechecks.provider
//
//import cn.wj.plugins.androidcodechecks.inspection.AndroidComponentNameJavaInspection
//import cn.wj.plugins.androidcodechecks.inspection.AndroidComponentNameKotlinInspection
//import com.intellij.codeInspection.InspectionToolProvider
//
///**
// * 检查工具提供者
// */
//class CustomInspectionProvider : InspectionToolProvider {
//
//    companion object {
//        /** 检查工具列表 */
//        private val INSPECTIONS = arrayOf<Class<*>>(
//                AndroidComponentNameKotlinInspection::class.java,
//                AndroidComponentNameJavaInspection::class.java
//        )
//    }
//
//    override fun getInspectionClasses(): Array<Class<*>> {
//        return INSPECTIONS
//    }
//}
