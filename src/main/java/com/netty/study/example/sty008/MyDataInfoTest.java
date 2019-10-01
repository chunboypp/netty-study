package com.netty.study.example.sty008;

public class MyDataInfoTest {

    public static void main(String[] args){
        MyDataInfo.MyMessage mymessage  = null;

        mymessage = MyDataInfo.MyMessage.newBuilder().setDataType(MyDataInfo.MyMessage.DataType.PersonType)
                .setPerson(MyDataInfo.Person.newBuilder().setAge(45).setName("bc").build()).build();

        mymessage = MyDataInfo.MyMessage.newBuilder().setDataType(MyDataInfo.MyMessage.DataType.StudentType)
                .setStudent(MyDataInfo.Student.newBuilder().setCuros("语文").build()).build();

        mymessage = MyDataInfo.MyMessage.newBuilder().setDataType(MyDataInfo.MyMessage.DataType.TeachType)
                .setTeach(MyDataInfo.Teach.newBuilder().setTeachcuros("数学").build()).build();


    }
}
