package com.woniuxy.dto;

import lombok.Data;

@Data
public class StatusCode {
    public static final int OK = 20000;//成功
    public static final int ERROR = 20001;//失败
    public static final int LOGINERROR = 20002;//用户名或密码错误
    public static final int ACCESSERROR = 20003;//权限不足
    public static final int REMOTEERROR = 20004;//远程调用失败
    public static final int REPERROR = 20005;//重复操作
    public static final int NOTFOUNDERROR = 20006;//没有对应的抢购数据
    public static final int NUKNOWACCOUNT = 20007;//当前角色未注册
    public static final int INCORRECTCREDENTIALS = 20008;//密码错误

    public StatusCode() {
    }

    public static int getOK() {
        return OK;
    }

    public static int getERROR() {
        return ERROR;
    }

    public static int getLOGINERROR() {
        return LOGINERROR;
    }

    public static int getACCESSERROR() {
        return ACCESSERROR;
    }

    public static int getREMOTEERROR() {
        return REMOTEERROR;
    }

    public static int getREPERROR() {
        return REPERROR;
    }

    public static int getNOTFOUNDERROR() {
        return NOTFOUNDERROR;
    }

    public static int getNUKNOWACCOUNT() {
        return NUKNOWACCOUNT;
    }

    public static int getINCORRECTCREDENTIALS() {
        return INCORRECTCREDENTIALS;
    }
}
