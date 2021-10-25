package com.mthree.classroster.service;

import com.mthree.classroster.dao.ClassRosterAuditDao;
import com.mthree.classroster.dao.ClassRosterDao;
import com.mthree.classroster.dao.ClassRosterPersistenceException;
import com.mthree.classroster.dto.Student;

import java.util.List;

public class ClassRosterServiceLayerImpl implements ClassRosterServiceLayer {

    private ClassRosterDao dao;
    private ClassRosterAuditDao auditDao;

    public ClassRosterServiceLayerImpl(ClassRosterDao dao, ClassRosterAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public void createStudent(Student student) throws ClassRosterDuplicateIdException, ClassRosterDataValidationException, ClassRosterPersistenceException {
        //make sure student id isn't already in use
        if(dao.getStudent(student.getStudentId()) != null) {
            throw new ClassRosterDuplicateIdException(
                    "ERROR: Could not create student. Student Id " + student.getStudentId() + " already exists.");
        }

        //validate student data
        validateStudentData(student);

        //add data so it persists
        dao.addStudent(student.getStudentId(), student);

        //now write to the audit log
        auditDao.writeAuditEntry("Student " + student.getStudentId() + " CREATED.");
    }

    //pass through method
    @Override
    public List<Student> getAllStudents() throws ClassRosterPersistenceException {
        return dao.getAllStudents();
    }

    //another pass through method
    @Override
    public Student getStudent(String studentId) throws ClassRosterPersistenceException {
        return dao.getStudent(studentId);
    }

    @Override
    public Student removeStudent(String studentId) throws ClassRosterPersistenceException {
        Student removedStudent = dao.removeStudent(studentId);

        //write to audit log
        auditDao.writeAuditEntry("Student " + studentId + " REMOVED.");

        return removedStudent;
    }

    private void validateStudentData(Student student) throws ClassRosterDataValidationException {

        if(student.getFirstName() == null ||
        student.getFirstName().trim().length() == 0 ||
        student.getLastName() == null ||
        student.getLastName().trim().length() == 0 ||
        student.getCohort() == null ||
        student.getCohort().trim().length() == 0) {
            throw new ClassRosterDataValidationException(
                    "ERROR: All fields [First Name, Last Name, Cohort] are required.");
        }

    }
}
