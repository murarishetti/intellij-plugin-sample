package com.smuraris.plugin;

import com.intellij.codeInspection.*;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;

public class TodoInspection extends LocalInspectionTool {

    /**
     * TODO testing
     */
    @Override
    public ProblemDescriptor @NotNull [] checkFile(@NotNull PsiFile file,
                                                   @NotNull InspectionManager manager,
                                                   boolean isOnTheFly) {
        if (!file.isWritable()) {
            return ProblemDescriptor.EMPTY_ARRAY;
        }

        // Find TODO comments
        for (PsiComment comment : PsiTreeUtil.findChildrenOfType(file, PsiComment.class)) {
            if (comment.getText().contains("TODO")) {
                ProblemDescriptor problem = manager.createProblemDescriptor(
                        comment,
                        "TODO comment found",
                        (LocalQuickFix) null,
                        ProblemHighlightType.WARNING,
                        isOnTheFly
                );
                return new ProblemDescriptor[]{problem};
            }
        }
        return ProblemDescriptor.EMPTY_ARRAY;
    }
}
