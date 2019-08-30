package cn.wj.plugins.androidcodechecks.inspection

import com.intellij.codeHighlighting.HighlightDisplayLevel
import com.intellij.codeInsight.daemon.GroupNames
import com.intellij.codeInspection.AbstractBaseJavaLocalInspectionTool
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.JavaElementVisitor
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiElementVisitor
import org.jetbrains.kotlin.idea.inspections.AbstractKotlinInspection
import org.jetbrains.kotlin.idea.quickfix.RenameIdentifierFix
import org.jetbrains.kotlin.psi.classOrObjectRecursiveVisitor
import org.jetbrains.kotlin.psi.psiUtil.getSuperNames

/**
 * Kotlin 中对 Android 基本组件名称进行检查
 */
class AndroidComponentNameKotlinInspection : AbstractKotlinInspection() {

    override fun isEnabledByDefault(): Boolean {
        // 是否默认开启
        return true
    }

    override fun getDefaultLevel(): HighlightDisplayLevel {
        // 高亮等级
        return HighlightDisplayLevel.ERROR
    }

    override fun getShortName(): String {
        // 简短名称，同时指向 resources 下的详细说明
        return "AndroidComponentNameKotlin"
    }

    override fun getDisplayName(): String {
        // 显示名称
        return "Android Component Name Inspection Kotlin"
    }

    override fun getGroupDisplayName(): String {
        // 检查分组
        return GroupNames.STYLE_GROUP_NAME
    }

    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor {
        return classOrObjectRecursiveVisitor(fun(obj) {
            // 继承、实现接口列表
            val superClassNames = obj.getSuperNames()
            if (superClassNames.isEmpty()) {
                return
            }
            // 当前类名
            val name = obj.name ?: return
            val nameIdentifier = obj.nameIdentifier ?: return

            // 父类名
            val superName = superClassNames[0]

            // 检查 Activity
            if (superName.endsWith("Activity") && !name.endsWith("Activity")) {
                // 是 Activity 没有以 Activity 结尾
                holder.registerProblem(
                        nameIdentifier,
                        "Android 中 Activity 的子类名称必须以 Activity 结尾！",
                        RenameIdentifierFix()
                )
                return
            }

            // 检查 Service
            if (superName.endsWith("Service") && !name.endsWith("Service")) {
                // 是 Service 没有以 Service 结尾
                holder.registerProblem(
                        nameIdentifier,
                        "Android 中 Service 的子类名称必须以 Service 结尾！",
                        RenameIdentifierFix()
                )
                return
            }

            // 检查 BroadcastReceiver
            if (superName.endsWith("BroadcastReceiver") && !name.endsWith("Receiver")) {
                // 是 Service 没有以 Service 结尾
                holder.registerProblem(
                        nameIdentifier,
                        "Android 中 BroadcastReceiver 的子类名称必须以 Receiver 结尾！",
                        RenameIdentifierFix()
                )
                return
            }

            // 检查 BroadcastReceiver
            if (superName.endsWith("ContentProvider") && !name.endsWith("Provider")) {
                // 是 Service 没有以 Service 结尾
                holder.registerProblem(
                        nameIdentifier,
                        "Android 中 ContentProvider 的子类名称必须以 Provider 结尾！",
                        RenameIdentifierFix()
                )
                return
            }
        })
    }
}

/**
 * Java 中对 Android 基本组件名称进行检查
 */
class AndroidComponentNameJavaInspection : AbstractBaseJavaLocalInspectionTool() {

    override fun isEnabledByDefault(): Boolean {
        // 是否默认开启
        return true
    }

    override fun getDefaultLevel(): HighlightDisplayLevel {
        // 高亮等级
        return HighlightDisplayLevel.ERROR
    }

    override fun getShortName(): String {
        // 简短名称，同时指向 resources 下的详细说明
        return "AndroidComponentNameJava"
    }

    override fun getDisplayName(): String {
        // 显示名称
        return "Android Component Name Inspection Java"
    }

    override fun getGroupDisplayName(): String {
        // 检查分组
        return GroupNames.STYLE_GROUP_NAME
    }

    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor {
        return object : JavaElementVisitor() {
            override fun visitClass(aClass: PsiClass?) {
                super.visitClass(aClass)

                if (aClass == null) {
                    return
                }

                // 父类名
                val superClassName = aClass.superClass?.name.orEmpty()
                if (superClassName.isBlank()) {
                    return
                }
                // 当前类名
                val name = aClass.name ?: return
                val nameIdentifier = aClass.nameIdentifier ?: return

                // 检查 Activity
                if (superClassName.endsWith("Activity") && !name.endsWith("Activity")) {
                    // 是 Activity 没有以 Activity 结尾
                    holder.registerProblem(
                            nameIdentifier,
                            "Android 中 Activity 的子类名称必须以 Activity 结尾！",
                            RenameIdentifierFix()
                    )
                    return
                }

                // 检查 Service
                if (superClassName.endsWith("Service") && !name.endsWith("Service")) {
                    // 是 Service 没有以 Service 结尾
                    holder.registerProblem(
                            nameIdentifier,
                            "Android 中 Service 的子类名称必须以 Service 结尾！",
                            RenameIdentifierFix()
                    )
                    return
                }

                // 检查 BroadcastReceiver
                if (superClassName.endsWith("BroadcastReceiver") && !name.endsWith("Receiver")) {
                    // 是 Service 没有以 Service 结尾
                    holder.registerProblem(
                            nameIdentifier,
                            "Android 中 BroadcastReceiver 的子类名称必须以 Receiver 结尾！",
                            RenameIdentifierFix()
                    )
                    return
                }

                // 检查 BroadcastReceiver
                if (superClassName.endsWith("ContentProvider") && !name.endsWith("Provider")) {
                    // 是 Service 没有以 Service 结尾
                    holder.registerProblem(
                            nameIdentifier,
                            "Android 中 ContentProvider 的子类名称必须以 Provider 结尾！",
                            RenameIdentifierFix()
                    )
                    return
                }
            }
        }
    }
}