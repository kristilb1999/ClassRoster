package com.mthree.classroster;

import com.mthree.classroster.controller.ClassRosterController;
import com.mthree.classroster.dao.ClassRosterAuditDao;
import com.mthree.classroster.dao.ClassRosterAuditDaoFileImpl;
import com.mthree.classroster.dao.ClassRosterDao;
import com.mthree.classroster.dao.ClassRosterDaoFileImpl;
import com.mthree.classroster.service.ClassRosterServiceLayer;
import com.mthree.classroster.service.ClassRosterServiceLayerImpl;
import com.mthree.classroster.ui.ClassRosterView;
import com.mthree.classroster.ui.UserIO;
import com.mthree.classroster.ui.UserIOConsoleImpl;

public class App {

    public static void main(String[] args) {
        UserIO myIo = new UserIOConsoleImpl();
        ClassRosterView myView = new ClassRosterView(myIo);

        ClassRosterDao myDao = new ClassRosterDaoFileImpl();
        ClassRosterAuditDao myAuditDao = new ClassRosterAuditDaoFileImpl();
        ClassRosterServiceLayer myService = new ClassRosterServiceLayerImpl(myDao, myAuditDao);

        ClassRosterController controller = new ClassRosterController(myService, myView);
        controller.run();
    }

}
