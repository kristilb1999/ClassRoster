package com.mthree.classroster.service;

import com.mthree.classroster.dao.ClassRosterDao;
import com.mthree.classroster.dao.ClassRosterPersistenceException;
import com.mthree.classroster.dto.Student;

import java.util.List;

public class ClassRosterServiceLayerImpl implements ClassRosterServiceLayer {

    ClassRosterDao dao;

    public ClassRosterServiceLayerImpl(ClassRosterDao dao) {
        this.dao = dao;
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

    //also, a pass through method -- later add Audit log info
    @Override
    public Student removeStudent(String studentId) throws ClassRosterPersistenceException {
        return dao.removeStudent(studentId);
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
