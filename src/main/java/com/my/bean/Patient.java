package com.my.bean;

import java.util.Enumeration;

/*
    实体类
 */
public class Patient {
    private String name,gender,id,address;
    private int age;
    private String section;  //科室，部门
    private String nation;  //民族
    private String telephone,birthday;
    private String workspace;     //职称
    private String idCard;          //医保卡号
    private String guardian;    //监护人
    private String OPCID;       //门诊号码
    public String password;     //登录密码
    public String dateWork;     //入职日期
    private int price,number;
    private String end,content;

    public  Patient(){}
    public Patient(String name, String password, String gender, String end,String content,int age, int price,int number,String id, String address,
                   String section, String nation, String telephone, String birthday, String workspace, String idCard, String guardian, String OPCID,String dateWork){
        this.name=name;
        this.end=end;
        this.content=content;
        this.password=password;
        this.gender=gender;
        this.age=age;
        this.price=price;
        this.number=number;
        this.id=id;
        this.address=address;
        this.section=section;
        this.nation=nation;
        this.telephone=telephone;
        this.birthday=birthday;
        this.workspace=workspace;
        this.idCard=idCard;
        this.guardian=guardian;
        this.OPCID=OPCID;
        this.dateWork=dateWork;
    }

    public void setName(String name){
        this.name=name;
    }
    public void setPassword(String password){this.password=password;}
    public void setGender(String gender){
        this.gender=gender;
    }
    public void setAge(int age){
        this.age=age;
    }
    public void setId(String id){
        this.id=id;
    }
    public void setAddress(String address){
        this.address=address;
    }
    public void setSection(String section){
        this.section=section;
    }
    public void setNation(String nation){
        this.nation=nation;
    }
    public void setTelephone(String telephone){
        this.telephone=telephone;
    }
    public void setBirthday(String birthday){
        this.birthday=birthday;
    }
    public void setWorkspace(String workspace){
        this.workspace=workspace;
    }
    public void setIdCard(String idCard){
        this.idCard=idCard;
    }
    public void setGuardian(String guardian){
        this.guardian=guardian;
    }
    public void setOPCID(String OPCID){
        this.OPCID=OPCID;
    }
    public void setDateWork(String dateWork){this.dateWork=dateWork;}
    public void setPrice(int price){this.price=price;}
    public void setNumber(int number){this.number=number;}
    public void setEnd(String end) {this.end=end;}
    public void setContent(String content) {this.content=content;}
    public String getContent() {return content;}
    public String getEnd() {return end;}
    public int getPrice(){return price;}
    public int getNumber(){return number;}
    public String getName(){
        return name;
    }
    public String getPassword(){return password;}
    public String getGender(){
        return gender;
    }
    public int getAge(){
        return age;
    }
    public String getId(){
        return id;
    }
    public String getAddress(){
        return address;
    }
    public String getSection(){
        return section;
    }
    public String getNation(){
        return nation;
    }
    public String getTelephone(){
        return telephone;
    }
    public String getBirthday(){
        return birthday;
    }
    public String getWorkspace(){
        return workspace;
    }
    public String getIdCard(){
        return idCard;
    }
    public String getGuardian(){
        return guardian;
    }
    public String getOPCID(){
        return OPCID;
    }
    public String getDateWork() {return dateWork;}
	@Override
	public String toString() {
		return "Patient [name=" + name + ", gender=" + gender + ", id=" + id + ", address=" + address + ", age=" + age
				+ ", section=" + section + ", nation=" + nation + ", telephone=" + telephone + ", birthday=" + birthday
				+ ", workspace=" + workspace + ", idCard=" + idCard + ", guardian=" + guardian + ", OPCID=" + OPCID
				+ ", password=" + password + ", dateWork=" + dateWork + ", price=" + price + ", number=" + number + "]";
	}
}
