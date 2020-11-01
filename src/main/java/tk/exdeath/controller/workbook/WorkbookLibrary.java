package tk.exdeath.controller.workbook;

import tk.exdeath.model.service.WorkbookService;
import tk.exdeath.model.workbooks.Informatics.grade7.page8.Informatics78;

public abstract class WorkbookLibrary {
    private static String lesson;
    private static int grade;
    private static int page;
    private static Object workbook;

    public static void setWorkbookAndPage(int taskID, String tableName) {
        switch (tableName) {
            case "Информатика 7 класс, стр 8": {
                WorkbookService<Informatics78> workbookService = new WorkbookService<>();
                workbook = workbookService.read(new Informatics78(), taskID);
                lesson = "Informatics";
                grade = 7;
                page = 8;
            }
        }
    }

    public static String getLesson() {
        return lesson;
    }

    public static int getGrade() {
        return grade;
    }

    public static int getPage() {
        return page;
    }

    public static Object getWorkbook() {
        return workbook;
    }
}
