package testcompany.testrail.main;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.editor.event.CaretEvent;
import com.intellij.openapi.editor.event.CaretListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;

import javax.swing.*;

public class MyToolWindowFactory implements ToolWindowFactory {

    private ToolWindow toolWindow;
    private JPanel panel1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private TestCaseData testCaseData;
    private TestCase currentTestCase;

    public MyToolWindowFactory() {
        CaretListener myCaretListener = new CaretListener() {
            @Override
            public void caretPositionChanged(CaretEvent e) {
                System.out.println("Caret position changed");

                Editor editor = e.getEditor();
                Project project = editor.getProject();

                if (project == null) {
                    return;
                }

                PsiFile psiFile = PsiDocumentManager.getInstance(project).getPsiFile(editor.getDocument());

                if (psiFile instanceof PsiJavaFile) {
                    System.out.println("Found java file");
                    PsiJavaFile psiJavaFile = (PsiJavaFile) psiFile;
                    PsiMethod method = PsiTreeUtil.getParentOfType(psiJavaFile.findElementAt(e.getCaret().getOffset()), PsiMethod.class);

                    if (method != null) {
                        System.out.println("Method: " + method.getName());

                        PsiAnnotation[] annotations = method.getModifierList().getAnnotations();

                        if (annotations.length > 0) {
                            for (PsiAnnotation annotation : annotations) {
                                if (annotation.getText().contains("TestLog")) {
                                    PsiAnnotationMemberValue psiAnnotationMemberValue = annotation.findDeclaredAttributeValue("id");
                                    String testId = psiAnnotationMemberValue == null ? null : psiAnnotationMemberValue.getText().replaceAll("\"", "");
                                    if (testCaseData != null && testCaseData.testCaseExists(testId)) {
                                        currentTestCase = testCaseData.getTestCase(testId);
                                        updateTable();
                                    }
                                }
                            }
                        }
                    }
                }
            }

        };

        EditorFactory.getInstance().getEventMulticaster().addCaretListener(myCaretListener);
    }

    private void updateTable() {
        System.out.println("Updating table");
        label1.setText(currentTestCase.getTitle());
        label2.setText(currentTestCase.getSteps());
        label3.setText(currentTestCase.getExpectedResults());
    }

    @Override
    public void createToolWindowContent(Project project, ToolWindow toolWindow) {
        this.toolWindow = toolWindow;
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(panel1, "", false);
        toolWindow.getContentManager().addContent(content);
        this.testCaseData = new TestCaseData();
    }
}
