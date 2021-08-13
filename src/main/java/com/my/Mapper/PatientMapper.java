package com.my.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.ibatis.annotations.Param;

import com.my.bean.Patient;

public interface PatientMapper {
	public int addDoctor(Patient patient);
	
	public int deleteDoctorData(String id);
	
	public int deletePatientData(String idCard);
	
	public int addPatient(Patient patient);

	public int deleteMedical(String id);
	
	public List<Patient>selectAllDoctorData();
	
	public List<Patient>falshDoctorData(String id);
	
	public List<Patient>selectDoctorData(String id);
	
	public List<Patient>selectPatientData(String idCard);
	
	public List<Patient>selectPatientInfo(String idCard);
	
	public List<Patient>flashPatientData(String idCard);
	
	public List<Patient>selectAllPatientData();
	
	public List<Patient>selectAllMedical();

	public int updatePatientData(@Param("cha")String cha,@Param("idCard")String idCard);

	public List<Patient>selectMedical(String id);
	
	public int updateDoctorData(@Param("cha")String cha,@Param("id")String id);
	
	public int addFeedBack(Patient patient);
}
