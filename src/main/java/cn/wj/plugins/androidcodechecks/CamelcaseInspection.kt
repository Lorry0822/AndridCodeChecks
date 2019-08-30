package cn.wj.plugins.androidcodechecks

import com.intellij.codeInspection.ProblemsHolder
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.idea.inspections.AbstractKotlinInspection
import org.jetbrains.kotlin.psi.callExpressionVisitor
import org.jetbrains.kotlin.psi.psiUtil.startOffset

class CamelcaseInspection : AbstractKotlinInspection() {

    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean) = callExpressionVisitor(fun(call) {
        val callee = call.calleeExpression ?: return

        holder.registerProblem(
                call,
                callee.textRangeIn(call),
                "heihei haha"
        )
    })

    private fun String.isDefinedCamelCase(): Boolean {
        val toCharArray = toCharArray()
        return toCharArray
                .mapIndexed { index, current -> current to toCharArray.getOrNull(index + 1) }
                .none { it.first.isUpperCase() && it.second?.isUpperCase() ?: false }
    }

    fun PsiElement.textRangeIn(other: PsiElement): TextRange = textRange.shiftLeft(other.startOffset)
}
