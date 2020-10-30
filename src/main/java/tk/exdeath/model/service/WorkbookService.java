package tk.exdeath.model.service;

import tk.exdeath.model.DAO.WorkbookDAO;

public class WorkbookService<E> {

    private final WorkbookDAO<E> DAO = new WorkbookDAO<>();

    public void create(E workbook) {
        DAO.create(workbook);
    }

    public void update(E workbook) {
        DAO.update(workbook);
    }

    public void delete(E workbook) {
        DAO.delete(workbook);
    }
}
